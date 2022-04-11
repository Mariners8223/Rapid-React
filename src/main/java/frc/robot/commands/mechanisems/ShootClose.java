package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootClose extends SequentialCommandGroup {
  public ShootClose(double time) {
    addCommands(new TransportBalls(false, 0.3, 0.8), new ShootCycle(time, 54));
  }
}
