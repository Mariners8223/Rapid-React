package frc.robot;


import org.ejml.simple.SimpleMatrix;

public final class Constants {
    // Motors
    public static final int LEFT_FRONT = 1;
    public static final int LEFT_BACK = 4;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_BACK = 3;
    public static final int EYE_LEFT = 6;
    public static final int EYE_RIGHT = 8;
    public static final int INTAKE_LEFT = 5; 
    public static final int INTAKE_RIGHT = 9;
    public static final int TRANSPORT_BOTTOM = 11;
    public static final int TRANSPORT_TOP = 7;
    public static final int SHOOTER = 12;

    // Motors Invertion
    public static final boolean LEFT_FRONT_INVERTED = false;
    public static final boolean LEFT_BACK_INVERTED = false;
    public static final boolean RIGHT_FRONT_INVERTED = true;
    public static final boolean RIGHT_BACK_INVERTED = true;

    public static final boolean SHOOTER_INVERTED = false;
    public static final boolean LEFT_INTAKE_INVERTED = false;    
    public static final boolean LEFT_PULLIE_INVERTED = true;
    public static final boolean RIGHT_INTAKE_INVERTED = true;
    public static final boolean RIGHT_PULLIE_INVERTED = false;
    public static final boolean TOP_TRANSPORT_INVERTED = false;
    public static final boolean BOTTOM_TRANSPORT_INVERTED = true;    

    // Motors encoders                              0.00001072997
    public static final double LEFT_FRONT_DPP = 0.000010440706;
    public static final double LEFT_BACK_DPP = 0.000010440706;
    public static final double RIGHT_FRONT_DPP = 0.000010440706;
    public static final double RIGHT_BACK_DPP = 0.000010440706;

    // Joysticks
    public static final int DRIVE_JOYSTICK = 0;
    public static final int ARM_JOYSTICK = 1;

    // Axises
    public static final int DRIVE_DIRECTION_X = 0;
    public static final int DRIVE_DIRECTION_Y = 1;
    public static final int DRIVE_ROTATION = 4;
    public static final int SHOOT_TRIGGER = 2;

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

    // Speed Values
    public static final double PULLIES_SPEED = 0.6;
    public static final double INTAKE_SPEED = 0.6;
    public static final double TRANSPORT_SPEED = 0.6;
    public static final double SHOOTER_SPEED = 0.5;

    // Buttons
    public static final int RAISE_PULLIES_BUTTON = 3;
    public static final int LOWER_PULLIES_BUTTON = 2;
    public static final int INTAKE_LEFT_BUTTON = 5;
    public static final int INTAKE_RIGHT_BUTTON = 6;
    public static final int SHOOT_COMMAND_BUTTON = 5;
    public static final int TRANSPORT_INWARDS_BUTTON = 3;
    public static final int TRANSPORT_OUTWARDS_BUTTON = 2;

    // Path Follower
    public static final int FIND_TARGET_ITERATIONS = 10;

    // Autonomus
    private static final double[][] one_ball_path_arr = {{0,0}, {0, -1}};
    public static final SimpleMatrix[] ONE_BALL_PATH = RobotContainer.arrayToPath(one_ball_path_arr);

    // Other constants
    public static final double CHASSIS_DEAD_BAND = 0.2;
    public static final double CHASSIS_MULTIPLIE = 0.7;
    public static final double CHASSIS_CLAMP = 0.8;
    public static final double CHASSIS_VELOCITY_TIME_TO_SECONDS = 10.0;
    public static final double ROTATION_SPEED = 0.4;
    public static final double ROTATION_DEAD_BAND = 0.1;
    public static final double NO_TIME = -1.0;
}