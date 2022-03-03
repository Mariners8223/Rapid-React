// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static VictorSPX left_eye;
  private static VictorSPX right_eye;
  private static VictorSPX left_intake;
  private static VictorSPX right_intake;
  private static Intake instance;

  private Intake() {
    left_intake = new VictorSPX(Constants.INTAKE_LEFT);
    right_intake = new VictorSPX(Constants.INTAKE_RIGHT);
    left_eye =  new VictorSPX(Constants.EYE_LEFT);
    right_eye = new VictorSPX(Constants.EYE_RIGHT);

    left_eye.setInverted(Constants.LEFT_PULLIE_INVERTED);
    right_eye.setInverted(Constants.RIGHT_PULLIE_INVERTED);
    right_intake.setInverted(Constants.RIGHT_INTAKE_INVERTED);
    left_intake.setInverted(Constants.LEFT_INTAKE_INVERTED);
  }


  public void setRight(double voltage) {right_intake.set(ControlMode.PercentOutput, voltage);}

  public void setLeft(double voltage) {left_intake.set(ControlMode.PercentOutput, voltage);}

  public void lowerPullies(double voltage) {
    left_eye.set(ControlMode.PercentOutput, voltage);
    right_eye.set(ControlMode.PercentOutput, voltage);
  }

  public void raisePullies(double voltage) {
    left_eye.set(ControlMode.PercentOutput, voltage);
    right_eye.set(ControlMode.PercentOutput, voltage);
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