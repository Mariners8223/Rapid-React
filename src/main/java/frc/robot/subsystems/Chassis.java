package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private TalonFX LEFT_FRONT;
  private TalonFX LEFT_BACK;
  private TalonFX RIGHT_FRONT;
  private TalonFX RIGHT_BACK;

  private AHRS navx;
  private static Chassis instance;

  private Chassis() {
    LEFT_FRONT = new TalonFX(Constants.LEFT_FRONT);
    LEFT_BACK = new TalonFX(Constants.LEFT_BACK);
    RIGHT_FRONT = new TalonFX(Constants.RIGHT_FRONT);
    RIGHT_BACK = new TalonFX(Constants.RIGHT_BACK);

    navx = new AHRS();
    navx.calibrate();
    navx.reset();

    LEFT_FRONT.setNeutralMode(NeutralMode.Brake);
    LEFT_BACK.setNeutralMode(NeutralMode.Brake);
    RIGHT_FRONT.setNeutralMode(NeutralMode.Brake);
    RIGHT_BACK.setNeutralMode(NeutralMode.Brake);
  }
  
  /**
   * Singleton function.
   * <br></br>
   * Checks whether an instance of the chasis exists, if not creates one.
   * @return Returns the instance of the chasis.
   */
  public static Chassis getInstance() {
    if (instance == null)
      instance = new Chassis();
    return instance; 
  }
  
  /**
   * Sets the speed for the drivetrain, allows for steering, strafing etc etc.
   * <p>Works by using basic Tank movement (Adding Y to all wheels, adding RX to left and subtracting from right) however it was changed to fit this picture I used (https://imgur.com/a/fecunMR)
   * and finally adding / removing X from opposite wheels to strafe.</p>
   * <p>In order to make the drive work in a field oriented manner we take the the values of lx and ly, put them on a vector and rotate it by the gyro's angle. After which we take the deviation.</p>
   * @param x X axis from the left joystick. (Axis 4)
   * @param y Y axis from the left joystick. (Axis 0)
   * @param r X axis from the right joystick. (Axis 1)
   * @param driveMatrix matrix that represents the drive.
   */
  public void setSpeed(double x, double y, double r, SimpleMatrix driveMatrix) {
    double[][] joystick_value_arr = {{x}, {y}};
    SimpleMatrix joystick_value = new SimpleMatrix(joystick_value_arr);
    SimpleMatrix motors_value = driveMatrix.mult(joystick_value);
    correctDrive(motors_value.get(1, 0) + r, motors_value.get(0, 0) - r,
                 motors_value.get(0, 0) + r, motors_value.get(1, 0) - r);
  }

  /**
   * Stops the fuckign robot retard.
   */
  public void stopRobot() {
    LEFT_FRONT.set(ControlMode.Disabled, 0);
    LEFT_BACK.set(ControlMode.Disabled, 0);
    RIGHT_FRONT.set(ControlMode.Disabled, 0);
    RIGHT_BACK.set(ControlMode.Disabled, 0);
  }

  public void correctDrive(double LF, double RF, double LB, double RB)
  {
    LEFT_FRONT.set(ControlMode.PercentOutput, MathUtil.clamp(LF * Constants.MULTI, -Constants.MAX_CLAMP, Constants.MAX_CLAMP));
    LEFT_BACK.set(ControlMode.PercentOutput, MathUtil.clamp(LB * Constants.MULTI, -Constants.MAX_CLAMP, Constants.MAX_CLAMP));
    RIGHT_FRONT.set(ControlMode.PercentOutput, MathUtil.clamp(-RF * Constants.MULTI, -Constants.MAX_CLAMP, Constants.MAX_CLAMP));
    RIGHT_BACK.set(ControlMode.PercentOutput, MathUtil.clamp(-RB * Constants.MULTI, -Constants.MAX_CLAMP, Constants.MAX_CLAMP));
  }

  public double getAngle(){
    return navx.getAngle();
  }

  public SimpleMatrix rotationMatrix(double angle){
    double[][] rot = {
      {Math.cos(angle), Math.sin(angle)},
      {-Math.sin(angle), Math.cos(angle)}
    };
    return new SimpleMatrix(rot);
  }
}
