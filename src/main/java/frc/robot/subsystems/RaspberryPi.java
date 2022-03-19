package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaspberryPi extends SubsystemBase {
  private static RaspberryPi instance;

  private NetworkTable table;

  private NetworkTableEntry xEntry;
  private NetworkTableEntry yEntry;

  private RaspberryPi() {
    table = NetworkTableInstance.getDefault().getTable(Constants.RASPBERRYPI_TABLE);

    xEntry = table.getEntry(Constants.RASPBERRYPI_X_ENTERY);
    yEntry = table.getEntry(Constants.RASPBERRYPI_Y_ENTERY);
  }

  public static RaspberryPi getInstance(){
    if(instance == null) instance = new RaspberryPi();
    return instance;
  }

  public double getX() {
    return xEntry.getDouble(0);
  }

  public double getY() {
    return yEntry.getDouble(0);
  }
}
