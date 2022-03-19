package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.mechanisems.IntakeBalls;

public class TwoBallRightAuto extends SequentialCommandGroup {
  public TwoBallRightAuto() {
    addCommands(new IntakeBalls(Constants.LOWER_PULLIES_ENUM, Constants.NO_TIME), new CollectBehindRight(), new ShootTwoBallsRight());
  }
}
