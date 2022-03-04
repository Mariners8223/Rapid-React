package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class ShootTwoBalls extends SequentialCommandGroup {
  public ShootTwoBalls() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_HUB_PATH), new TransportBalls(false, 0.5), new ShootCycle(3), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
