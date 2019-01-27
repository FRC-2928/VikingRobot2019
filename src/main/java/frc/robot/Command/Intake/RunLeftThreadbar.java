package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunLeftThreadbar extends Command {

private double power;
private double encoderTicks;

  public RunLeftThreadbar(double power) {
    //requires(Robot.intake.leftThreadbar);
    this.encoderTicks = encoderTicks;
    this.power = power;
  }

  @Override
  protected void initialize() {
    
    Robot.intake.leftThreadbar.setLeftPower(power);
    Robot.intake.leftThreadbar.setLeftEncoderTicks(encoderTicks);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.leftThreadbar.setLeftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
