package frc.robot.Command.Endgame;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
* After level 3 climb, we want to move forward towards driver station wall 
* but not so far such that we crash into it.
*/
public class PostClimbDrive extends Command {
  private double currentPosition;

  public PostClimbDrive() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {
    // drive forward
    Robot.chassis.drivetrain.drive(0.7, 0);
  }

  @Override
  protected void execute() {
    currentPosition = Robot.chassis.rangefinder.getRangefinder();
  }

  @Override
  protected boolean isFinished() {
    // if we're at or near the driver station wall, we're done
    // otherwise, keep going
    return currentPosition < 118;
  }

  @Override
  protected void end() {
    // stop the robot
    Robot.chassis.drivetrain.drive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
