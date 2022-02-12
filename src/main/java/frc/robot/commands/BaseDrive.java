package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class BaseDrive extends CommandBase {
  private Chassis chassis;
  private SimpleMatrix direction; private double r;

  public BaseDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    direction = RobotContainer.getDriveDirection();
    r += Constants.ROTATION_SPEED * RobotContainer.controller.getRawAxis(Constants.DRIVE_ROTATION);

    chassis.setSpeed(direction, chassis.getRotationPID(r), Constants.BASE_DRIVE);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
