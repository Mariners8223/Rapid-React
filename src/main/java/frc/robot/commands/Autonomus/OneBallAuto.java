package frc.robot.commands.Autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.mechanisems.ShootCycle;

public class OneBallAuto extends SequentialCommandGroup {
  public OneBallAuto() {
    addCommands(new ShootCycle(2.0), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
