 package frc.robot;

 //In here we map all the ports for the robot

public class RobotMap 
{

    //Drivetrain motors
    //Back Talon on left and right are the leaders, rest are slaves
    public static final int VCTOR_FRONT_LEFT = 13;
    public static final int VICTOR_MIDDLE_LEFT = 14;
    public static final int TALON_BACK_LEFT = 15;
    public static final int VICTOR_FRONT_RIGHT = 2;
    public static final int VICTOR_MIDDLE_RIGHT = 1;
    public static final int TALON_BACK_RIGHT = 0;

    //Ground hatch motors
    public static final int TALON_GROUND_HATCH = 27; //This shit be gone

    //Threadbar/intake motors
    public static final int TALON_LEFT_THREADBAR = 11;
    public static final int TALON_RIGHT_THREADBAR = 10;
    public static final int TALON_LEFT_INTAKE = 4;
    public static final int TALON_RIGHT_INTAKE = 5;

    //Elevator motors (placeholder)
    public static final int TALON_ELEVATOR = 55;

    //Four-Bar motors (placeholder)
    public static final int TALON_FOUR_BAR = 56;

    //Sensors
    public static final int PIGEON = 3;

    //Solenoids
    public static final int SOLENOID_TRANSMISSION_LOW = 0;
    public static final int SOLENOID_TRANSMISSION_HIGH = 1;
    public static final int SOLENOID_GROUND_HATCH = 2;
}
