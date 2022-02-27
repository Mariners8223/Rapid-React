package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Transport;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class IntakeButtons extends CommandBase {
  private static Transport transport;
  private static Intake intake;
  private boolean left;
  private int button;
  public IntakeButtons(int button, boolean left) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = left;
  }

  public IntakeButtons(int button) {
    transport = Transport.getInstance();
    intake = Intake.getInstance();
    addRequirements(transport, intake);

    this.button = button;
    this.left = false;

  }
  
  @Override
  public void initialize() {
    if (button == Constants.RAISE_PULLIES_BUTTON) intake.raisePullies(Constants.PULLIES_SPEED);
    else if (button == Constants.LOWER_PULLIES_BUTTON) intake.lowerPullies(Constants.PULLIES_SPEED);
    else if (left) {
      intake.setLeft(Constants.INTAKE_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    } else {
      intake.setRight(Constants.INTAKE_SPEED);
      transport.transportOutwards(Constants.TRANSPORT_SPEED);      

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
    return false;
  }
}
