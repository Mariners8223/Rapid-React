package frc.robot.commands.mechanisems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
//import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Climber;

public class Climb extends CommandBase {
  private Climber climber;
  //private Chassis chassis;

  private boolean up;

  public Climb(boolean up) {
    climber = Climber.getInstance();
    //chassis = Chassis.getInstance();
    addRequirements(climber);//, chassis);

    this.up = up;
  }

  @Override
  public void initialize() {
    //chassis.disable();
  }

  @Override
  public void execute() {
    if(up) climber.setSpeed(Constants.CLIMBER_SPEED);
    else climber.setSpeed(-Constants.CLIMBER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
    //chassis.enable();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
