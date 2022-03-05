package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class TwoBallLeftAuto extends SequentialCommandGroup {
  public TwoBallLeftAuto() {
    addCommands(new CollectBehindLeft(), new ShootTwoBallsLeft());
  }
}
