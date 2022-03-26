package frc.robot;


import org.ejml.simple.SimpleMatrix;

public final class Constants {
    // Motors
    public static final int LEFT_FRONT = 1;
    public static final int LEFT_BACK = 4;
    public static final int RIGHT_FRONT = 2;
    public static final int RIGHT_BACK = 3;
    public static final int EYE_LEFT = 8;
    public static final int EYE_RIGHT = 6;
    public static final int INTAKE_LEFT = 5; 
    public static final int INTAKE_RIGHT = 10;
    public static final int TRANSPORT_BOTTOM = 11;
    public static final int TRANSPORT_TOP = 7;
    public static final int SHOOTER = 12;
    public static final int CLIMBER = 13;


    // Motors Invertion
    public static final boolean LEFT_FRONT_INVERTED = false;
    public static final boolean LEFT_BACK_INVERTED = false;
    public static final boolean RIGHT_FRONT_INVERTED = true;
    public static final boolean RIGHT_BACK_INVERTED = true;

    public static final boolean SHOOTER_INVERTED = true;
    public static final boolean LEFT_INTAKE_INVERTED = false;    
    public static final boolean LEFT_PULLIE_INVERTED = false;
    public static final boolean RIGHT_INTAKE_INVERTED = true;
    public static final boolean RIGHT_PULLIE_INVERTED = true;
    public static final boolean TOP_TRANSPORT_INVERTED = false;
    public static final boolean BOTTOM_TRANSPORT_INVERTED = true;    
    public static final boolean CLIMBER_INVERTED = true;    


    // Motors encoders                              0.00001072997
    public static final double LEFT_FRONT_DPP = 0.000010440706;
    public static final double LEFT_BACK_DPP = 0.000010440706;
    public static final double RIGHT_FRONT_DPP = 0.000010440706;
    public static final double RIGHT_BACK_DPP = 0.000010440706;

    public static final double LEFT_EYE_DPP = 1.0 / 10000.0;
    public static final double RIGHT_EYE_DPP = 1.0 / 10000.0;

    public static final double ENCODERS_SPEED_TO_RPS = 10.0 / 2048.0;


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
        {-0.5, 0.5},
        {0.5, 0.5}
    };
    public static final SimpleMatrix BASE_DRIVE = new SimpleMatrix(base_drive_arr);

    private static final double[][] left_base_drive_arr = {
        {0.5, 0.5},
        {0.5, -0.5}
    };
    public static final SimpleMatrix LEFT_BASE_DRIVE = new SimpleMatrix(left_base_drive_arr);

    private static final double[][] right_base_drive_arr = {
        {-0.5, -0.5},
        {-0.5, 0.5}
    };
    public static final SimpleMatrix RIGHT_BASE_DRIVE = new SimpleMatrix(right_base_drive_arr);

    private static final double[][] velocity_arr = {
        {-0.66666666666, 0.66666666666},
        {0.75074183976, 0.75074183976}
    };
    public static final SimpleMatrix VELOCITY = new SimpleMatrix(velocity_arr);

    private static final double[][] zero_arr = {{0}, {0}};
    public static final SimpleMatrix ZERO_VECTOR = new SimpleMatrix(zero_arr);

    // PID values
    public static final double ANGLE_KP = 0.03;
    public static final double ANGLE_KI = 0;
    public static final double ANGLE_KD = 0.002;
    public static final double ANGLE_TOLERANCE = 5.0;

    public static final double INTAKE_LEFT_KP = 1.0;
    public static final double INTAKE_LEFT_KI = 0;
    public static final double INTAKE_LEFT_KD = 0;

    public static final double INTAKE_RIGHT_KP = 1.0;
    public static final double INTAKE_RIGHT_KI = 0;
    public static final double INTAKE_RIGHT_KD = 0;

    public static final double INTAKE_TOLERANCE = 0.05;

    public static final double SHOOTER_KF = (0.5 * 1023.0) / 11200.0;
    public static final double SHOOTER_KP = 0.35;
    public static final double SHOOTER_KI = 0.0005;
    public static final double SHOOTER_KD = 0.005;
    public static final double SHOOTER_TOLERANCE = 0.3;


    // Raspberry pi
    public static final String RASPBERRYPI_TABLE = "vision";

    // Speed Values
    public static final double PULLIES_SPEED = 0.6;
    public static final double INTAKE_LEFT_SPEED = 0.6;
    public static final double INTAKE_RIGHT_SPEED = 0.6;
    public static final double TRANSPORT_SPEED = 0.6;
    public static final double SHOOTER_SPEED = 0.4;
    public static final double CLIMBER_SPEED = 1.0;
    public static final double EYE_SPEED = 1.0;

    // Buttons
    public static final int INTAKE_LEFT_BUTTON = 5; 
    public static final int INTAKE_RIGHT_BUTTON = 6;
    public static final int SHOOT_COMMAND_BUTTON = 5;
    public static final int TRANSPORT_INWARDS_BUTTON = 3;
    public static final int TRANSPORT_OUTWARDS_BUTTON = 2;
    public static final int CLIMB_UP_BUTTON = 6;
    public static final int CLIMB_DOWN_BUTTON = 5;
    public static final int RESET_ANGLE_BUTTON = 1;
    public static final int SHOOT_CLOSE_BUTTON = 1;
    public static final int COLLECT_AUTO_BUTTON = 2;
    public static final int ROTATE_TO_ORIGIN_BUTTON = 4;
    public static final int DISABLE_ANGLE_FIX = 3;
    public static final int CHANGE_CHASSIS_SPEED_BUTTON = 4;

    // Path Follower
    public static final int FIND_TARGET_ITERATIONS = 10;

    // Autonomus
    private static final double[][] one_ball_path_arr = {{0,0}, {0, -5.5}};
    public static final SimpleMatrix[] ONE_BALL_PATH = RobotContainer.arrayToPath(one_ball_path_arr);

    private static final double[][] one_ball_path_arr1 = {{0,0}, {1.6, -2}};
    public static final SimpleMatrix[] ONE_BALL_PATH1 = RobotContainer.arrayToPath(one_ball_path_arr1);

    private static final double[][] ball_behind_right_path_arr = {{0,0}, {0, -0.7}, {2.5, -0.7}};
    public static final SimpleMatrix[] BALL_BEHIND_RIGHT_PATH = RobotContainer.arrayToPath(ball_behind_right_path_arr);

    private static final double[][] ball_behind_right_hub_path_arr = {{0,0}, {-4.5, 0}, {-4.5, 0.4}};
    public static final SimpleMatrix[] BALL_BEHIND_RIGHT_HUB_PATH = RobotContainer.arrayToPath(ball_behind_right_hub_path_arr);

    private static final double[][] ball_behind_left_path_arr = {{0,0}, {-1.5, -0.5}};
    public static final SimpleMatrix[] BALL_BEHIND_LEFT_PATH = RobotContainer.arrayToPath(ball_behind_left_path_arr);

    private static final double[][] ball_behind_left_hub_path_arr = {{0,0}, {1.5, 0}, {1.5, 1.9}};
    public static final SimpleMatrix[] BALL_BEHIND_LEFT_HUB_PATH = RobotContainer.arrayToPath(ball_behind_left_hub_path_arr);

    private static final double[][] two_balls_first_path = {{0,0}, {3.5, -0.7}};
    public static final SimpleMatrix[] TWO_BALL_RIGHT_FIRST_PATH = RobotContainer.arrayToPath(two_balls_first_path);

    private static final double[][] two_balls_secound_path = {{0,0}, {-5.7, -1.6}};
    public static final SimpleMatrix[] TWO_BALL_RIGHT_SECOUND_PATH = RobotContainer.arrayToPath(two_balls_secound_path);

    private static final double[][] two_balls_hub_path = {{0,0}, {1.5, 1}, {1, 2.5}};
    public static final SimpleMatrix[] TWO_BALL_HUB_PATH = RobotContainer.arrayToPath(two_balls_hub_path);

    private static final double[][] three_ball_init_to_hub_arr = {{0,0}, {-4, 0.4}};
    public static final SimpleMatrix[] THREE_BALL_INIT_TO_HUB_PUTH = RobotContainer.arrayToPath(three_ball_init_to_hub_arr);

    // Other constants
    public static final double CHASSIS_DEAD_BAND = 0.05;
    public static final double CHASSIS_CLAMP = 1.0;
    public static final double CHASSIS_MULTIPLIE = 1.2;
    public static final double CHASSIS_VELOCITY_TIME_TO_SECONDS = 10.0;
    public static final double ROTATION_SPEED = 0.3;
    public static final double ROTATION_DEAD_BAND = 0.05;
    public static final double NO_TIME = -1.0;

    public static final double EYE_UP = -0.2;
    public static final double EYE_LEFT_DOWN = -2.8;
    public static final double EYE_RIGHT_DOWN = -3.2;
}