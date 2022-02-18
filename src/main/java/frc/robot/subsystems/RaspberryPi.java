package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaspberryPi extends SubsystemBase {
  private static RaspberryPi instance;

  private NetworkTable table;

  private NetworkTableEntry angleEntry;
  private NetworkTableEntry distanceEntry;

  private RaspberryPi() {
    table = NetworkTableInstance.getDefault().getTable(Constants.RASPBERRYPI_TABLE);

    angleEntry = table.getEntry(Constants.RASPBERRYPI_ANGLE_ENTERY);
    distanceEntry = table.getEntry(Constants.RASPBERRYPI_DISTANCE_ENTERY);
  }

  public static RaspberryPi getInstance(){
    if(instance == null) instance = new RaspberryPi();
    return instance;
  }

  public double getAngle() {
    return angleEntry.getDouble(0);
  }

  public double getDistance() {
    return distanceEntry.getDouble(0);
  }
}
