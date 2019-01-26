 package frc.robot;

public class RobotMap 
{
    
    //Talon SRX, needs updating
    //Back Talon on left and right are the leaders, rest are slaves
    public static final int VCTOR_FRONT_LEFT = 13;
    public static final int VICTOR_MIDDLE_LEFT = 14;
    public static final int TALON_BACK_LEFT = 15;
    public static final int VICTOR_FRONT_RIGHT = 2;
    public static final int VICTOR_MIDDLE_RIGHT = 1;
    public static final int TALON_BACK_RIGHT = 0;

    public static final int VICTOR_GROUND_HATCH = 2928;

    public static final int TALON_LEFT_THREADBAR = 16;
    public static final int TALON_RIGHT_THREADBAR = 987;
    public static final int VICTOR_LEFT_INTAKE = 2910;
    public static final int VICTOR_RIGHT_INTAKE = 2930;



    //Sensors
    public static final int PIGEON = 3;

    //Solenoids
    public static final int SOLENOID_TRANSMISSION_LOW = 0;
    public static final int SOLENOID_TRANSMISSION_HIGH = 1;
    public static final int SOLENOID_GROUND_HATCH = 118;
}
