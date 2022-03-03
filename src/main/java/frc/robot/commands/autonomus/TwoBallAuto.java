package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallAuto extends SequentialCommandGroup {
  public TwoBallAuto() {
    addCommands(new CollectBehind(), new ShootTwoBalls());
  }
}
