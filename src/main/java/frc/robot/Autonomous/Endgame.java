package frc.robot.Autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Endgame extends CommandGroup {

  /*TODO: 
  1.Drive backwards however many inches are needed
  2.Start four bar movement
  3.After a few seconds, start driving forward
  4.Check for distance to driver station, and stop once you're close enough  
  */
  public Endgame() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
