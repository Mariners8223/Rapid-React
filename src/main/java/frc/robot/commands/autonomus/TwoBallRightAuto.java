package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallRightAuto extends SequentialCommandGroup {
  public TwoBallRightAuto() {
    addCommands(new CollectBehindRight(), new ShootTwoBallsRight());
  }
}
