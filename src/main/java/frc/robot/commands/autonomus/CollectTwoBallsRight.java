package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.mechanisems.IntakeBalls;

public class CollectTwoBallsRight extends ParallelCommandGroup {
  public CollectTwoBallsRight() {
    addCommands(new DriveTwoBallsRight(), new IntakeBalls(Constants.INTAKE_BOUTH_ENUM, 8));
  }
}
