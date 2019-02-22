package frc.robot.Subsystem.Intake;

//Where we initialize stuff for Robot.java, allows us to call subsystems from other places

public class Intake {

    public Threadbar threadbar;
    //public Sensors sensors;
    public Wheels wheels;
    public ArmPreSets armPresets;
    public Drawbridge drawbridge;

    public Intake()
    {
        threadbar = new Threadbar();
        //sensors = new Sensors();
        wheels = new Wheels();
        armPresets = new ArmPreSets();
        drawbridge = new Drawbridge();
    }

}
