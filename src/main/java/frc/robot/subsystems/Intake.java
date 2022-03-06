// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static TalonSRX left_eye;
  private static TalonSRX right_eye;

  private static VictorSPX left_intake;
  private static VictorSPX right_intake;
  private static Intake instance;

  private PIDController left_eye_pid;
  private PIDController right_eye_pid;

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

    left_eye.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    right_eye.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    left_eye.setSelectedSensorPosition(0);
    right_eye.setSelectedSensorPosition(0);

    left_eye_pid = new PIDController(Constants.INTAKE_KP, Constants.INTAKE_KI, Constants.INTAKE_KD);
    left_eye_pid.setTolerance(Constants.INTAKE_TOLERANCE);
    right_eye_pid = new PIDController(Constants.INTAKE_KP, Constants.INTAKE_KI, Constants.INTAKE_KD);
    right_eye_pid.setTolerance(Constants.INTAKE_TOLERANCE);
  }

  public void setRight(double voltage) {
    right_intake.set(ControlMode.PercentOutput, voltage);
  }

  public void setLeft(double voltage) {
    left_intake.set(ControlMode.PercentOutput, voltage);
  }

  public void lowerPullies() {
    left_eye_pid.setSetpoint(Constants.EYE_DOWN);
    right_eye_pid.setSetpoint(-0.6);
  }

  public void raisePullies() {
    left_eye_pid.setSetpoint(Constants.EYE_UP);
    right_eye_pid.setSetpoint(Constants.EYE_UP);
  }

  public boolean isLeftAtSetpoint(){
    if(left_eye_pid.getSetpoint() == Constants.EYE_UP && Math.abs(left_eye.getMotorOutputPercent()) > 0.4) {
      if(Math.abs(left_eye.getSelectedSensorVelocity()) < 0.01) return true;
    }
    return left_eye_pid.atSetpoint();
  }

  public boolean isRightAtSetpoint(){
    SmartDashboard.putNumber("ri", right_eye.getMotorOutputPercent());
    if(right_eye_pid.getSetpoint() == Constants.EYE_UP && Math.abs(right_eye.getMotorOutputPercent()) > 0.4) {
      if(Math.abs(right_eye.getSelectedSensorVelocity()) < 0.01) return true;
    }
    return right_eye_pid.atSetpoint();
  }

  public void setEyeLeft(double s) {
    left_eye.set(ControlMode.PercentOutput, MathUtil.clamp(s, -Constants.EYE_SPEED, Constants.EYE_SPEED));
  }

  public void setEyeRight(double s) {
    right_eye.set(ControlMode.PercentOutput, MathUtil.clamp(s, -Constants.EYE_SPEED, Constants.EYE_SPEED));
  }

  public void resetLeftEye(){
    left_eye.setSelectedSensorPosition(0);
  }

  public void resetRightEye(){
    right_eye.setSelectedSensorPosition(0);
  }

  public void leftPID() {
    setEyeLeft(left_eye_pid.calculate(Constants.LEFT_EYE_DPP * left_eye.getSelectedSensorPosition()));
  }

  public void rightPID() {
    setEyeRight(right_eye_pid.calculate(Constants.RIGHT_EYE_DPP * right_eye.getSelectedSensorPosition()));
  }

  public void stopAll() {
    left_eye.set(ControlMode.PercentOutput, 0);
    right_eye.set(ControlMode.PercentOutput, 0);
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
