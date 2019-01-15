package frc.robot.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Command.Chassis.RotateNinety;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystem.Chassis.Drivetrain;

public class SpinnyBoi extends CommandGroup {
    public SpinnyBoi(){

        Command rotateninety = new RotateNinety(90);
        addSequential(rotateninety);
    }

}
