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
    public static final int TALON_GROUND_HATCH_LEFT = 4;
    public static final int TALON_GROUND_HATCH_RIGHT= 5; //This shit be gone

    //Threadbar/intake motors
    public static final int TALON_LEFT_THREADBAR = 10;
    public static final int TALON_RIGHT_THREADBAR = 8;
    public static final int TALON_LEFT_INTAKE = 11;
    public static final int TALON_RIGHT_INTAKE = 9;

    //Elevator motors
    public static final int TALON_ELEVATOR = 7;

    //Four-Bar motors
    public static final int TALON_FOUR_BAR = 6;

    //Sensors
    public static final int PIGEON = 3;

    //Solenoids
    public static final int SOLENOID_TRANSMISSION_LOW = 0; //Only one, need to refactor
    public static final int SOLENOID_TRANSMISSION_HIGH = 1;
    public static final int SOLENOID_GROUND_HATCH = 2;
    public static final int SOLENOID_INTAKE_DRAWBRIDGE = 3;
    public static final int SOLENOID_ELEVATOR_BRAKE = 0;
}
