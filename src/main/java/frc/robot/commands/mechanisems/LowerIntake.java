package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class LowerIntake extends CommandBase {
  private Intake intake;

  private boolean stop_left;
  private boolean stop_right;

  public LowerIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }
  
  @Override
  public void initialize() {
    intake.lowerPullies();
    
    this.stop_left = false;
    this.stop_right = false;
    
    intake.leftPID();
    intake.rightPID();
  }

  
  @Override
  public void execute() {
    intake.setLeft(Constants.INTAKE_LEFT_SPEED);
    intake.setRight(Constants.INTAKE_RIGHT_SPEED);

    if(intake.isLeftAtSetpoint() && !stop_left) {
      stop_left = true;
      SmartDashboard.putBoolean("cum", true);
      intake.setEyeLeft(0);
    }
    else if(stop_left) intake.setEyeLeft(0);
    else {
      intake.leftPID();
      SmartDashboard.putBoolean("cum", false);
    }

    if(intake.isRightAtSetpoint() && !stop_right) {
      stop_right = true;
      intake.setEyeRight(0);
    }
    else if(stop_right) intake.setEyeRight(0);
    else {
      intake.rightPID();
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
