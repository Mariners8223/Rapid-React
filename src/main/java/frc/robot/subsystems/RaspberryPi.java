package frc.robot.subsystems;

import org.ejml.simple.SimpleMatrix;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaspberryPi extends SubsystemBase {
  private static RaspberryPi instance;

  private NetworkTable table;

  private NetworkTableEntry leftX;
  private NetworkTableEntry leftY;

  private NetworkTableEntry rightX;
  private NetworkTableEntry rightY;

  private RaspberryPi() {
    table = NetworkTableInstance.getDefault().getTable(Constants.RASPBERRYPI_TABLE);

    leftX = table.getEntry("left-x");
    leftY = table.getEntry("left-y");

    rightX = table.getEntry("right-x");
    rightY = table.getEntry("right-y");
  }

  public static RaspberryPi getInstance(){
    if(instance == null) instance = new RaspberryPi();
    return instance;
  }

  public SimpleMatrix getLeft(){
    double[][] left_arr = {{leftX.getDouble(0)}, {leftY.getDouble(0)}};
    return new SimpleMatrix(left_arr);
  }

  public SimpleMatrix getRight(){
    double[][] right_arr = {{rightX.getDouble(0)}, {rightY.getDouble(0)}};
    return new SimpleMatrix(right_arr);
  }
}
