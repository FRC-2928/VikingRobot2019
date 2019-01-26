package frc.robot.Command.GroundIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunGroundIntake extends Command {

  private double power;
  private boolean gLimitSwitch;
  private boolean upOrDown;

  public RunGroundIntake(double power, boolean gLimitSwitch, boolean upOrDown) {
    requires(Robot.groundintake.hatchGrabber);
    this.power = power;
    this.gLimitSwitch = gLimitSwitch;
    this.upOrDown = upOrDown;

  }

  @Override
  protected void initialize() {
    Robot.groundintake.hatchGrabber.setpower(power);
    Robot.groundintake.hatchGrabber.setlimit(gLimitSwitch, upOrDown);
  }

  @Override
  protected void execute() {
  }
  
  @Override
  protected boolean isFinished() {
    return gLimitSwitch;
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
