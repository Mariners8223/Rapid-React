package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.drive.FieldOrientedDrive;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Intake;

public class Robot extends TimedRobot {
  private Command autonomousCommand;
  private RobotContainer robotContainer;

  private final Chassis chassis = Chassis.getInstance();
  private final FieldOrientedDrive drive = new FieldOrientedDrive();

  private final Intake intake = Intake.getInstance();

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
    chassis.resetAngle();
    intake.resetLeftEye();
    intake.resetRightEye();
    CommandScheduler.getInstance().setDefaultCommand(chassis, drive);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    chassis.resetAngle();
    intake.resetLeftEye();
    intake.resetRightEye();
    autonomousCommand = robotContainer.getAutonomousCommand();

    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    intake.resetLeftEye(); // need to remove
    intake.resetRightEye(); // need to remove
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    
  }

  @Override
  public void testPeriodic() {}
}
