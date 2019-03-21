package frc.robot.Command.Endgame;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DefaultRunFourBar extends Command {
  public boolean stopDefaultFourBar;


  public DefaultRunFourBar() {
    requires(Robot.chassis.fourbar);
    stopDefaultFourBar = Robot.chassis.fourbar.getDefaultFourBarState();
  }

  @Override
  protected void initialize() {
    Robot.chassis.fourbar.setFourBarPower(-0.1);
  }

  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(stopDefaultFourBar == true){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.chassis.fourbar.setFourBarPower(0);
    stopDefaultFourBar = true;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
