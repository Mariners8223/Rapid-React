package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  private Climber climber;

  private boolean up;

  public Climb(boolean up) {
    climber = Climber.getInstance();
    addRequirements(climber);

    this.up = up;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("climber speed", climber.getSpeed());
    if(up) climber.setSpeed(Constants.CLIMBER_SPEED);
    else climber.setSpeed(-Constants.CLIMBER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
    SmartDashboard.putNumber("climber speed", 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
