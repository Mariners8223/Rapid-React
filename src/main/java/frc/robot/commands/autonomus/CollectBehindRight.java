package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.CollectAndTransport;

public class CollectBehindRight extends ParallelCommandGroup {
  public CollectBehindRight() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_RIGHT_PATH), new CollectAndTransport(5));
  }
}
