package frc.robot;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.PathFollower;
import frc.robot.commands.drive.ResetAngle;
import frc.robot.commands.mechanisems.ClimbWithIntake;
import frc.robot.commands.mechanisems.CollectBalls;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.ShootClose;

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);

  private static JoystickButton pullies_raise_button = new JoystickButton(chasis_controller, Constants.RAISE_PULLIES_ENUM);
  private static JoystickButton pullies_lower_button = new JoystickButton(chasis_controller, Constants.LOWER_PULLIES_ENUM);
  private static JoystickButton intake_left_button = new JoystickButton(chasis_controller, Constants.INTAKE_LEFT_ENUM);
  private static JoystickButton intake_right_button = new JoystickButton(chasis_controller, Constants.INTAKE_RIGHT_ENUM);
  private static JoystickButton reset_angle = new JoystickButton(chasis_controller, Constants.RESET_ANGLE_BUTTON);
  private static JoystickButton climb_up = new JoystickButton(limb_controller, Constants.CLIMB_UP_BUTTON);
  private static POVButton climb_down = new POVButton(limb_controller, Constants.CLIMB_DOWN_BUTTON);
  private static JoystickButton shoot_close = new JoystickButton(limb_controller, Constants.SHOOT_CLOSE_BUTTON);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    pullies_raise_button.whenPressed(new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME));
    pullies_lower_button.whenPressed(new IntakeBalls(Constants.LOWER_PULLIES_ENUM, Constants.NO_TIME));

    intake_left_button.whileHeld(new CollectBalls());
    intake_left_button.whenReleased(new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME));
    intake_right_button.whileHeld(new CollectBalls());
    intake_right_button.whenReleased(new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME));
    
    reset_angle.whenPressed(new ResetAngle());
    climb_up.whileHeld(new ClimbWithIntake(true));
    climb_down.whileHeld(new ClimbWithIntake(false));
    shoot_close.whenPressed(new ShootClose());
  }

  public Command getAutonomousCommand(){
    return new PathFollower(Constants.ONE_BALL_PATH);
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

  public static SimpleMatrix[] arrayToPath(double[][] path_arr) {
    SimpleMatrix[] path = new SimpleMatrix[path_arr.length];

    for(int i = 0; i < path_arr.length; i++){
      double[][] pos = {{path_arr[i][0]}, {path_arr[i][1]}};
      path[i] = new SimpleMatrix(pos);
    }

    return path;
  }

  public static boolean isBlue(){
    return DriverStation.getAlliance() == DriverStation.Alliance.Blue;
  }
}