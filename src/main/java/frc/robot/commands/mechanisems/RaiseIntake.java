package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class RaiseIntake extends CommandBase {
  private Intake intake;

  public RaiseIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }
  
  @Override
  public void initialize() {
    intake.raisePullies();
  }

  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    return (intake.leftAtSetpoint() && intake.rightAtSetpoint());
  }
}
