package frc.robot.commands.drive;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class FieldOrientedDrive extends CommandBase {
  private Chassis chassis;

  private SimpleMatrix direction;
  private double r;
  private double angle;
  private double rotation;

  public FieldOrientedDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }
  
  @Override
  public void initialize() {
    r = 0;
    double[][] zero = {{0}, {0}};
    direction = new SimpleMatrix(zero);
    angle = chassis.getAngle();
    //chassis.resetAngle();

    chassis.resetPosition();
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

    SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();

    chassis.setSpeed(direction, r, fodMatrix);
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