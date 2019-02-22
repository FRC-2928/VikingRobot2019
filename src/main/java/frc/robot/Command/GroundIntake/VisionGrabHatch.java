package frc.robot.Command.GroundIntake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//Code to grab the hatch from limelight
public class VisionGrabHatch extends Command {
  public VisionGrabHatch() {
    requires(Robot.groundintake.hatchGrabber);
    requires(Robot.groundintake.pusher);
  }

  @Override
  protected void initialize() {
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
  }

  @Override
  protected void interrupted() {
  }
}
