package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class ShootCycle extends CommandBase {
  private static Transport transport;
  private static Shooter shooter;
  public ShootCycle() {
    transport = Transport.getInstance();
    shooter = Shooter.getInstance();

    addRequirements(shooter);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    shooter.setSpeed(Math.abs(RobotContainer.getArmsAxis(1)) * 0.5);
    if (RobotContainer.getArmsButton(3)) {transport.transportInwards(Constants.TRANSPORT_SPEED);}
    else if (RobotContainer.getArmsButton(2)) {transport.transportOutwards(Constants.TRANSPORT_SPEED);}
    else {transport.stopAll();}
  }

  
  @Override
  public void end(boolean interrupted) {
    transport.transportInwards(0);
    shooter.setSpeed(0);
  }

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
