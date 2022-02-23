package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

public class IntakeRight extends CommandBase {
  private static Intake intake;
  private static Transport transport;
  public IntakeRight() {
    intake = Intake.getInstance();
    transport = Transport.getInstance();
    addRequirements(intake, transport);
  }

  
  @Override
  public void initialize() {}

  
  @Override
  public void execute() {
    intake.setRight(Constants.INTAKE_SPEED);
    transport.transportInwards(Constants.TRANSPORT_SPEED);
  }

  
  @Override
  public void end(boolean interrupted) {}

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
