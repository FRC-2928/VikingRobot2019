package frc.robot.Command.Endgame;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Command.Endgame.ClimbWithFourBar;
import frc.robot.Command.Endgame.RangefinderDriveToPlatform;
import frc.robot.Command.Endgame.PostClimbDrive;
import frc.robot.Robot;

/**
 * Endgame climb commands
 */
public class Endgame extends CommandGroup {
  public Endgame() {
    CommandGroup driveCommandGroup = new CommandGroup();
    CommandGroup climbCommandGroup = new CommandGroup();
    CommandGroup endCommandGroup = new CommandGroup();

    driveCommandGroup.addSequential(new RangefinderDriveToPlatform());
    climbCommandGroup.addSequential(new ClimbWithFourBar());
    endCommandGroup.addSequential(new PostClimbDrive());

    addSequential(driveCommandGroup);
    addSequential(climbCommandGroup);
    if (Robot.chassis.rangefinder.getRangefinder() > 254) {
      addSequential(endCommandGroup);
    }
  }
}
