package frc.robot.commands.auto_ball_collector;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.RaspberryPi;

public class AutoBallDriver extends CommandBase {
  private Chassis chassis;
  private RaspberryPi rPi;

  private double angle;

  private boolean left;

  private SimpleMatrix left_direction;
  private SimpleMatrix right_direction;
  private SimpleMatrix last_direction;

  private double right_distance;
  private double left_distance;

  public AutoBallDriver() {
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

    SmartDashboard.putNumber("cx", last_direction.get(0,0));
    SmartDashboard.putNumber("cy", last_direction.get(1,0));

    if (left_distance == 0 && right_distance == 0) {
      //chassis.setSpeed(last_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE);
    } else {
      left = (left_distance > right_distance);

      if (left) {
        left_direction = left_direction.scale(0.4 / left_distance);
        SmartDashboard.putNumber("left direction x", left_direction.get(0,0));
        SmartDashboard.putNumber("left direction y", left_direction.get(1,0));
        chassis.setSpeed(left_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE);
        last_direction = left_direction.copy();
      } else {
        right_direction = right_direction.scale(0.4 / right_distance);
        SmartDashboard.putNumber("right direction x", right_direction.get(0,0));
        SmartDashboard.putNumber("right direction y", right_direction.get(1,0));
        chassis.setSpeed(right_direction, chassis.getRotationPID(angle), Constants.BASE_DRIVE);
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
