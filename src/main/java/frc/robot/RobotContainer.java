package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.IntakeButtons;
import frc.robot.commands.ShootCycle;

import frc.robot.commands.PathFollower;

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);
  private static JoystickButton pullies_raise_button = new JoystickButton(chasis_controller, Constants.RAISE_PULLIES_BUTTON);
  private static JoystickButton pullies_lower_button = new JoystickButton(chasis_controller, Constants.LOWER_PULLIES_BUTTON);
  private static JoystickButton intake_left_button = new JoystickButton(chasis_controller, Constants.INTAKE_LEFT_BUTTON);
  private static JoystickButton intake_right_button = new JoystickButton(chasis_controller, Constants.INTAKE_RIGHT_BUTTON);
  private static JoystickButton shoot_start = new JoystickButton(limb_controller, Constants.SHOOT_COMMAND_BUTTON);
  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    pullies_raise_button.whileHeld(new IntakeButtons(Constants.RAISE_PULLIES_BUTTON, Constants.NO_TIME));
    pullies_lower_button.whileHeld(new IntakeButtons(Constants.LOWER_PULLIES_BUTTON, Constants.NO_TIME));
    intake_left_button.whileHeld(new IntakeButtons(Constants.INTAKE_LEFT_BUTTON, true, Constants.NO_TIME));
    intake_right_button.whileHeld(new IntakeButtons(Constants.INTAKE_RIGHT_BUTTON, false, Constants.NO_TIME));
    shoot_start.toggleWhenPressed(new ShootCycle(Constants.NO_TIME));
  }

  public Command getAutonomousCommand(){
    int discret_factor = 30;
    SimpleMatrix[] path = new SimpleMatrix[discret_factor];
    for(int i = 0; i < discret_factor; i++){
      double x = 2.0 * (double)i/(double)discret_factor;
      double[][] pos = {{1.16 * x}, {Math.sqrt(Math.abs(2.0 * x - x * x))}};
      path[i] = new SimpleMatrix(pos);
    }
    return new PathFollower(path);
  }

  public static boolean getChasisButton(int button) {return chasis_controller.getRawButton(button);}

  public static boolean getArmsButton(int button) {return limb_controller.getRawButton(button);}

  public static double getArmsAxis(int Axis) {return limb_controller.getRawAxis(Axis);}
  
  public static double getChasisAxis(int Axis) {return chasis_controller.getRawAxis(Axis);}

  
  public static SimpleMatrix getDriveDirection() {
    double x = chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    double[][] joystick_value_arr = {{x}, {y}};
    SimpleMatrix joystick_value = new SimpleMatrix(joystick_value_arr);
    return joystick_value;
  }

  public static double getDriveAngle() {
    double x = chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_X);
    double y = -chasis_controller.getRawAxis(Constants.DRIVE_DIRECTION_Y); //Inverted because joystick is inverted.
    if(Math.abs(x) < Constants.ROTATION_DEAD_BAND && Math.abs(y) < Constants.ROTATION_DEAD_BAND) return 361;
    double angle = Math.atan2(x, Math.abs(y));
    return Math.toDegrees(angle);
  }

  public static double getDriveRotation() {
    double s = RobotContainer.chasis_controller.getRawAxis(Constants.DRIVE_ROTATION);
    if(Math.abs(s) < Constants.ROTATION_DEAD_BAND) return 0;
    return Constants.ROTATION_SPEED * s;
  }
}