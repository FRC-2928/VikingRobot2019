package frc.robot.Subsystem.Intake;

import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;

public class Intake {

    public LeftThreadbar leftThreadbar;
    public RightThreadbar rightThreadbar;
    //public Sensors sensors;
    public Wheels wheels;
    public ArmPreSets armPresets;
    public Drawbridge drawbridge;

    public Intake()
    {
        leftThreadbar = new LeftThreadbar();
        rightThreadbar = new RightThreadbar();
        //sensors = new Sensors();
        wheels = new Wheels();
        armPresets = new ArmPreSets();
        drawbridge = new Drawbridge();
    }

}
