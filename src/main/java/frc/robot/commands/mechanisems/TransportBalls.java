package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

public class TransportBalls extends CommandBase {
  private Transport transport;
  private Shooter shooter;

  private boolean inwords;
  private double time;
  private double start_time;
  
  public TransportBalls(boolean inwords) {
    transport = Transport.getInstance();
    addRequirements(transport);

    if(!inwords){
      shooter = Shooter.getInstance();
      addRequirements(shooter);
    }

    this.inwords = inwords;
    this.time = Constants.NO_TIME;
  }

  public TransportBalls(boolean inwords, double time) {
    transport = Transport.getInstance();
    addRequirements(transport);

    if(!inwords){
      shooter = Shooter.getInstance();
      addRequirements(shooter);
    }

    this.inwords = inwords;
    this.time = time;
  }

  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
    if(inwords) transport.transportInwards(Constants.TRANSPORT_SPEED);
    else {
      transport.transportOutwards(Constants.TRANSPORT_SPEED);
      shooter.setSpeed(-10);
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    transport.stopAll();
    if(!inwords) shooter.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false;
  }
}
