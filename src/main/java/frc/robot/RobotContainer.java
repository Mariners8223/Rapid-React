package frc.robot;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.autonomus.OneBallAuto;
import frc.robot.commands.autonomus.ThreeBallsAuto;
import frc.robot.commands.autonomus.TwoBallRightAuto;
import frc.robot.commands.drive.ResetAngle;
import frc.robot.commands.mechanisems.Climb;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.subsystems.Chassis;

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);

  private static JoystickButton pullies_raise_button = new JoystickButton(chasis_controller, Constants.RAISE_PULLIES_ENUM);
  private static JoystickButton pullies_lower_button = new JoystickButton(chasis_controller, Constants.LOWER_PULLIES_ENUM);
  private static JoystickButton intake_left_button = new JoystickButton(chasis_controller, Constants.INTAKE_LEFT_ENUM);
  private static JoystickButton intake_right_button = new JoystickButton(chasis_controller, Constants.INTAKE_RIGHT_ENUM);
  private static JoystickButton shoot_start = new JoystickButton(limb_controller, Constants.SHOOT_COMMAND_BUTTON);
  private static JoystickButton reset_angle = new JoystickButton(chasis_controller, 1);
  private static JoystickButton climb_up = new JoystickButton(limb_controller, Constants.CLIMB_UP_BUTTON);
  private static POVButton climb_down = new POVButton(limb_controller, Constants.CLIMB_DOWN_BUTTON);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    pullies_raise_button.whenPressed(new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME));
    pullies_lower_button.whenPressed(new IntakeBalls(Constants.LOWER_PULLIES_ENUM, Constants.NO_TIME));
    intake_left_button.whileHeld(new IntakeBalls(Constants.INTAKE_LEFT_ENUM, true, Constants.NO_TIME));
    intake_right_button.whileHeld(new IntakeBalls(Constants.INTAKE_RIGHT_ENUM, false, Constants.NO_TIME));
    shoot_start.toggleWhenPressed(new ShootCycle(Constants.NO_TIME));
    reset_angle.whenPressed(new ResetAngle());
    climb_up.whileHeld(new Climb(true));
    climb_down.whileHeld(new Climb(false));
  }

  public Command getAutonomousCommand(){
    return new ThreeBallsAuto();
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