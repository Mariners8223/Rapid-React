package frc.robot.commands.autonomus;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Chassis;

public class Rotate extends CommandBase {
  private Chassis chassis;

  private double angle;

  public Rotate(double angle) {
    chassis = Chassis.getInstance();
    addRequirements(chassis);

    this.angle = angle;
  }

  @Override
  public void initialize() {
    //chassis.setSmoothRotation(true);
  }

  @Override
  public void execute() {
    chassis.setSpeed(Constants.ZERO_VECTOR, chassis.getRotationPID(angle), Constants.BASE_DRIVE);
  }

  @Override
  public void end(boolean interrupted) {
    chassis.setMotorsSpeed(0, 0, 0, 0);
    //chassis.setSmoothRotation(false);
  }

  @Override
  public boolean isFinished() {
    return chassis.isRotationPIDatSetpoint();
  }
}
