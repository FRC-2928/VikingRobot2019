package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RotateToSetpoint extends Command {
  private double setpoint;
  private double currentAngle;
  private double error;
  private double kP;
  private double kI;
  private double kD;
  private double errorSum;
  private double derivative;

  public RotateToSetpoint(double target) {
    requires(Robot.chassis.drivetrain);
    this.setpoint = target;
  }

  @Override
  protected void initialize() {
    Robot.chassis.drivetrain.zeroGyro();
  }

  @Override
  protected void execute() {
    
    currentAngle = Robot.chassis.drivetrain.getYaw();
    error = setpoint - currentAngle;

  }

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
