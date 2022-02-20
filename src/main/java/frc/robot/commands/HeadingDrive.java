package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class HeadingDrive extends CommandBase {
  private Chassis chassis;

  private SimpleMatrix direction;
  private double heading;

  public HeadingDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
    heading = 0;
    double[][] zero = {{0}, {0}};
    direction = new SimpleMatrix(zero);
    chassis.resetAngle();
  }

  @Override
  public void execute() {
    direction = RobotContainer.getDriveDirection();
    double angle = RobotContainer.getDriveRotationDiff();
    if(angle != 361) {
      heading = angle;
    }

    SimpleMatrix robotOrientationMatrix = chassis.rotationMatrix(Math.toRadians(-chassis.getAngle()));
    SimpleMatrix fodMatrix = Constants.BASE_DRIVE.mult(robotOrientationMatrix);

    chassis.setSpeed(direction, chassis.getRotationPID(heading), fodMatrix);
  }

  @Override
  public void end(boolean interrupted) {
    chassis.setMotorsSpeed(0, 0, 0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
