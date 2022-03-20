package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class CollectAndTransport extends ParallelCommandGroup {
  public CollectAndTransport() {
    addCommands(new CollectBalls(), new TransportBalls(true));
  }

  public CollectAndTransport(double time) {
    addCommands(new CollectBalls(time), new TransportBalls(true, time));
  }
}
