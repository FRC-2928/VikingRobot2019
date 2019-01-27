package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunRightThreadbar extends Command {
  private double power;

  public RunRightThreadbar(double power) {
    //requires(Robot.intake.rightThreadbar);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.intake.rightThreadbar.setRightPower(power);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.rightThreadbar.setRightPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  public void resetEncoders(){

    while(!false){

      Robot.intake.rightThreadbar.setRightPower(power);

    }



  }
}
