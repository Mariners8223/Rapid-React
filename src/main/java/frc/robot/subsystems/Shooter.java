// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static TalonFX shooter;

  private static Shooter instance;

  private Shooter() {
    shooter = new TalonFX(Constants.SHOOTER);
    shooter.setInverted(Constants.SHOOTER_INVERTED);
  }

  public void setSpeed(double voltage) {
    shooter.set(ControlMode.PercentOutput, voltage);
  }

  public static Shooter getInstance() {
    if (instance == null)
      instance = new Shooter();
    return instance;
  }
}
