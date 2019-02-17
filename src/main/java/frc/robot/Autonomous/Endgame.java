package frc.robot.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Command.Chassis.RangefinderDrive;
import frc.robot.Command.Chassis.SetFourBar;
import frc.robot.Robot;
import frc.robot.Command.Chassis.DriveToWall;

public class Endgame extends CommandGroup {

  /*TODO: 
  1.Drive backwards however many inches are needed
  2.Start four bar movement
  3.After a few seconds, start driving forward
  4.Check for distance to driver station, and stop once you're close enough  
  */
  public Endgame() {

    CommandGroup driveCommandGroup = new CommandGroup();  
    CommandGroup climbCommandGroup = new CommandGroup();
    CommandGroup endCommandGroup = new CommandGroup();

    driveCommandGroup
      .addSequential(new RangefinderDrive());

    climbCommandGroup
      .addSequential(new SetFourBar(true));
   
    endCommandGroup
      .addSequential(new DriveToWall());


    addSequential(driveCommandGroup);
    addSequential(climbCommandGroup);
    if(Robot.chassis.rangefinder.getRangefinder() > 254){
      addSequential(endCommandGroup);
    } 
   }
}
