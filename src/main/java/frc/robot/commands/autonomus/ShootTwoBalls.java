package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.mechanisems.ShootCycle;

public class ShootTwoBalls extends SequentialCommandGroup {
  public ShootTwoBalls() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_HUB_PATH), new ShootCycle(4), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
