package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class Rotate extends CommandBase {
  private Chassis chassis;

  private double sp_time;

  private double angle;

  public Rotate(double angle) {
    chassis = Chassis.getInstance();
    addRequirements(chassis);

    this.angle = angle;
  }

  @Override
  public void initialize() {
    sp_time = Constants.NO_TIME;
    chassis.setSmoothRotation(true);
  }

  @Override
  public void execute() {
    chassis.setSpeed(Constants.ZERO_VECTOR, chassis.getRotationPID(angle), Constants.BASE_DRIVE);

    if(chassis.isRotationPIDatSetpoint() && sp_time == Constants.NO_TIME) {
      sp_time = Timer.getFPGATimestamp();
    }
    else if(!chassis.isRotationPIDatSetpoint()) sp_time = Constants.NO_TIME;
  }

  @Override
  public void end(boolean interrupted) {
    chassis.setMotorsSpeed(0, 0, 0, 0);
    chassis.setSmoothRotation(false);
  }

  @Override
  public boolean isFinished() {
    if(sp_time == Constants.NO_TIME) return false;
    return Timer.getFPGATimestamp() - sp_time > 0.4;
  }
}
