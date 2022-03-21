package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static TalonSRX left_eye;
  private static TalonSRX right_eye;

  private static VictorSPX left_intake;
  private static VictorSPX right_intake;
  private static Intake instance;

  private Intake() {
    left_intake = new VictorSPX(Constants.INTAKE_LEFT);
    right_intake = new VictorSPX(Constants.INTAKE_RIGHT);
    left_eye =  new TalonSRX(Constants.EYE_LEFT);
    right_eye = new TalonSRX(Constants.EYE_RIGHT);

    left_eye.setInverted(Constants.LEFT_PULLIE_INVERTED);
    right_eye.setInverted(Constants.RIGHT_PULLIE_INVERTED);
    right_intake.setInverted(Constants.RIGHT_INTAKE_INVERTED);
    left_intake.setInverted(Constants.LEFT_INTAKE_INVERTED);

    left_eye.setNeutralMode(NeutralMode.Brake);
    left_eye.configPeakOutputForward(Constants.EYE_SPEED, 0);
    right_eye.setNeutralMode(NeutralMode.Brake);
    right_eye.configPeakOutputForward(Constants.EYE_SPEED, 0);

    left_eye.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    right_eye.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    left_eye.setSelectedSensorPosition(0);
    right_eye.setSelectedSensorPosition(0);

    left_eye.config_kP(0, Constants.INTAKE_LEFT_KP);
    left_eye.config_kI(0, Constants.INTAKE_LEFT_KI);
    left_eye.config_kD(0, Constants.INTAKE_LEFT_KD);

    right_eye.config_kP(0, Constants.INTAKE_RIGHT_KP);
    right_eye.config_kI(0, Constants.INTAKE_RIGHT_KI);
    right_eye.config_kD(0, Constants.INTAKE_RIGHT_KD);
  }

  public void setRight(double voltage) {
    right_intake.set(ControlMode.PercentOutput, voltage);
  }

  public void setLeft(double voltage) {
    left_intake.set(ControlMode.PercentOutput, voltage);
  }

  public void lowerPullies() {
    left_eye.set(ControlMode.Position, Constants.EYE_DOWN / Constants.LEFT_EYE_DPP);
    right_eye.set(ControlMode.Position, Constants.EYE_DOWN / Constants.LEFT_EYE_DPP);
  }

  public void raisePullies() {
    left_eye.set(ControlMode.Position, Constants.EYE_UP / Constants.LEFT_EYE_DPP);
    right_eye.set(ControlMode.Position, Constants.EYE_UP / Constants.LEFT_EYE_DPP);
  }

  public boolean leftAtSetpoint() {
    SmartDashboard.putNumber("l", left_eye.getSelectedSensorPosition() * Constants.LEFT_EYE_DPP);
    return Math.abs((left_eye.getSelectedSensorPosition() - left_eye.getClosedLoopTarget()) * Constants.LEFT_EYE_DPP) < Constants.INTAKE_TOLERANCE;
  }

  public boolean rightAtSetpoint() {
    SmartDashboard.putNumber("r", right_eye.getSelectedSensorPosition() * Constants.RIGHT_EYE_DPP);
    return Math.abs((right_eye.getSelectedSensorPosition() - right_eye.getClosedLoopTarget()) * Constants.RIGHT_EYE_DPP) < Constants.INTAKE_TOLERANCE;
  }

  public void resetLeftEye(){
    left_eye.setSelectedSensorPosition(0);
  }

  public void resetRightEye(){
    right_eye.setSelectedSensorPosition(0);
  }

  public void stopCollectors() {
    left_intake.set(ControlMode.PercentOutput, 0);
    right_intake.set(ControlMode.PercentOutput, 0);
  }


  //Singleton instance.
  public static Intake getInstance() {
    if (instance == null)
      instance = new Intake();
    return instance;
  }
}
