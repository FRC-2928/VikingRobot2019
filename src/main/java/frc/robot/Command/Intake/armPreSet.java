package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * By Erika, this is bad, but the arms mirror a set midPoint in theory
 */
public class armPreSet extends Command{
    int midpoint= 0; //set
    public void hatchSet(int moveAmount){/*
        i = (differance needed between bars)/2;
        if arms close{ do nothing }
        else:
            robot.Subsystem.Intake.LeftThreadbar(moveAmount-i,0.5,0.5);;
            robot.Subsystem.Intake.RightThreadbar(moveAmount+i,0.5,0.5);;
        */
    }
    public void ballSet(int moveAmount){/*
        o = (differance needed between bars)/2; 
        if arms far{do nothing}
        else:
            robot.Subsystem.Intake.LeftThreadbar(moveAmount-o,0.5,0.5);;
            robot.Subsystem.Intake.RightThreadbar(moveAmount+o,0.5,0.5);;
        */
    }
    protected boolean isFinished() {
        return true;
      }
}
