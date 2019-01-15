package frc.robot.Command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Field;

public class WaitForFMSData extends Command {

    public WaitForFMSData()
    {
        // No subsystem requirements
    }

    public void initialize()
    {
        // Nothing to initialize
    }

    public void execute()
    {
        // Nothing to execute
    }

    @Override
    public boolean isFinished()
    {
        // Better to put this in isFinished, as it's called just as often as execute
        return Field.getInstance().update();
    }
}
