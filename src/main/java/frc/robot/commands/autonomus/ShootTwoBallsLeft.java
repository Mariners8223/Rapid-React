// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootClose;

public class ShootTwoBallsLeft extends SequentialCommandGroup {
  public ShootTwoBallsLeft() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_LEFT_HUB_PATH), new ShootClose(5), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
