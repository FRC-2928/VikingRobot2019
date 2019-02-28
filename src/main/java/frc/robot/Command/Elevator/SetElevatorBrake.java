package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;

public class SetElevatorBrake extends Command {
  private BrakeState target;

  public SetElevatorBrake(BrakeState target) {
    requires(Robot.elevator.lift);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.lift.shiftBrake(target);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}