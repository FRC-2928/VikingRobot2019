package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ResetElevatorEncoders extends Command {
  public ResetElevatorEncoders() {
    requires(Robot.elevator.lift);
  }

  @Override
  protected void initialize() {
    Robot.elevator.lift.resetLiftEncoders();
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
    end();
  }
}
