package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  public static Joystick controller = new Joystick(Constants.DRIVE_JOYSTICK);

  public static SimpleMatrix getDriveDirection() {
    double x = controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    double[][] joystick_value_arr = {{x}, {y}};
    SimpleMatrix joystick_value = new SimpleMatrix(joystick_value_arr);
    return joystick_value;
  }

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getAutonomousCommand(){
    return null;
  }
}
