package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.mechanisems.LowerIntake;
import frc.robot.commands.mechanisems.RaiseIntake;

public class TwoBallLeftAuto extends SequentialCommandGroup {
  public TwoBallLeftAuto() {
    addCommands(new LowerIntake(), new CollectBehindLeft(), new RaiseIntake(), new ShootTwoBallsLeft());
  }
}
