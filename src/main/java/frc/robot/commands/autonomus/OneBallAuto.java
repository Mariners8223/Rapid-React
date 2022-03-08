package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.PathFollower;
import frc.robot.commands.mechanisems.ShootCycle;
import frc.robot.commands.mechanisems.TransportBalls;

public class OneBallAuto extends SequentialCommandGroup {
  public OneBallAuto() {
    addCommands(new TransportBalls(false, 0.5), new ShootCycle(4.0, 0.43), new PathFollower(Constants.ONE_BALL_PATH));
  }
}
