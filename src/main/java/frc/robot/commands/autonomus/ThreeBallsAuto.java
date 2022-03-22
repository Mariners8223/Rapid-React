package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.LowerIntake;
import frc.robot.commands.mechanisems.RaiseIntake;
import frc.robot.commands.mechanisems.ShootClose;

public class ThreeBallsAuto extends SequentialCommandGroup {
  public ThreeBallsAuto() {
    addCommands(new Rotate(-50), new PathFollower(Constants.THREE_BALL_INIT_TO_HUB_PUTH), new ShootClose(3), new LowerIntake(), new CollectTwoBallsRight(), new RaiseIntake(), new ShootClose(5), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
