package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

public class toggleIntake extends CommandBase {
  private static Intake intake;
  private static Transport transport;
  double startTime;
  public toggleIntake() {
    intake = Intake.getInstance();
    transport = Transport.getInstance();
    addRequirements(intake, transport);
  }

  
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    if (Timer.getFPGATimestamp() - startTime < 0.5) {
      intake.lowerPullies(Constants.PULLIES_SPEED);
    }
    SmartDashboard.putBoolean("Intake Down", true);
  }

  
  @Override
  public void execute() {
    if (RobotContainer.intakeInCheck()) {
      intake.intakeBalls(Constants.INTAKE_SPEED);
      transport.transportInwards(Constants.TRANSPORT_SPEED);
    } else if (RobotContainer.intakeInCheck()) intake.outtakeBalls(Constants.INTAKE_SPEED);
  }

  
  @Override
  public void end(boolean interrupted) {
    startTime = Timer.getFPGATimestamp();
    if (Timer.getFPGATimestamp() - startTime < 0.5) {
      intake.raisePullies(Constants.PULLIES_SPEED);
    }
    SmartDashboard.putBoolean("Intake Down", false);
  }

  
  @Override
  public boolean isFinished() {
    return false;
  }
}
