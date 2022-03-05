// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  private Climber climber;

  private boolean up;

  public Climb(boolean up) {
    climber = Climber.getInstance();
    addRequirements(climber);

    this.up = up;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(up) climber.setSpeed(Constants.CLIMBER_SPEED);
    else climber.setSpeed(-Constants.CLIMBER_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
