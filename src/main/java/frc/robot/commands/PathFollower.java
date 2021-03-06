package frc.robot.commands;

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
  private double angle;
  private boolean finished = false;

  public PathFollower(SimpleMatrix[] path) {
    addRequirements(chassis);
    this.path = path;
    points = path.length - 1;
  }

  @Override
  public void initialize() {
    chassis.resetPosition();
    angle = chassis.getAngle();
    //chassis.resetAngle();
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

    SimpleMatrix error = target.copy().minus(position);
    double error_norm = error.normF();
    if(error_norm > 0.15){
      SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();
      chassis.setSpeed(error.scale((1.0 / error_norm)), chassis.getRotationPID(angle), fodMatrix);
    }
    else{
      target_index++;
      last_index_position = target_index;
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
    SimpleMatrix prediction = position.copy().plus(velocity.copy().scale(Constants.FIND_TARGET_ITERATIONS * dt));
    int closest_index = points;
    double dist_optimal_prediction = prediction.copy().minus(path[closest_index]).normF();

    for(int i = last_index_position + 1; i < points; i++){
      double dist_current_prediction = prediction.copy().minus(path[i]).normF();
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