package frc.robot.commands;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chasis;

public class FieldOrientedDrive extends CommandBase {
  private Chasis chasis;
  private double y; private double x; private double r;

  public FieldOrientedDrive() {
    chasis = Chasis.getInstance();
    addRequirements(chasis);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    x = RobotContainer.controller.getRawAxis(0); //Multiplied to counteract imperfect strafing.
    y = -RobotContainer.controller.getRawAxis(1); //Inverted to reverse needed movements.
    r = RobotContainer.controller.getRawAxis(4);

    SimpleMatrix robotOrientationMatrix = chasis.rotationMatrix(Math.toRadians(-chasis.getAngle()));
    SimpleMatrix fodMatrix = Constants.BASE_DRIVE.mult(robotOrientationMatrix);
    chasis.setSpeed(x, y, r, fodMatrix);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
