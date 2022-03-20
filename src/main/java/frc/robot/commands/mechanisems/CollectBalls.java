package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class CollectBalls extends SequentialCommandGroup {
  public CollectBalls() {
    addCommands(new LowerIntake(), new IntakeBalls(true));
  }

  public CollectBalls(double time) {
    addCommands(new LowerIntake(), new IntakeBalls(true, time));
  }
}
