package frc.robot.Subsystem.Intake;

//Where we initialize stuff for Robot.java, allows us to call subsystems from other places

public class Intake {

    public Threadbar threadbar;
    //public Sensors sensors;
    public Wheels wheels;
    public ArmPresets armPresets;
    public Drawbridge drawbridge;

    public Intake()
    {
        threadbar = new Threadbar();
        //sensors = new Sensors();
        wheels = new Wheels();
        armPresets = new ArmPresets();
        drawbridge = new Drawbridge();
    }

}
