package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class ThreeBallsAuto extends SequentialCommandGroup {
  public ThreeBallsAuto() {
    addCommands(new OneBallAuto(), new CollectTwoBallsRight(), new TransportBalls(false, 0.5), new ShootCycle(2.0, 0.39), new PathFollower(Constants.ONE_BALL_PATH1));
  }
}
