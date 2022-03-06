package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class ResetAngle extends CommandBase {
  private Chassis chassis;
  public ResetAngle() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
    chassis.resetAngle();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
