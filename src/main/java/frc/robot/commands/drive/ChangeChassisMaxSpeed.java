package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ChangeChassisMaxSpeed extends CommandBase {
  public ChangeChassisMaxSpeed() {
  }

  @Override
  public void initialize() {
    RobotContainer.setChassisSpeed(1.6);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    RobotContainer.setChassisSpeed(Constants.CHASSIS_MULTIPLIE);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
