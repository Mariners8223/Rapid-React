package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Transport extends SubsystemBase {
  private static VictorSPX transport_bottom;
  private static VictorSPX transport_top;


  private static Transport instance;


  private Transport() {
    transport_bottom = new VictorSPX(Constants.TRANSPORT_BOTTOM);
    transport_top = new VictorSPX(Constants.TRANSPORT_TOP);
    
    transport_bottom.setInverted(Constants.BOTTOM_TRANSPORT_INVERTED);
    transport_top.setInverted(Constants.TOP_TRANSPORT_INVERTED);
  }


  public void transportInwards(double voltage) {
    transport_top.set(ControlMode.PercentOutput, -voltage);
    transport_bottom.set(ControlMode.PercentOutput, -voltage);
  }

  public void transportOutwards(double voltage) {
    transport_top.set(ControlMode.PercentOutput, voltage);
    transport_bottom.set(ControlMode.PercentOutput, voltage);
  }

  public void setUpper(double speed) {
    transport_top.set(ControlMode.PercentOutput, -speed);
  }
  
  public void stopAll() {
    transport_top.set(ControlMode.PercentOutput, 0);
    transport_bottom.set(ControlMode.PercentOutput, 0);
  }

  public static Transport getInstance() {
    if (instance == null) 
      instance = new Transport();
    return instance;
  }
}
