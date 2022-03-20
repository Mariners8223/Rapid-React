package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.RaspberryPi;

public class AutoBallCollector extends CommandBase {
  private Chassis chassis;
  private RaspberryPi rPi;

  private double angle;

  private boolean left;

  private SimpleMatrix left_direction;
  private SimpleMatrix right_direction;
  private SimpleMatrix last_direction;

  private double right_distance;
  private double left_distance;

  public AutoBallCollector() {
    chassis = Chassis.getInstance();
    rPi = RaspberryPi.getInstance();
    addRequirements(chassis, rPi);
  }

  @Override
  public void initialize() {
    angle = chassis.getAngle();
    left = true;
    left_direction = Constants.ZERO_VECTOR;
    right_direction = Constants.ZERO_VECTOR;
    last_direction = Constants.ZERO_VECTOR;
  }

  @Override
  public void execute() {
    left_direction = rPi.getLeft();
    right_direction = rPi.getRight();

    left_distance = left_direction.normF();
    right_distance = right_direction.normF();

    if (left_distance == 0 && right_distance == 0) {
      if (left)
        chassis.setSpeed(last_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE_LEFT);
      else
        chassis.setSpeed(last_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE_RIGHT);
    } else {
      left = (left_distance > right_distance);

      if (left) {
        left_direction = left_direction.scale(1.0 / left_distance);
        chassis.setSpeed(left_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE_LEFT);
        last_direction = left_direction.copy();
      } else {
        right_direction = right_direction.scale(1.0 / right_distance);
        chassis.setSpeed(right_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE_RIGHT);
        last_direction = right_direction.copy();
      }
    }
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
