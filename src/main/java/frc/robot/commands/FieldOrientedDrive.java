package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class FieldOrientedDrive extends CommandBase {
  private Chassis chassis;

  private SimpleMatrix direction;
  private double r;
  private double diff;

  public FieldOrientedDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }
  
  @Override
  public void initialize() {
    r = 0;
    double[][] zero = {{0}, {0}};
    direction = new SimpleMatrix(zero);
    chassis.resetAngle();

    chassis.resetPosition();
  }

  @Override
  public void execute() {
    direction = RobotContainer.getDriveDirection();
    diff = RobotContainer.getDriveRotationDiff();
    if(diff != 0) {
      r += diff;
      chassis.setSmoothRotation(true);
    }
    else chassis.setSmoothRotation(false);

    SimpleMatrix position = chassis.getPosition();
    SmartDashboard.putNumber("x", position.get(0, 0));
    SmartDashboard.putNumber("y", position.get(1, 0));    

    SimpleMatrix fodMatrix = chassis.getFieldOrientedMatrix();

    chassis.setSpeed(direction, chassis.getRotationPID(r), fodMatrix);
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