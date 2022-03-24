package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class ShootClose extends SequentialCommandGroup {
  public ShootClose(double time) {
    addCommands(new TransportBalls(false, 0.25, Constants.TRANSPORT_SPEED), new ShootCycle(time, 54));
  }
}
