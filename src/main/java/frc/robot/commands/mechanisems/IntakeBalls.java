package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeBalls extends CommandBase {
  private Intake intake;

  private boolean inwords;
  private double time;

  private double start_time;

  public IntakeBalls(boolean inwords) {
    intake = Intake.getInstance();
    addRequirements(intake);

    this.inwords = inwords;
    this.time = Constants.NO_TIME;
  }
  
  public IntakeBalls(boolean inwords, double time) {
    addRequirements(intake);

    this.inwords = inwords;
    this.time = time;
  }

  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
    if(inwords) {
      intake.setLeft(Constants.INTAKE_LEFT_SPEED);
      intake.setRight(Constants.INTAKE_RIGHT_SPEED);
    }
    else {
      intake.setLeft(-Constants.INTAKE_LEFT_SPEED);
      intake.setRight(-Constants.INTAKE_RIGHT_SPEED);
    }
  }

  
  @Override
  public void execute() {
  }

  
  @Override
  public void end(boolean interrupted) {
    intake.stopAll();
  }

  
  @Override
  public boolean isFinished() {
    if(time != Constants.NO_TIME) return (Timer.getFPGATimestamp() - start_time) > time;
    return false; 
  }
}
