package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;

public class ClimbWithIntake extends ParallelCommandGroup {
  public ClimbWithIntake(boolean up) {
    addCommands(new Climb(up), new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME));
  }
}
