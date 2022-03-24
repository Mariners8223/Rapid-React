package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Transport;

public class Climb extends CommandBase {
  private Climber climber;
  private Transport transport;

  private boolean up;

  public Climb(boolean up) {
    climber = Climber.getInstance();
    transport = Transport.getInstance();
    addRequirements(climber, transport);

    this.up = up;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("climber speed", climber.getSpeed());
    transport.setUpper(0.5);
    if(up) climber.setSpeed(Constants.CLIMBER_SPEED);
    else climber.setSpeed(-Constants.CLIMBER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
    transport.stopAll();
    SmartDashboard.putNumber("climber speed", 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
