package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class ShootCycle extends CommandBase {
  private Transport transport;
  private Shooter shooter;

  private double time;
  private double start_time;

  public ShootCycle(double time) {
    transport = Transport.getInstance();
    shooter = Shooter.getInstance();

    addRequirements(shooter, transport);

    this.time = time;
  }


  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
  }

  
  @Override
  public void execute() {
    if(time != Constants.NO_TIME) {
      shooter.setSpeed(0.4);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
    else {
      shooter.setSpeed(Math.abs(RobotContainer.getArmsAxis(Constants.SHOOT_TRIGGER)) * Constants.SHOOTER_SPEED);
      if (RobotContainer.getArmsButton(Constants.TRANSPORT_OUTWARDS_BUTTON)) transport.transportOutwards(Constants.TRANSPORT_SPEED);
      else transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
  }

  
  @Override
  public void end(boolean interrupted) {
    transport.transportInwards(0);
    shooter.setSpeed(0);
  }

  
  @Override
  public boolean isFinished() {
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false; 
  }
}
