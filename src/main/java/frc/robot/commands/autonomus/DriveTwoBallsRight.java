// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveTwoBallsRight extends SequentialCommandGroup {
  /** Creates a new DriveTwoBallsRight. */
  public DriveTwoBallsRight() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new PathFollower(Constants.TWO_BALL_RIGHT_FIRST_PATH), new PathFollower(Constants.TWO_BALL_RIGHT_SECOUND_PATH), new PathFollower(Constants.TWO_BALL_HUB_PATH));
  }
}
