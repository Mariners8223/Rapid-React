package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PathFollower extends CommandBase {
  double time;
  double dt;
  int discret_factor;
  int last_index_position;
  int target_index;
  SimpleMatrix[] path;
  SimpleMatrix position;
  SimpleMatrix target;
  SimpleMatrix velocity;
  boolean finished = false;
  private final Chassis chassis = Chassis.getInstance();

  public PathFollower(SimpleMatrix[] path) {
    addRequirements(chassis);
    this.path = path;
    discret_factor = path.length;
  }

  @Override
  public void initialize() {
    chassis.resetPosition();
    chassis.resetAngle();
    time = Timer.getFPGATimestamp();
    last_index_position = 0;
    target_index = 0;
    position = chassis.getPosition();
    target = path[0];
  }

  @Override
  public void execute() {
    dt = Timer.getFPGATimestamp() - time;
    time = Timer.getFPGATimestamp();
    SmartDashboard.putNumber("dt", dt);
    position = chassis.getPosition();
    SmartDashboard.putNumber("position x", position.get(0, 0));
    SmartDashboard.putNumber("position y", position.get(1, 0));
    velocity = chassis.getVelocity();
    find_target();
    SmartDashboard.putNumber("target x", target.get(0, 0));
    SmartDashboard.putNumber("target y", target.get(1, 0));

    if(target_index == discret_factor - 1){
      velocity = velocity.scale(dt);
      SimpleMatrix prediction = position.plus(velocity);
      if(prediction.minus(target).normF() > position.minus(target).normF()){
        SmartDashboard.putBoolean("faggot", true);
        finished = false;
        return;
      }
    }
    else SmartDashboard.putBoolean("faggot", false);

    SimpleMatrix error = target.minus(position);
    double error_norm = error.normF();
    if(error_norm > 0.05){
      SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();
      SmartDashboard.putNumber("error x", error.scale((0.5 / error_norm)).get(0,0));
      SmartDashboard.putNumber("error y", error.scale((0.5 / error_norm)).get(1,0));
      chassis.setSpeed(error.scale((1.0 / error_norm)), chassis.getRotationPID(0), fodMatrix);
    }
    else{
      last_index_position = target_index + 1;
      if(last_index_position < discret_factor) target = path[last_index_position];
      else finished = true;
      SmartDashboard.putNumber("index", last_index_position);
      //?? I = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {
    chassis.getPosition();
    SmartDashboard.putNumber("position x", position.get(0, 0));
    SmartDashboard.putNumber("position y", position.get(1, 0));
    chassis.setMotorsSpeed(0, 0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  public void find_target(){
    velocity = velocity.scale(10 * dt);
    SimpleMatrix prediction = position.plus(velocity);
    int closest_index = discret_factor - 1;
    double dist_optimal_prediction = prediction.minus(path[closest_index]).normF();

    for(int i = last_index_position + 1; i < discret_factor - 1; i++){
      //double dist_current = Vector2d.subtract(position, path[i]).magnitude_sqered();
      double dist_current_prediction = prediction.minus(path[i]).normF();
      //if(dist_current_prediction < dist_current){
      if(dist_current_prediction < dist_optimal_prediction){
        dist_optimal_prediction = dist_current_prediction;
        closest_index = i;
      }

      if (i - last_index_position > 10) break;
      //}
    }
    target_index = closest_index;
    target = path[closest_index];
  }
}