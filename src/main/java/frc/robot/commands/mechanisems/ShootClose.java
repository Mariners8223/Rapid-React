package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootClose extends SequentialCommandGroup {
  public ShootClose() {
    addCommands(new TransportBalls(false, 0.5), new ShootCycle(3, 0.3721));
  }
}
