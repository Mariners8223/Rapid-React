package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Transport;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private Transport transport;
  private Intake intake;

  private int state;
  private double time;
  private double start_time;

  private double left_start_time;
  private double right_start_time;
  private boolean stop_left;
  private boolean stop_right;

  public IntakeBalls(int button, double time) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.state = button;
    this.time = time;

    left_start_time = Constants.NO_TIME;
    right_start_time = Constants.NO_TIME;
    this.stop_left = false;
    this.stop_right = false;
  }
  
  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();

    if (state == Constants.RAISE_PULLIES_ENUM) intake.raisePullies();
    else if (state == Constants.LOWER_PULLIES_ENUM) intake.lowerPullies();
    else if (state == Constants.INTAKE_BOUTH_ENUM){
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
    else if (state == Constants.INTAKE_LEFT_ENUM) {
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    } else {
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
    left_start_time = Constants.NO_TIME;
    right_start_time = Constants.NO_TIME;
    stop_left = false;
    stop_right = false;
  }

  
  @Override
  public void execute() {
    if(state == Constants.RAISE_PULLIES_ENUM) {
      transport.transportInwards(Constants.TRANSPORT_SPEED);
      if(intake.isLeftAtSetpoint() && !stop_left) {
        if(left_start_time == Constants.NO_TIME) {
          left_start_time = Timer.getFPGATimestamp();
          intake.leftPID();
        }
        else if(Timer.getFPGATimestamp() - left_start_time > 0.1) 
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
        else if(Timer.getFPGATimestamp() - right_start_time > 0.1) 
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
    
    else if(state == Constants.LOWER_PULLIES_ENUM){
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);

      if(intake.isLeftAtSetpoint() && !stop_left) {
        stop_left = true;
      }
      else if(stop_left) intake.setEyeLeft(0);
      else {
        intake.leftPID();
        left_start_time = Constants.NO_TIME;
      }

      if(intake.isRightAtSetpoint() && !stop_right) {
        stop_right = true;
      }
      else if(stop_right) intake.setEyeRight(0);
      else {
        intake.rightPID();
        right_start_time = Constants.NO_TIME;
      }
    }
  }

  
  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
    transport.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    if(state == Constants.RAISE_PULLIES_ENUM || state == Constants.LOWER_PULLIES_ENUM) return (stop_left && stop_right);
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false; 
  }
}
