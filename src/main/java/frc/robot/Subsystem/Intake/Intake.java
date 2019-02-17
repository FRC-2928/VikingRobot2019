package frc.robot.Subsystem.Intake;

import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;

//Where we initialize stuff for Robot.java, allows us to call subsystems from other places

public class Intake {

    public Threadbar threadbar;
    public LeftThreadbar leftThreadbar;
    public RightThreadbar rightThreadbar;
    //public Sensors sensors;
    public Wheels wheels;
    public ArmPreSets armPresets;
    public Drawbridge drawbridge;

    public Intake()
    {
        threadbar = new Threadbar();
        System.out.println(threadbar);
        leftThreadbar = new LeftThreadbar();
        rightThreadbar = new RightThreadbar();
        //sensors = new Sensors();
        wheels = new Wheels();
        armPresets = new ArmPreSets();
        drawbridge = new Drawbridge();
    }

}
