package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private TalonFX left_front;
  private TalonFX left_back;
  private TalonFX right_front;
  private TalonFX right_back;

  private AHRS navx;
  private static Chassis instance;

  private Chassis() {
    left_front = new TalonFX(Constants.LEFT_FRONT);
    left_back = new TalonFX(Constants.LEFT_BACK);
    right_front = new TalonFX(Constants.RIGHT_FRONT);
    right_back = new TalonFX(Constants.RIGHT_BACK);

    navx = new AHRS();
    navx.calibrate();

    configMotor(left_front, Constants.LEFT_FRONT_INVERTED);
    configMotor(left_back, Constants.LEFT_BACK_INVERTED);
    configMotor(right_front, Constants.RIGHT_FRONT_INVERTED);
    configMotor(right_back, Constants.RIGHT_BACK_INVERTED);
  }
  
  // Singleton instance
  public static Chassis getInstance() {
    if (instance == null)
      instance = new Chassis();
    return instance; 
  }
  
  public void setSpeed(SimpleMatrix direction, double r, SimpleMatrix driveMatrix) {
    SimpleMatrix motors_value = driveMatrix.mult(direction);
    setMotorsSpeed(motors_value.get(1, 0) + r, motors_value.get(0, 0) - r,
                   motors_value.get(0, 0) + r, motors_value.get(1, 0) - r);
  }

  public void setMotorsSpeed(double lf, double rf, double lb, double rb)
  {
    left_front.set(ControlMode.PercentOutput, deadBandOutput(lf) * Constants.CHASSIS_MULTIPLIE);
    right_front.set(ControlMode.PercentOutput, deadBandOutput(rf) * Constants.CHASSIS_MULTIPLIE);
    left_back.set(ControlMode.PercentOutput, deadBandOutput(lb) * Constants.CHASSIS_MULTIPLIE);
    right_back.set(ControlMode.PercentOutput, deadBandOutput(rb) * Constants.CHASSIS_MULTIPLIE);
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
  }

  private void configMotor(TalonFX motor, boolean isInverted) {
    motor.configFactoryDefault();
    motor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);		
		motor.configPeakOutputForward(Constants.CHASSIS_CLAMP, 0);
		motor.configPeakOutputReverse(-Constants.CHASSIS_CLAMP, 0);
    motor.setNeutralMode(NeutralMode.Brake);
    if(isInverted) motor.setInverted(TalonFXInvertType.Clockwise);
    else motor.setInverted(TalonFXInvertType.CounterClockwise);
  }

  private double deadBandOutput(double s){
    if(Math.abs(s) < Constants.CHASSIS_DEAD_BAND) return 0;
    return s;
  }
}
