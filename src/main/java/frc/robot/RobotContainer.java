package frc.robot;
import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.PathFollower;

public class RobotContainer {
  private static Joystick controller = new Joystick(Constants.DRIVE_JOYSTICK);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {}

  public Command getAutonomousCommand(){
    int discret_factor = 30;
    SimpleMatrix[] path = new SimpleMatrix[discret_factor];
    for(int i = 0; i < discret_factor; i++){
      double x = (double)i/(double)discret_factor;
      double[][] pos = {{1.16 * x}, {Math.sqrt(Math.abs(1.0 * x - x * x))}};
      path[i] = new SimpleMatrix(pos);
    }
    return new PathFollower(path);
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