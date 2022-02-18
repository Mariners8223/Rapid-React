package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaspberryPi extends SubsystemBase {
  private static RaspberryPi instance;

  private NetworkTable table;

  private double angle;
  private double distance;

  private RaspberryPi() {
    table = NetworkTableInstance.getDefault().getTable(Constants.RASPBERRYPI_TABLE);
    
    angle = 0;
    distance = 0;
  }

  public static RaspberryPi getInstance(){
    if(instance == null) instance = new RaspberryPi();
    return instance;
  }

  public double getAngle() {
    table.getEntry(Constants.RASPBERRYPI_ANGLE_ENTERY).setDouble(angle);
    return angle;
  }

  public double getDistance() {
    table.getEntry(Constants.RASPBERRYPI_DISTANCE_ENTERY).setDouble(distance);
    return distance;
  }
}
