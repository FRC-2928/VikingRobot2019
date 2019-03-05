package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunRightThreadbar extends Command {
  private double power;

  public RunRightThreadbar(double power) {
    requires(Robot.intake.threadbar);
    this.power = power;
  }

  @Override
  protected void initialize() {
    Robot.intake.threadbar.setRightThreadbarPower(power);
  }

  @Override
  protected void execute() {
    Robot.intake.threadbar.getRightThreadbarEncoder();

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.threadbar.setRightThreadbarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
