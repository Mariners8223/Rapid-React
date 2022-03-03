package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Timer;

public class PathFollower extends CommandBase {
  private Chassis chassis = Chassis.getInstance();

  private double time;
  private double dt;
  private int points;
  private int last_index_position;
  private int target_index;
  private SimpleMatrix[] path;
  private SimpleMatrix position;
  private SimpleMatrix target;
  private SimpleMatrix velocity;
  private boolean finished = false;

  public PathFollower(SimpleMatrix[] path) {
    addRequirements(chassis);
    this.path = path;
    points = path.length - 1;
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
    position = chassis.getPosition();
    velocity = chassis.getVelocity();
    find_target();

    if(target_index == points){
      velocity = velocity.scale(1.0 / (double)Constants.FIND_TARGET_ITERATIONS);
      SimpleMatrix prediction = position.plus(velocity);
      if(prediction.minus(target).normF() > position.minus(target).normF()){
        finished = false;
        return;
      }
    }

    SimpleMatrix error = target.minus(position);
    double error_norm = error.normF();
    if(error_norm > 0.05){
      SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();
      chassis.setSpeed(error.scale((1.0 / error_norm)), chassis.getRotationPID(0), fodMatrix);
    }
    else{
      last_index_position = target_index + 1;
      if(last_index_position < points + 1) target = path[last_index_position];
      else finished = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    chassis.getPosition();
    chassis.setMotorsSpeed(0, 0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return finished;
  }

  public void find_target(){
    velocity = velocity.scale(Constants.FIND_TARGET_ITERATIONS * dt);
    SimpleMatrix prediction = position.plus(velocity);
    int closest_index = points;
    double dist_optimal_prediction = prediction.minus(path[closest_index]).normF();

    for(int i = last_index_position + 1; i < points; i++){
      double dist_current_prediction = prediction.minus(path[i]).normF();
      if(dist_current_prediction < dist_optimal_prediction){
        dist_optimal_prediction = dist_current_prediction;
        closest_index = i;
      }

      if (i - last_index_position > Constants.FIND_TARGET_ITERATIONS) break;
    }
    target_index = closest_index;
    target = path[closest_index];
  }
}