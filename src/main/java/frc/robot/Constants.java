package frc.robot;

import org.ejml.simple.SimpleMatrix;

public final class Constants {
    // Motors
    public static final int LEFT_FRONT = 1;
    public static final int LEFT_BACK = 4;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_BACK = 3;

    // Joysticks
    public static final int DRIVE_JOYSTICK = 0;

    // Axises
    public static final int DRIVE_DIRECTION_X = 0;
    public static final int DRIVE_DIRECTION_Y = 1;
    public static final int DRIVE_ROTATION = 4;


    private static double sqrt2inverted = 0.70710678118;
    private static final double[][] base_drive_arr = {
        {sqrt2inverted, sqrt2inverted},
        {-sqrt2inverted, sqrt2inverted}
    };
    public static final SimpleMatrix BASE_DRIVE = new SimpleMatrix(base_drive_arr);

    public static final double DEAD_BAND = 0.001;
    public static final double MULTI = 0.3;
    public static final double MAX_CLAMP = 0.5;
    public static final double ROTATION_SPEED = 1;

    // PID values
    public static final double ANGLE_KI = 0;
    public static final double ANGLE_KP = 0;
    public static final double ANGLE_KD = 0;
}