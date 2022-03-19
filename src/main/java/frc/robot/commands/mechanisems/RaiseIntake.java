package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class RaiseIntake extends CommandBase {
  private Intake intake;

  private double left_start_time;
  private double right_start_time;
  private boolean stop_left;
  private boolean stop_right;

  public RaiseIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }
  
  @Override
  public void initialize() {
    intake.raisePullies();

    intake.leftPID();
    intake.rightPID();

    left_start_time = Constants.NO_TIME;
    right_start_time = Constants.NO_TIME;
    this.stop_left = false;
    this.stop_right = false;
  }

  
  @Override
  public void execute() {    
    if(intake.isLeftAtSetpoint() && !stop_left) {
      if(left_start_time == Constants.NO_TIME) {
        left_start_time = Timer.getFPGATimestamp();
        intake.leftPID();
      }
      else if(Timer.getFPGATimestamp() - left_start_time > 0.07) 
      {
        stop_left = true;
        intake.setEyeLeft(0);
      }
    }
    else if(stop_left) intake.setEyeLeft(0);
    else {
      intake.leftPID();
      left_start_time = Constants.NO_TIME;
    }

    if(intake.isRightAtSetpoint() && !stop_right) {
      if(right_start_time == Constants.NO_TIME) {
        right_start_time = Timer.getFPGATimestamp();
        intake.rightPID();
      }
      else if(Timer.getFPGATimestamp() - right_start_time > 0.07) 
      {
        stop_right = true;
        intake.setEyeRight(0);
      }
    }
    else if(stop_right) intake.setEyeRight(0);
    else {
      intake.rightPID();
      right_start_time = Constants.NO_TIME;
    }
  }

  
  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    return (stop_left && stop_right);
  }
}
