package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateToSetpoint extends Command {
  private double setpoint;
  private double error;

  public RotateToSetpoint(double setpoint) {
    requires(Robot.chassis.drivetrain);
    this.setpoint = setpoint;
  }

  @Override
  protected void initialize() {
  }


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
  }
}
