package frc.robot;

import org.ejml.simple.SimpleMatrix;

public final class Constants {
    // Motors
    public static final int LEFT_FRONT = 1;
    public static final int LEFT_BACK = 4;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_BACK = 3;

    // Motors Invertion
    public static final boolean LEFT_FRONT_INVERTED = false;
    public static final boolean LEFT_BACK_INVERTED = false;
    public static final boolean RIGHT_FRONT_INVERTED = true;
    public static final boolean RIGHT_BACK_INVERTED = true;

    // Motors encoders                              0.00001072997
    public static final double LEFT_FRONT_DPP = 0.000010440706;
    public static final double LEFT_BACK_DPP = 0.000010440706;
    public static final double RIGHT_FRONT_DPP = 0.000010440706;
    public static final double RIGHT_BACK_DPP = 0.000010440706;

    // Joysticks
    public static final int DRIVE_JOYSTICK = 0;

    // Axises
    public static final int DRIVE_DIRECTION_X = 0;
    public static final int DRIVE_DIRECTION_Y = 1;
    public static final int DRIVE_ROTATION = 4;

    // Matrices
    private static final double[][] base_drive_arr = {
        {0.5, 0.5},
        {-0.5, 0.5}
    };
    public static final SimpleMatrix BASE_DRIVE = new SimpleMatrix(base_drive_arr);

    private static final double sqrt2inverted = 0.707106781;
    private static final double[][] velocity_arr = {
        {sqrt2inverted, -sqrt2inverted},
        {sqrt2inverted, sqrt2inverted}
    };
    public static final SimpleMatrix VELOCITY = new SimpleMatrix(velocity_arr);

    private static final double[][] zero_arr = {{0}, {0}};
    public static final SimpleMatrix ZERO_VECTOR = new SimpleMatrix(zero_arr);

    // PID values
    public static final double ANGLE_KP = 0.03;
    public static final double ANGLE_KI = 0;
    public static final double ANGLE_KD = 0.01;

    // Raspberry pi
    public static final String RASPBERRYPI_TABLE = "vision";
    public static final String RASPBERRYPI_ANGLE_ENTERY = "angle";
    public static final String RASPBERRYPI_DISTANCE_ENTERY = "distance";

    // Other constants
    public static final double CHASSIS_DEAD_BAND = 0.2;
    public static final double CHASSIS_MULTIPLIE = 0.7;
    public static final double CHASSIS_CLAMP = 0.8;
    public static final double CHASSIS_VELOCITY_TIME_TO_SECONDS = 10.0;
    public static final double ROTATION_SPEED = 2;
    public static final double ROTATION_DEAD_BAND = 0.3;
}