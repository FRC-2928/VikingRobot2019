 package frc.robot;

 /**
  * Map all the ports for the robots
  */
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
    public static final int TALON_GROUND_HATCH_RIGHT= 5;

    //Threadbar/intake motors
    public static final int TALON_LEFT_THREADBAR = 10;
    public static final int TALON_RIGHT_THREADBAR = 8;
    public static final int TALON_LEFT_INTAKE = 11;
    public static final int TALON_RIGHT_INTAKE = 9;

    //Elevator motors
    public static final int SPARK_ELEVATOR = 7;
    public static final int SPARK_ELEVATOR_TOP = 8;

    //Four-Bar motors
    public static final int SPARK_FOUR_BAR = 6;

    //Sensors
    public static final int PIGEON = 3;

    //Solenoids
    public static final int SOLENOID_TRANSMISSION = 2;
    // public static final int SOLENOID_TRANMISSION_HIGH = 1;
    public static final int SOLENOID_INTAKE_DRAWBRIDGE = 1;
    public static final int SOLENOID_ELEVATOR_BRAKE= 0;
}
