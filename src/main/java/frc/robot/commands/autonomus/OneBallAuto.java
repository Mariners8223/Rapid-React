package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.ShootCycle;

public class OneBallAuto extends SequentialCommandGroup {
  public OneBallAuto() {
    addCommands(new Rotate(-45),new ShootCycle(1.0), new PathFollower(Constants.ONE_BALL_PATH), new IntakeBalls(Constants.LOWER_PULLIES_ENUM, Constants.NO_TIME));
  }
}
