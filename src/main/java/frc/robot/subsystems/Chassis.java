package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Chassis extends SubsystemBase {
  private static Chassis instance;
  
  private TalonFX left_front;
  private TalonFX left_back;
  private TalonFX right_front;
  private TalonFX right_back;

  private AHRS navx;

  private PIDController anglePID;

  private SimpleMatrix position;
  private double last_time_position;

  private Chassis() {
    left_front = new TalonFX(Constants.LEFT_FRONT);
    left_back = new TalonFX(Constants.LEFT_BACK);
    right_front = new TalonFX(Constants.RIGHT_FRONT);
    right_back = new TalonFX(Constants.RIGHT_BACK);

    configMotor(left_front, Constants.LEFT_FRONT_INVERTED);
    configMotor(left_back, Constants.LEFT_BACK_INVERTED);
    configMotor(right_front, Constants.RIGHT_FRONT_INVERTED);
    configMotor(right_back, Constants.RIGHT_BACK_INVERTED);

    navx = new AHRS();
    navx.calibrate();

    anglePID = new PIDController(Constants.ANGLE_KP, Constants.ANGLE_KI, Constants.ANGLE_KD);
    anglePID.enableContinuousInput(0, 360);

    position = Constants.ZERO_VECTOR;
    last_time_position = 0;
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

  public SimpleMatrix getFieldOrientedMatrix(){
    SimpleMatrix robotOrientationMatrix = rotationMatrix(Math.toRadians(-getAngle()));
    return Constants.BASE_DRIVE.mult(robotOrientationMatrix);
  }

  private SimpleMatrix rotationMatrix(double angle){
    double[][] rot = {
      {Math.cos(angle), Math.sin(angle)},
      {-Math.sin(angle), Math.cos(angle)}
    };
    return new SimpleMatrix(rot);
  }

  public void resetAngle(){
    navx.reset();
    anglePID.setSetpoint(0);
    anglePID.reset();
  }

  private void configMotor(TalonFX motor, boolean isInverted) {
    motor.configFactoryDefault();
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

  private double deadBandOutput(double s){
    if(Math.abs(s) < Constants.CHASSIS_DEAD_BAND) return 0;
    return s;
  }

  public void setSmoothRotation(boolean smooth) {
    if (smooth) anglePID.setD(0);
    else anglePID.setD(Constants.ANGLE_KD);
  }

  public void resetPosition() {
    position = Constants.ZERO_VECTOR;
    last_time_position = Timer.getFPGATimestamp();
  }

  public SimpleMatrix getVelocity() {
    double lf =  Constants.LEFT_FRONT_DPP * Constants.CHASSIS_VELOCITY_TIME_TO_SECONDS * left_front.getSelectedSensorVelocity();
    double rf = Constants.RIGHT_FRONT_DPP * Constants.CHASSIS_VELOCITY_TIME_TO_SECONDS * right_front.getSelectedSensorVelocity();
    double lb = Constants.LEFT_BACK_DPP * Constants.CHASSIS_VELOCITY_TIME_TO_SECONDS * left_back.getSelectedSensorVelocity();
    double rb = Constants.RIGHT_BACK_DPP * Constants.CHASSIS_VELOCITY_TIME_TO_SECONDS * right_back.getSelectedSensorVelocity();

    SmartDashboard.putNumber("lf", lf);
    SmartDashboard.putNumber("rf", rf);
    SmartDashboard.putNumber("lb", lb);
    SmartDashboard.putNumber("rb", rb);

    double e1 = (rf + lb);
    double e2 = (lf + rb);

    double[][] raw_velocity_arr = {{e1}, {e2}};

    return Constants.VELOCITY.mult(new SimpleMatrix(raw_velocity_arr));
  }

  public SimpleMatrix getPosition() {
    SimpleMatrix field_oriented_velocity = rotationMatrix(Math.toRadians(getAngle())).mult(getVelocity());

    SmartDashboard.putNumber("x vel", field_oriented_velocity.get(0, 0));
    SmartDashboard.putNumber("y vel", field_oriented_velocity.get(1, 0));

    double time = Timer.getFPGATimestamp();
    position = position.plus(field_oriented_velocity.scale(time - last_time_position));
    last_time_position = time;
    
    return position;
  }
}