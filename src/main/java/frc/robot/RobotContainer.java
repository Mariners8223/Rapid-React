package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private static Joystick controller = new Joystick(Constants.DRIVE_JOYSTICK);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getAutonomousCommand(){
    return null;
  }

  
  public static SimpleMatrix getDriveDirection() {
    double x = controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    double[][] joystick_value_arr = {{x}, {y}};
    SimpleMatrix joystick_value = new SimpleMatrix(joystick_value_arr);
    return joystick_value;
  }

  public static double getDriveRotationDiff() {
    double s = RobotContainer.controller.getRawAxis(Constants.DRIVE_ROTATION);
    if(Math.abs(s) < Constants.ROTATION_DEAD_BAND) return 0;
    return Constants.ROTATION_SPEED * s;
  }
}