// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class ShootTwoBallsLeft extends SequentialCommandGroup {
  public ShootTwoBallsLeft() {
    addCommands(new Rotate(45), new PathFollower(Constants.BALL_BEHIND_LEFT_HUB_PATH), new TransportBalls(false, 0.5), new ShootCycle(3, 0.39), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
