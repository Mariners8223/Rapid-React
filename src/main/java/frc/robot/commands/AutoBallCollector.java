package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.RaspberryPi;

public class AutoBallCollector extends CommandBase {
  private Chassis chassis;
  private RaspberryPi rPi;

  private double distance;
  private double angle;
  private double[][] direction_arr;
  private SimpleMatrix direction;

  private SimpleMatrix right_matrix;

  public AutoBallCollector() {
    chassis = Chassis.getInstance();
    rPi = RaspberryPi.getInstance();
    addRequirements(chassis);
    addRequirements(rPi);

    double[][] arr = {{0, -1},
                      {1, 0}};
    right_matrix = new SimpleMatrix(arr).mult(Constants.BASE_DRIVE);
  }

  @Override
  public void initialize() {
    distance = 0;
    angle = 0;
    direction_arr = new double[][] {{0}, {0}};
    direction = new SimpleMatrix(direction_arr);

    chassis.resetAngle();
  }

  @Override
  public void execute() {
    distance = rPi.getDistance();
    angle = rPi.getAngle();
    direction_arr = new double[][] {{distance * Math.cos(angle)}, {distance * Math.sin(angle)}};
    direction = new SimpleMatrix(direction_arr);

    chassis.setSpeed(direction.scale(0.45 / direction.normF()), chassis.getRotationPID(0), right_matrix);
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
