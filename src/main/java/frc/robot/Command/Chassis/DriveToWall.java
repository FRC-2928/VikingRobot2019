package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveToWall extends Command {
  private double currentPosition;
  public DriveToWall() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {
    Robot.chassis.drivetrain.drive(0.7, 0);
  }

  @Override
  protected void execute() {
    currentPosition = Robot.chassis.rangefinder.getRangefinder();
  }

  @Override
  protected boolean isFinished() {
    if(currentPosition < 118){ //placeholder
      return true;    
    }
    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.drivetrain.drive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
