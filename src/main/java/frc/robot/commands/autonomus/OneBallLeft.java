package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootClose;

public class OneBallLeft extends SequentialCommandGroup {
  public OneBallLeft() {
    addCommands(new Rotate(30), new ShootClose(3), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
