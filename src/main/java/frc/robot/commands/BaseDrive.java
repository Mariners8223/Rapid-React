package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class BaseDrive extends CommandBase {
  private Chassis chassis;
  
  private SimpleMatrix direction;
  private double r;
  private double angle;
  private double rotation;

  public BaseDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
    r = 0;
    double[][] zero = {{0}, {0}};
    direction = new SimpleMatrix(zero);
    angle = 0;
    chassis.resetAngle();
  }

  @Override
  public void execute() {
    direction = RobotContainer.getDriveDirection();
    rotation = RobotContainer.getDriveRotation();
    if(rotation != 0) {
      r = rotation;
      angle = chassis.getAngle();
    }
    else r = chassis.getRotationPID(angle);

    chassis.setSpeed(direction, r, Constants.BASE_DRIVE);
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