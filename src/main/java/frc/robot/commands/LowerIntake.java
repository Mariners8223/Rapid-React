package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class LowerIntake extends CommandBase {
  private static Intake intake;
  public LowerIntake() {
    intake = Intake.getInstance();
    addRequirements(intake);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {intake.lowerPullies(Constants.PULLIES_SPEED);}

  
  @Override
  public void end(boolean interrupted) {intake.lowerPullies(0);}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
