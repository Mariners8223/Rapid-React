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

    addRequirements(transport, shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    transport.transportOutwards(0.3);
    shooter.setSpeed(Constants.SHOOTER_SPEED);
    transport.transportInwards(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.getChasisButton(Constants.SHOOT_FIRE_BUTTON)) {transport.transportInwards(Constants.TRANSPORT_SPEED);}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    transport.transportInwards(0);
    shooter.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
