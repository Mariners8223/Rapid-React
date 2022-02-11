package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  public static Joystick controller = new Joystick(0);
  public RobotContainer() {
    configureButtonBindings();
  }
  private void configureButtonBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
