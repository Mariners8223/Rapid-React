package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.CommandBase;
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
  }
  
  @Override
  public boolean isFinished() {
    return (intake.leftAtSetpoint() && intake.rightAtSetpoint());
  }
}
