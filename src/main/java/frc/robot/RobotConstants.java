package frc.robot;

//Set constants here for later use
public class RobotConstants {
    public static final long DRIVE_TICKS_PER_FOOT_LOW = 6969;
    public static final long DRIVE_TICKS_PER_FOOT_HIGH = 420;

    public static final long SHIFT_DELAY_MS = 100;
    public static final long DRAWBRIDGE_DELAY_MS = 250;

    public static final double THREAD_ENCODER_TICKS_PER_INCH = 27927;
    public static final double THREAD_ENCODER_TICKS_TO_HATCH = 0; //placeholder, ticks from midpoint to hatch
    public static final double THREAD_ENCODER_TICKS_TO_BALL = 2; //placeholder, ticks from midpoint to ball
    public static final long THREAD_MAX_ENCODER_TICKS_LEFT = 118; //placeholder
    public static final long THREAD_MAX_ENCODER_TICKS_RIGHT = 148; //placeholder
    public static final double LIMELIGHT_DISTANCE_FROM_FRONT = 24.5; //inches
    public static final double LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES = 0.8;

    public static final long ELEVATOR_ENCODER_TICKS_PER_INCH = 254; //placeholder
    public static final long ELEVATOR_MAX_ENCODER_TICKS = 973; //placeholder

    public static final long FOUR_BAR_ENCODER_TICKS_PER_INCH = 1011; //placeholder
    public static final long FOUR_BAR_MAX_ENCODER_TICKS = 5499; //placeholder
}
