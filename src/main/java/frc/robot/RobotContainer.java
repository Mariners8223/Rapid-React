package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.toggleIntake; 

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  // private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);
  private static JoystickButton pullies_button = new JoystickButton(chasis_controller, Constants.TOGGLE_PULLIES_BUTTON);


  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    pullies_button.whenPressed(new toggleIntake());
  }

  public Command getAutonomousCommand(){
    return null;
  }

  public static boolean intakeOutCheck() {
    if (chasis_controller.getRawAxis(Constants.INTAKE_OUT_AXIS) > 0.1) return true;
    return false;
  }

  public static boolean intakeInCheck() {
    if (chasis_controller.getRawButton(Constants.INTAKE_IN_BUTTON)) return true;
    return false;
  }
  
  public static SimpleMatrix getDriveDirection() {
    double x = chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    double[][] joystick_value_arr = {{x}, {y}};
    SimpleMatrix joystick_value = new SimpleMatrix(joystick_value_arr);
    return joystick_value;
  }

  public static double getDriveAngle() {
    double x = controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    if(Math.abs(x) < Constants.ROTATION_DEAD_BAND && Math.abs(y) < Constants.ROTATION_DEAD_BAND) return 361;
    double angle = Math.atan2(x, Math.abs(y));
    return Math.toDegrees(angle);
  }

  public static double getDriveRotation() {
    double s = RobotContainer.controller.getRawAxis(Constants.DRIVE_ROTATION);
    if(Math.abs(s) < Constants.ROTATION_DEAD_BAND) return 0;
    return Constants.ROTATION_SPEED * s;
  }
}