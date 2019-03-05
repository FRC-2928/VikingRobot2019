package frc.robot.Command.Endgame;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * Code for climbing to the third level platform via four bar
 */
public class ClimbWithFourBar extends Command {

  public enum Direction {
    FORWARD, BACKWARDS
  }

  private double currentPosition;
  private double setpoint;
  private double error;
  private Direction setpointDirection;
  public boolean stopFourBar;

  public ClimbWithFourBar() {
    this.setpointDirection = Direction.FORWARD;
    stopFourBar = false;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    currentPosition = Robot.chassis.fourbar.getFourBarPosition();
    setpoint = RobotConstants.FOUR_BAR_MAX_ENCODER_TICKS;
    error = setpoint - currentPosition;

    if (setpointDirection == Direction.FORWARD) {
      // Starts driving the four bar up until it hits max
      Robot.chassis.fourbar.setFourBarPower(0.2); // Placeholder, low for testing
    }

    // Goes backwards if we aren't too far
    if (setpointDirection == Direction.BACKWARDS) {
      if (currentPosition < 10000) { // Placeholder, needs testing
        Robot.chassis.fourbar.setFourBarPower(-0.2);
      } else {
        stopFourBar = true;
      }
    }
  }

  @Override
  protected boolean isFinished() {
    return error > 100000 || // placeholder
        stopFourBar || 
        setpointDirection == Direction.BACKWARDS && error < 1000;
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
