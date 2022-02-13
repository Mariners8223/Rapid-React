package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private TalonFX left_front;
  private TalonFX left_back;
  private TalonFX right_front;
  private TalonFX right_back;

  private AHRS navx;
  private static Chassis instance;

  PIDController anglePID;

  private Chassis() {
    left_front = new TalonFX(Constants.LEFT_FRONT);
    left_back = new TalonFX(Constants.LEFT_BACK);
    right_front = new TalonFX(Constants.RIGHT_FRONT);
    right_back = new TalonFX(Constants.RIGHT_BACK);

    navx = new AHRS();
    navx.calibrate();

    anglePID = new PIDController(Constants.ANGLE_KP, Constants.ANGLE_KI, Constants.ANGLE_KD);
    anglePID.enableContinuousInput(0, 360);

    configMotor(left_front, Constants.LEFT_FRONT_INVERTED);
    configMotor(left_back, Constants.LEFT_BACK_INVERTED);
    configMotor(right_front, Constants.RIGHT_FRONT_INVERTED);
    configMotor(right_back, Constants.RIGHT_BACK_INVERTED);
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
  public void setSpeed(SimpleMatrix direction, double r, SimpleMatrix driveMatrix) {
    SimpleMatrix motors_value = driveMatrix.mult(direction);
    setMotorsSpeed(motors_value.get(1, 0) + r, motors_value.get(0, 0) - r,
                 motors_value.get(0, 0) + r, motors_value.get(1, 0) - r);
  }

  /**
   * Stops the fuckign robot retard.
   */
  public void stopRobot() {
    left_front.set(ControlMode.Disabled, 0);
    left_back.set(ControlMode.Disabled, 0);
    right_front.set(ControlMode.Disabled, 0);
    right_back.set(ControlMode.Disabled, 0);
  }

  public void setMotorsSpeed(double lf, double rf, double lb, double rb)
  {
    left_front.set(ControlMode.PercentOutput, lf * Constants.CHASSIS_MULTIPLIE);
    right_front.set(ControlMode.PercentOutput, rf * Constants.CHASSIS_MULTIPLIE);
    left_back.set(ControlMode.PercentOutput, lb * Constants.CHASSIS_MULTIPLIE);
    right_back.set(ControlMode.PercentOutput, rb * Constants.CHASSIS_MULTIPLIE);
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

  public void resetAngle(){
    navx.reset();
    anglePID.reset();
  }

  private void configMotor(TalonFX motor, boolean isInverted) {
    motor.configFactoryDefault();
		motor.configNeutralDeadband(Constants.DEAD_BAND);
    motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);							
		motor.configNominalOutputForward(0, 0);
		motor.configNominalOutputReverse(0, 0);
		motor.configPeakOutputForward(Constants.CHASSIS_CLAMP, 0);
		motor.configPeakOutputReverse(-Constants.CHASSIS_CLAMP, 0);
    motor.setNeutralMode(NeutralMode.Brake);
    if(isInverted) motor.setInverted(TalonFXInvertType.Clockwise);
    else motor.setInverted(TalonFXInvertType.CounterClockwise);
  }

  public double getRotationPID(double target){
    return anglePID.calculate(getAngle(), target);
  }
}
