package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chasis;

public class DriveBase extends CommandBase {
  private Chasis chasis;
  private Joystick controller = new Joystick(0);
  private double ly; private double lx; private double rx;

  public DriveBase() {
    chasis = Chasis.getInstance();
    addRequirements(chasis);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    lx = controller.getRawAxis(0) * 1.1; //Multiplied to counteract imperfect strafing.
    ly = -controller.getRawAxis(1); //Inverted to reverse needed movements.
    rx = controller.getRawAxis(4);
    chasis.setSpeed(lx, ly, rx);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
