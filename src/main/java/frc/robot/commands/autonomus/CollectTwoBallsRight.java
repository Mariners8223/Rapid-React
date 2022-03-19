package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.mechanisems.CollectAndTransport;

public class CollectTwoBallsRight extends ParallelCommandGroup {
  public CollectTwoBallsRight() {
    addCommands(new DriveTwoBallsRight(), new CollectAndTransport(5));
  }
}
