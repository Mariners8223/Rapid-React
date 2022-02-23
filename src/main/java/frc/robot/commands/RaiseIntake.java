package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.Constants;

public class RaiseIntake extends CommandBase {
  private static Intake intake;
  public RaiseIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    intake.raisePullies(Constants.PULLIES_SPEED);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
