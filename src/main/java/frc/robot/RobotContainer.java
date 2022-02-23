package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeLeft;
import frc.robot.commands.IntakeRight;
import frc.robot.commands.LowerIntake;
import frc.robot.commands.RaiseIntake;

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  // private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);
  private static JoystickButton pullies_raise_button = new JoystickButton(chasis_controller, Constants.RAISE_PULLIES_BUTTON);
  private static JoystickButton pullies_lower_button = new JoystickButton(chasis_controller, Constants.LOWER_PULLIES_BUTTON);
  private static JoystickButton intake_left_button = new JoystickButton(chasis_controller, Constants.INTAKE_LEFT_BUTTON);
  private static JoystickButton intake_right_button = new JoystickButton(chasis_controller, Constants.INTAKE_RIGHT_BUTTON);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    pullies_raise_button.whileHeld(new RaiseIntake());
    pullies_lower_button.whileHeld(new LowerIntake());
    intake_left_button.whileHeld(new IntakeLeft());
    intake_right_button.whileHeld(new IntakeRight());
  }

  public Command getAutonomousCommand(){
    return null;
  }

  public static boolean getChasisButton(int button) {
    return chasis_controller.getRawButton(button);
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