package frc.robot.commands.mechanisems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class CollectAndTransport extends ParallelCommandGroup {
  public CollectAndTransport() {
    addCommands(new CollectBalls(), new TransportBalls(true, Constants.TRANSPORT_SPEED));
  }

  public CollectAndTransport(double time) {
    addCommands(new CollectBalls(time), new TransportBalls(true, time, Constants.TRANSPORT_SPEED));
  }
}
