package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.TransportBalls;

public class CollectBehindLeft extends ParallelCommandGroup {
  public CollectBehindLeft() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_LEFT_PATH), new TransportBalls(true, 2, Constants.TRANSPORT_SPEED),new IntakeBalls(true, 2));
  }
}
