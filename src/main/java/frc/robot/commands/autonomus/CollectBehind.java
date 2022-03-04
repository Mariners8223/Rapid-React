package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.IntakeBalls;

public class CollectBehind extends ParallelCommandGroup {
  public CollectBehind() {
    addCommands(new PathFollower(Constants.BALL_BEHIND_PATH), new IntakeBalls(Constants.INTAKE_RIGHT_ENUM, false, 5));
  }
}
