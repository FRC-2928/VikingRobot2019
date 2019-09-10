package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunLeftThreadbar extends Command {
  private double power;

  public RunLeftThreadbar(double power) {
    requires(Robot.intake.threadbar);
    requires(Robot.intake.drawbridge);
    this.power = power;
  }

  @Override
  protected void initialize() {
    // - for left, + for right
    Robot.intake.threadbar.setLeftThreadbarPower(power);
    // Robot.intake.sensors.setSensor(sensor);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intake.threadbar.getLeftThreadbarEncoder();
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.threadbar.setLeftThreadbarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
