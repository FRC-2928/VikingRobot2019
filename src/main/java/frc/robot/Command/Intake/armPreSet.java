package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 * By Erika, this is bad, but the arms mirror a set midPoint in theory
 */
public class armPreSet extends Command{
    int midpoint= 0; //set
    public void hatchSet(int setMidPoint){/*
        i = (hatchDistance in ticks)/2
        
        RunLeftThreadbar(midPoint-i)
        RunRightThreadbar(midPoint+i)
        */
    }
    public void ballSet(int setMidPoint){/*
        o = (ballDistance in ticks)/2
        RunLeftThreadbar(midPoint-o)
        RunRightThreadbar(midPoint+o)
        */
    }
    protected boolean isFinished() {
        return false;
      }
}
