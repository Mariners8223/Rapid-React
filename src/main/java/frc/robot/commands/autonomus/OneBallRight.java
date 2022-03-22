package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootClose;

public class OneBallRight extends SequentialCommandGroup {
  public OneBallRight() {
    addCommands(new Rotate(-45), new ShootClose(3), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
