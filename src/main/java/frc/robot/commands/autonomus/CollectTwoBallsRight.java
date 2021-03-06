package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.mechanisems.IntakeBalls;
import frc.robot.commands.mechanisems.TransportBalls;

public class CollectTwoBallsRight extends ParallelCommandGroup {
  public CollectTwoBallsRight() {
    addCommands(new DriveTwoBallsRight(), new TransportBalls(true, 6, Constants.TRANSPORT_SPEED), new IntakeBalls(true, 6));
  }
}
