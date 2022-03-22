package frc.robot;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoBallDriver;
import frc.robot.commands.autonomus.OneBallLeft;
import frc.robot.commands.autonomus.OneBallRight;
import frc.robot.commands.autonomus.Rotate;
import frc.robot.commands.autonomus.ThreeBallsAuto;
import frc.robot.commands.autonomus.TwoBallLeftAuto;
import frc.robot.commands.drive.ResetAngle;
import frc.robot.commands.mechanisems.ClimbWithIntake;
import frc.robot.commands.mechanisems.CollectAndTransport;
import frc.robot.commands.mechanisems.RaiseIntake;
import frc.robot.commands.mechanisems.ShootClose;
import frc.robot.commands.mechanisems.TransportBalls;

public class RobotContainer {
  private static Joystick chasis_controller = new Joystick(Constants.DRIVE_JOYSTICK);
  private static Joystick limb_controller = new Joystick(Constants.ARM_JOYSTICK);

  private static JoystickButton intake_left_button = new JoystickButton(chasis_controller, Constants.INTAKE_LEFT_BUTTON);
  private static JoystickButton intake_right_button = new JoystickButton(chasis_controller, Constants.INTAKE_RIGHT_BUTTON);
  private static JoystickButton reset_angle = new JoystickButton(chasis_controller, Constants.RESET_ANGLE_BUTTON);
  private static JoystickButton rotate_to_origin = new JoystickButton(chasis_controller, Constants.ROTATE_TO_ORIGIN_BUTTON);
  private static JoystickButton collect_auto = new JoystickButton(chasis_controller, Constants.COLLECT_AUTO_BUTTON);

  private static JoystickButton climb_up = new JoystickButton(limb_controller, Constants.CLIMB_UP_BUTTON);
  private static JoystickButton climb_down = new JoystickButton(limb_controller, Constants.CLIMB_DOWN_BUTTON);
  private static JoystickButton shoot_close = new JoystickButton(limb_controller, Constants.SHOOT_CLOSE_BUTTON);
  private static JoystickButton transport_in = new JoystickButton(limb_controller, Constants.TRANSPORT_INWARDS_BUTTON);
  private static JoystickButton transport_out = new JoystickButton(limb_controller, Constants.TRANSPORT_OUTWARDS_BUTTON);

  private static SendableChooser<Command> autonomous_chooser = new SendableChooser<>();

  public RobotContainer() {
    configureButtonBindings();

    autonomous_chooser.setDefaultOption("one ball left", new OneBallLeft());
    autonomous_chooser.addOption("one ball right", new OneBallRight());
    autonomous_chooser.addOption("auto collect", new AutoBallDriver());
    autonomous_chooser.addOption("Two balls left", new TwoBallLeftAuto());
    autonomous_chooser.addOption("Three balls", new ThreeBallsAuto());
    SmartDashboard.putData(autonomous_chooser);
  }

  private void configureButtonBindings() {
    intake_left_button.whileHeld(new CollectAndTransport());
    intake_left_button.whenReleased(new RaiseIntake());
    intake_right_button.whileHeld(new CollectAndTransport());
    intake_right_button.whenReleased(new RaiseIntake());
    
    reset_angle.whenPressed(new ResetAngle());
    rotate_to_origin.whileHeld(new Rotate(0));
    collect_auto.whileHeld(new AutoBallDriver());

    climb_up.whileHeld(new ClimbWithIntake(true));
    climb_down.whileHeld(new ClimbWithIntake(false));
    shoot_close.whenPressed(new ShootClose(15));
    transport_in.whileHeld(new TransportBalls(true));
    transport_out.whileHeld(new TransportBalls(false));
  }

  public Command getAutonomousCommand(){
    return autonomous_chooser.getSelected();
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