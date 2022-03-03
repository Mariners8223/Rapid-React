package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Transport;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private Transport transport;
  private Intake intake;

  private boolean left;
  private int button;
  private double time;
  private double start_time;
  
  public IntakeBalls(int button, boolean left, double time) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = left;
    this.time = time;
  }

  public IntakeBalls(int button, double time) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = false;
    this.time = time;
  }
  
  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();

    if (button == Constants.RAISE_PULLIES_ENUM) intake.raisePullies(Constants.PULLIES_SPEED);
    else if (button == Constants.LOWER_PULLIES_ENUM) intake.lowerPullies(Constants.PULLIES_SPEED);
    else if (left) {
      intake.setLeft(Constants.INTAKE_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    } else {
      intake.setRight(Constants.INTAKE_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    }
  }

  
  @Override
  public void execute() {}

  
  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
    transport.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    if(time == Constants.NO_TIME) return false;
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false; 
  }
}