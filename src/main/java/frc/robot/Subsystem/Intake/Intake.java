/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystem.Intake;

/**
 * Add your docs here.
 */
public class Intake {

    public LeftThreadbar leftThreadbar;
    public RightThreadbar rightThreadbar;
    public Sensors sensors;
    public Wheels wheels;

    public Intake()
    {
        leftThreadbar = new LeftThreadbar();
        rightThreadbar = new RightThreadbar();
        sensors = new Sensors();
        wheels = new Wheels();
    }

}
