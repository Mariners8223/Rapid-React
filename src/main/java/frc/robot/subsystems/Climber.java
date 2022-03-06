package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private static Climber instance;

  TalonFX climber;

  private Climber() {
    climber = new TalonFX(Constants.CLIMBER);
    climber.setNeutralMode(NeutralMode.Coast);
    if(Constants.CLIMBER_INVERTED) climber.setInverted(TalonFXInvertType.Clockwise);
    else climber.setInverted(TalonFXInvertType.CounterClockwise);
    //climber.config_kF(0, Constants.CLIMBER_KF);
  }

  public void setSpeed(double s) {
    SmartDashboard.putNumber("v", climber.getSelectedSensorVelocity());
    if (climber.getSelectedSensorVelocity() < 1000 && climber.getMotorOutputPercent() > 0.3) climber.set(ControlMode.PercentOutput, s);
    else climber.set(ControlMode.PercentOutput, s);
  }

  public static Climber getInstance() {
    if (instance == null)
      instance = new Climber();
    return instance;
  }
}
