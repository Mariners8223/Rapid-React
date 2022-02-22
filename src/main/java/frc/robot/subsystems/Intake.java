// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static TalonSRX left_eye;
  private static TalonSRX right_eye;
  private static TalonSRX left_intake;
  private static TalonSRX right_intake;
  private static Intake instance;

  private Intake() {
    left_intake = new TalonSRX(Constants.INTAKE_LEFT);
    right_intake = new TalonSRX(Constants.INTAKE_RIGHT);
    left_eye =  new TalonSRX(Constants.EYE_LEFT);
    right_eye = new TalonSRX(Constants.EYE_RIGHT);

    right_eye.setInverted(Constants.EYE_RIGHT_INVERTED);
    right_intake.setInverted(Constants.INTAKE_RIGHT_INVERTED);
    left_eye.setInverted(Constants.EYE_LEFT_INVERTED);
    left_intake.setInverted(Constants.INTAKE_LEFT_INVERTED);
  }


  public void setRight(double voltage) {right_intake.set(ControlMode.PercentOutput, voltage);}

  public void setLeft(double voltage) {left_intake.set(ControlMode.PercentOutput, voltage);}

  public void lowerPullies(double voltage) {
    left_eye.set(ControlMode.PercentOutput, voltage);
    right_eye.set(ControlMode.Follower, left_eye.getDeviceID());
  }

  public void raisePullies(double voltage) {
    left_eye.set(ControlMode.PercentOutput, voltage);
    right_eye.set(ControlMode.Follower, left_eye.getDeviceID());
  }

  public void intakeBalls(double voltage) {
    setRight(voltage);
    setLeft(voltage);
  }

  public void outtakeBalls(double voltage) {
    setRight(-voltage);
    setLeft(-voltage);
  }


  //Singleton instance.
  public static Intake getInstance() {
    if (instance == null)
      instance = new Intake();
    return instance;
  }

  @Override
  public void periodic() {

  }
}
