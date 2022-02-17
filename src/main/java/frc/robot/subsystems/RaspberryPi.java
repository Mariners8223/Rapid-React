package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RaspberryPi extends SubsystemBase {
  private static RaspberryPi instance;

  private SerialPort usb;

  private RaspberryPi() {
    usb = new SerialPort(Constants.RASPBERRYPI_BAUDRATE, Constants.RASPBERRYPI_PORT, Constants.RASPBERRYPI_DATABITS);
    usb.setReadBufferSize(Constants.RASPBERRYPI_READ_BAFFER_SIZE);
  }

  public static RaspberryPi getInstance(){
    if(instance == null) instance = new RaspberryPi();
    return instance;
  }

  public double[] getData(){
    String[] data_str = usb.readString().split(" ");
    double[] data = new double[Constants.RASPBERRYPI_OUTPUT_LEANGTH];
    if (data_str.length != Constants.RASPBERRYPI_OUTPUT_LEANGTH) return data;
    for(int i = 0; i < Constants.RASPBERRYPI_OUTPUT_LEANGTH; i++) data[i] = Double.parseDouble(data_str[i]);
    return data;
  }
}
