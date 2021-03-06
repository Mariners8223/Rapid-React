package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class ShootCycle extends CommandBase {
  private Transport transport;
  private Shooter shooter;

  private double time;
  private double start_time;
  private double speed;
  private boolean first_sp;

  public ShootCycle(double time, double speed) {
    transport = Transport.getInstance();
    shooter = Shooter.getInstance();

    addRequirements(shooter, transport);

    this.time = time;
    this.speed = speed;
  }

  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
    first_sp = false;
    shooter.enableIntegral(false);
  }

  
  @Override
  public void execute() {
    SmartDashboard.putNumber("shooter speed", shooter.getSpeed());
    shooter.setSpeed(speed);
    if(!first_sp && shooter.atSetpoint()){
      first_sp = true;
      shooter.resetIntegral();
      shooter.enableIntegral(true);
    }
    if(shooter.atSetpoint()) transport.transportInwards(Constants.TRANSPORT_SPEED);
    else transport.stopAll();
  }

  
  @Override
  public void end(boolean interrupted) {
    transport.transportInwards(0);
    shooter.stop();
  }

  
  @Override
  public boolean isFinished() {
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false; 
  }
}