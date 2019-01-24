package frc.robot.Command.Arm;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunGroundIntake extends Command {
  private double power;

  public RunGroundIntake(double power) {
    requires(Robot.groundintake.hatchGrabber);
    this.power = power;
  }

  @Override
  protected void initialize() {
    Robot.groundintake.hatchGrabber.setpower(power);
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
    Robot.groundintake.hatchGrabber.setpower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
