package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunFourBar extends Command {
  private double output;
  public RunFourBar(double power) {
    requires(Robot.chassis.fourbar);
    this.output = power;
  }

  @Override
  protected void initialize() {
    Robot.chassis.fourbar.setFourBarPower(output);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.fourbar.setFourBarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
