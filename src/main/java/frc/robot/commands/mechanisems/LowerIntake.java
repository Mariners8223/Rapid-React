package frc.robot.commands.mechanisems;

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

    this.stop_left = false;
    this.stop_right = false;
  }
  
  @Override
  public void initialize() {
    intake.lowerPullies();
  }

  
  @Override
  public void execute() {
    intake.setLeft(Constants.INTAKE_LEFT_SPEED);
    intake.setRight(Constants.INTAKE_RIGHT_SPEED);

    if(intake.isLeftAtSetpoint() && !stop_left) {
      stop_left = true;
    }
    else if(stop_left) intake.setEyeLeft(0);
    else {
      intake.leftPID();
    }

    if(intake.isRightAtSetpoint() && !stop_right) {
      stop_right = true;
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
