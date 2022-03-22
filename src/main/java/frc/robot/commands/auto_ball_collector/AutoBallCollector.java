package frc.robot.commands.auto_ball_collector;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.mechanisems.LowerIntake;

public class AutoBallCollector extends SequentialCommandGroup {
  public AutoBallCollector() {
    addCommands(new LowerIntake(), new AutoBallDriverWithIntake());
  }
}
