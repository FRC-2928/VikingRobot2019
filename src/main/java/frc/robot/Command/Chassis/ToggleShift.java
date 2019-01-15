package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;

public class ToggleShift extends Command {
    private GearState target;
    public ToggleShift()
    {
        requires(Robot.chassis.transmission);
    }
    public void initialize()
    {
        Robot.chassis.transmission.toggle();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}