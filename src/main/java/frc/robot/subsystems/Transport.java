// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {
  private static TalonSRX transport_bottom;
  private static TalonSRX transport_top;


  private static Transport instance;


  private Transport() {
    transport_bottom = new TalonSRX(Constants.TRANSPORT_BOTTOM);
    transport_top = new TalonSRX(Constants.TRANSPORT_TOP);
  }


  public void transportInwards(double voltage) {
    transport_top.set(ControlMode.PercentOutput, voltage);
    transport_bottom.set(ControlMode.Follower, transport_top.getDeviceID());
  }

  public void transportOutwards(double voltage) {
    transport_top.set(ControlMode.PercentOutput, -voltage);
    transport_bottom.set(ControlMode.Follower, transport_top.getDeviceID());
  }


  public static Transport getInstance() {
    if (instance == null) 
      instance = new Transport();
    return instance;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
