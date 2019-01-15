package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;

public class Shift extends Command {

    private GearState target;

    public Shift(GearState target) {
        requires(Robot.chassis.transmission);
        this.target = target;
    }

    public void execute() {
        Robot.chassis.transmission.shift(target);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}