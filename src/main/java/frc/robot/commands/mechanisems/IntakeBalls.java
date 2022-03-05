package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Transport;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private Transport transport;
  private Intake intake;

  private boolean left;
  private int button;
  private double time;
  private double start_time;

  private boolean stop_left;
  
  public IntakeBalls(int button, boolean left, double time) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = left;
    this.time = time;

    this.stop_left = false;
  }

  public IntakeBalls(int button, double time) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = false;
    this.time = time;

    this.stop_left = false;
  }
  
  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();

    if (button == Constants.RAISE_PULLIES_ENUM) intake.raisePullies();
    else if (button == Constants.LOWER_PULLIES_ENUM) intake.lowerPullies();
    else if (button == Constants.INTAKE_BOUTH_ENUM){
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
    else if (left) {
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    } else {
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
    stop_left = false;
  }

  
  @Override
  public void execute() {
    if(button == Constants.RAISE_PULLIES_ENUM || button == Constants.LOWER_PULLIES_ENUM) {
      if(intake.isLeftAtSetpoint() && !stop_left) {
        intake.setEyeLeft(0);
        intake.resetLeftEye();
        stop_left = true;
      }
      else if(stop_left) intake.setEyeLeft(0);
      else intake.leftPID();
      if(intake.isRightAtSetpoint()) intake.setEyeRight(0);
      else intake.rightPID();
      SmartDashboard.putBoolean("lol", stop_left);
    }
  }

  
  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
    transport.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    if(button == Constants.RAISE_PULLIES_ENUM || button == Constants.LOWER_PULLIES_ENUM) return (intake.isLeftAtSetpoint() && intake.isRightAtSetpoint());
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false; 
  }
}
