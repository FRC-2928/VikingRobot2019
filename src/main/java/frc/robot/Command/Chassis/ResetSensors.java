package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetSensors extends Command {
    public ResetSensors()
    {
        requires(Robot.chassis.drivetrain);
    }
    @Override
    public boolean isFinished()
    {
        return true;
    }

    public void initialize()
    {
        Robot.chassis.drivetrain.zeroSensors();
    }
}