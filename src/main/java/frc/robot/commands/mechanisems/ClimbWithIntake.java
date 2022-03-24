package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class ClimbWithIntake extends ParallelCommandGroup {
  public ClimbWithIntake(boolean up) {
    addCommands(new Climb(up));
  }
}
