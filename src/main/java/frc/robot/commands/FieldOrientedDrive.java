package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class FieldOrientedDrive extends CommandBase {
  private Chassis chassis;
  private double y; private double x; private double r;

  public FieldOrientedDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  
  @Override
  public void initialize() {
    chassis.resetAngle();
  }

  
  @Override
  public void execute() {
    x = RobotContainer.controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    y = -RobotContainer.controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    r = RobotContainer.controller.getRawAxis(Constants.DRIVE_ROTATION);

    SmartDashboard.putNumber("angle", chassis.getAngle());
    SimpleMatrix robotOrientationMatrix = chassis.rotationMatrix(Math.toRadians(-chassis.getAngle()));
    SimpleMatrix fodMatrix = Constants.BASE_DRIVE.mult(robotOrientationMatrix);
    chassis.setSpeed(x, y, r, fodMatrix);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
