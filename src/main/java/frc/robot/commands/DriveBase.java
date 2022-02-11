package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chasis;

public class DriveBase extends CommandBase {
  private Chasis chasis;
  private double ly; private double lx; private double rx;

  public DriveBase() {
    chasis = Chasis.getInstance();
    addRequirements(chasis);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    lx = RobotContainer.controller.getRawAxis(0); //Multiplied to counteract imperfect strafing.
    ly = -RobotContainer.controller.getRawAxis(1); //Inverted to reverse needed movements.
    rx = RobotContainer.controller.getRawAxis(4);
    chasis.setSpeed(lx, ly, rx, Constants.BASE_DRIVE);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
