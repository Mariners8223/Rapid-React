package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class ShootClose extends SequentialCommandGroup {
  public ShootClose(double time) {
    addCommands(new TransportBalls(false, 0.25), new ShootCycle(time, 70));
  }
}
