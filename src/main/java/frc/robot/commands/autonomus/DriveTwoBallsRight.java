package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;

public class DriveTwoBallsRight extends SequentialCommandGroup {
  public DriveTwoBallsRight() {
    addCommands(new PathFollower(Constants.TWO_BALL_RIGHT_FIRST_PATH), new PathFollower(Constants.TWO_BALL_RIGHT_SECOUND_PATH), new PathFollower(Constants.TWO_BALL_HUB_PATH));
  }
}
