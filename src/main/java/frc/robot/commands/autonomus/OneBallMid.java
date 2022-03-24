package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootClose;

public class OneBallMid extends SequentialCommandGroup {
  public OneBallMid() {
    addCommands(new ShootClose(5), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
