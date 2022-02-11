package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class BaseDrive extends CommandBase {
  private Chassis chassis;
  private double y; private double x; private double r;

  public BaseDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    x = RobotContainer.controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    y = -RobotContainer.controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    r = RobotContainer.controller.getRawAxis(Constants.DRIVE_ROTATION);

    chassis.setSpeed(x, y, r, Constants.BASE_DRIVE);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
