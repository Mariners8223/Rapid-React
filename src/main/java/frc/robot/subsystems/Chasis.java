package frc.robot.subsystems;


import java.lang.Math;
import java.security.PublicKey;

import com.ctre.phoenix.motorcontrol.ControlFrameEnhanced;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants;

public class Chasis extends SubsystemBase {
  private TalonFX LEFT_FRONT;
  private TalonFX LEFT_BACK;
  private TalonFX RIGHT_FRONT;
  private TalonFX RIGHT_BACK;
  private static Chasis instance;

  private Chasis() {
    LEFT_FRONT = new TalonFX(Constants.LEFT_FRONT);
    LEFT_BACK = new TalonFX(Constants.LEFT_BACK);
    RIGHT_FRONT = new TalonFX(Constants.RIGHT_FRONT);
    RIGHT_BACK = new TalonFX(Constants.RIGHT_BACK);

    LEFT_FRONT.setNeutralMode(NeutralMode.Brake);
    LEFT_BACK.setNeutralMode(NeutralMode.Brake);
    RIGHT_FRONT.setNeutralMode(NeutralMode.Brake);
    RIGHT_BACK.setNeutralMode(NeutralMode.Brake);

    // resetPosition();
  }
  /**
   * Singleton function.
   * <br></br>
   * Checks whether an instance of the chasis exists, if not creates one.
   * @return Returns the instance of the chasis.
   */
  public static Chasis getInstance() {
    if (instance == null)
      instance = new Chasis();
    return instance; 
  }
  /**
   * Checks whether the given number is outside of the bounderis, if yes, returns the limit. Else returns the speed.
   * @param speed A double which needs to be within a certain limit.
   * @param max Maximum num of the limit.
   * @param min Minimum num of the limit.
   * @return Either limit (max-min) or the given input.
   */
  public double Clamp(double speed, double limit)
  {
    if (speed > limit) return limit;
    if (speed < -limit) return -limit;
    return speed;
  }
  
  /**
   * Sets the speed for the drivetrain, allows for steering, strafing etc etc.
   * <p>Works by using basic Tank movement (Adding Y to all wheels, adding RX to left and subtracting from right) however it was changed to fit this picture I used (https://imgur.com/a/fecunMR)
   * and finally adding / removing X from opposite wheels to strafe.</p>
   * <p>In order to make the drive work in a field oriented manner we take the the values of lx and ly, put them on a vector and rotate it by the gyro's angle. After which we take the deviation.</p>
   * @param lx X axis from the left joystick. (Axis 4)
   * @param ly Y axis from the left joystick. (Axis 0)
   * @param rx X axis from the right joystick. (Axis 1)
   * @param angle Current Gyro angle. Used for field oriented drive.
   */
  public void setSpeed(double lx, double ly, double rx) {
    rx = Clamp(rx, 1);
    double xSpeed = Clamp(lx, 1);
    double ySpeed = Clamp(ly, 1);
    correctDrive(ySpeed + xSpeed + rx, ySpeed - xSpeed + rx, ySpeed - xSpeed - rx, ySpeed + xSpeed - rx);
    
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

  public double[] getVeocity() {
    double LFVec = LEFT_FRONT.getSelectedSensorVelocity();
    double LBVec = LEFT_BACK.getSelectedSensorVelocity();
    double RFVec = RIGHT_FRONT.getSelectedSensorVelocity();
    double RBVec = RIGHT_BACK.getSelectedSensorVelocity();
    double[] VelocityArr= new double[]{LFVec, LBVec, RFVec, RBVec};
    return VelocityArr;
  }




  public void correctDrive(double LF, double LB, double RF, double RB)
  {
    LEFT_FRONT.set(ControlMode.PercentOutput, LF * Constants.MULTI);
    LEFT_BACK.set(ControlMode.PercentOutput, LB * Constants.MULTI);
    RIGHT_FRONT.set(ControlMode.PercentOutput, -RF * Constants.MULTI);
    RIGHT_BACK.set(ControlMode.PercentOutput, -RB * Constants.MULTI);
    SmartDashboard.putNumber("LF", LF);
    SmartDashboard.putNumber("LB", LB);
    SmartDashboard.putNumber("RF", RF);
    SmartDashboard.putNumber("RB", RB);
  }

  @Override
  public void periodic(){
    
    
  }
}
