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
  private double diff;

  public BaseDrive() {
    chassis = Chassis.getInstance();
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
    r = 0;
    double[][] zero = {{0}, {0}};
    direction = new SimpleMatrix(zero);
    chassis.resetAngle();
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

    chassis.setSpeed(direction, chassis.getRotationPID(r), Constants.BASE_DRIVE);
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