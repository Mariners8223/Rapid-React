// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Transport;

public class TransportBalls extends CommandBase {
  private Transport transport;

  private boolean inwords;
  private double time;
  private double start_time;
  
  public TransportBalls(boolean inwords, double time) {
    transport = Transport.getInstance();
    addRequirements(transport);

    this.inwords = inwords;
    this.time = time;
  }

  @Override
  public void initialize() {
    start_time = Timer.getFPGATimestamp();
    if(inwords) transport.transportInwards(Constants.TRANSPORT_SPEED);
    else transport.transportOutwards(Constants.TRANSPORT_SPEED);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    transport.stopAll();
  }

  @Override
  public boolean isFinished() {
    if(Timer.getFPGATimestamp() - start_time > time) return true;
    return false;
  }
}
