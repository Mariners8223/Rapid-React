package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class ThreeBallsAuto extends SequentialCommandGroup {
  public ThreeBallsAuto() {
    addCommands(new OneBallAuto(), new CollectTwoBallsRight(), new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME), new TransportBalls(false, 0.5), new ShootCycle(3), new PathFollower(Constants.ONE_BALL_PATH1));
  }
}
