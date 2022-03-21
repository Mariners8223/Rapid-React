package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class LowerIntake extends CommandBase {
  private Intake intake;


  public LowerIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }
  
  @Override
  public void initialize() {
    intake.lowerPullies();
    intake.setLeft(Constants.INTAKE_LEFT_SPEED);
    intake.setRight(Constants.INTAKE_RIGHT_SPEED);
  }
  
  @Override
  public boolean isFinished() {
    return (intake.leftAtSetpoint() && intake.rightAtSetpoint());
  }
}
