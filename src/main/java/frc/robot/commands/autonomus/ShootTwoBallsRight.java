package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class ShootTwoBallsRight extends SequentialCommandGroup {
  public ShootTwoBallsRight() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_RIGHT_HUB_PATH), new TransportBalls(false, 0.5), new ShootCycle(3, 0.36), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
