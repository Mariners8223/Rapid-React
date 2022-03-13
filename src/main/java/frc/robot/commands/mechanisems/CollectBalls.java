package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

public class CollectBalls extends SequentialCommandGroup {
  public CollectBalls() {
    addCommands(new IntakeBalls(Constants.RAISE_PULLIES_ENUM, Constants.NO_TIME), new IntakeBalls(Constants.LOWER_PULLIES_ENUM, Constants.NO_TIME), new IntakeBalls(Constants.INTAKE_BOUTH_ENUM, Constants.NO_TIME));
  }
}
