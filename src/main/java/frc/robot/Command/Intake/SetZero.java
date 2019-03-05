package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

/**
 * Sets threadbar back to default position for cargo or hatch (depends on arm state)
 */
public class SetZero extends Command {
  private double currentPositonLeft;
  private double currentPositionRight;
  private double errorLeft;
  private double errorRight;
  private double outputLeft;
  private double outputRight;
  private double setpoint;
  private double kP;
  private double min_Command;

  public SetZero() {
    requires(Robot.intake.threadbar);
  }

  @Override
  protected void initialize() {
    setpoint = 0;
    kP = 0.3;
    min_Command = 0.1;
  }

  @Override
  protected void execute() {
    currentPositonLeft = Robot.intake.threadbar.getLeftThreadbarEncoder()
        * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    currentPositionRight = Robot.intake.threadbar.getRightThreadbarEncoder()
        * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    errorLeft = setpoint - currentPositonLeft;
    errorRight = setpoint - currentPositionRight;

    if (errorLeft > 1 || errorRight > 1) {
      outputLeft = errorLeft * kP;
      outputRight = errorRight * kP;
    }

    if (errorLeft < 1 || errorRight < 1) {
      outputLeft = errorLeft * kP + errorLeft * min_Command;
      outputRight = errorRight * kP + errorRight * min_Command;
    }

    Robot.intake.threadbar.setThreadbarPower(outputLeft, outputRight);
  }

  @Override
  protected boolean isFinished() {
    return Math.abs(errorLeft) < 0.4 || Math.abs(errorRight) < 0.4;
  }

  @Override
  protected void end() {
    Robot.intake.threadbar.setThreadbarPower(0, 0);
  }

  @Override
  protected void interrupted() {
  }
}
