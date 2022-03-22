package frc.robot.commands.drive;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
    SmartDashboard.putNumber("angle", angle);
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
    else {
      if(RobotContainer.getChasisButton(Constants.DISABLE_ANGLE_FIX)) r = 0;
      else r = chassis.getRotationPID(angle);
    }

    SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();

    chassis.getPosition();

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