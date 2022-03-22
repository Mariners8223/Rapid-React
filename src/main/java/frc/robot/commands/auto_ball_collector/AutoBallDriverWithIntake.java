package frc.robot.commands.auto_ball_collector;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.mechanisems.IntakeBalls;

public class AutoBallDriverWithIntake extends ParallelCommandGroup {
  public AutoBallDriverWithIntake() {
    addCommands(new IntakeBalls(true), new AutoBallDriver());
  }
}
