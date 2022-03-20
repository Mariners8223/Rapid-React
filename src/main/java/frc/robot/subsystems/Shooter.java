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
    shooter.config_kF(0, Constants.SHOOTER_KF);
    shooter.config_kP(0, Constants.SHOOTER_KP);
    shooter.config_kI(0, Constants.SHOOTER_KI);
    shooter.config_kD(0, Constants.SHOOTER_KD);
  }

  public void setSpeed(double rps) {
    shooter.set(ControlMode.Velocity, rps / Constants.ENCODERS_SPEED_TO_RPS);
  }

  public double getSpeed() {
    return shooter.getSelectedSensorVelocity() * Constants.ENCODERS_SPEED_TO_RPS;
  }

  public boolean atSetpoint(){
    return Math.abs((shooter.getSelectedSensorVelocity() - shooter.getClosedLoopTarget()) * Constants.ENCODERS_SPEED_TO_RPS) < Constants.SHOOTER_TOLERANCE;
  }

  public static Shooter getInstance() {
    if (instance == null)
      instance = new Shooter();
    return instance;
  }
}
