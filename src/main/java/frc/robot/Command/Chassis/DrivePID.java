package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Drivetrain;

public class DrivePID extends Command {
  double error;
  double setpoint;
  double currentPositionLeft;
  double currentPositionRight;
  public DrivePID(double setpointInches) {
    requires(Robot.chassis.drivetrain);
    this.setpoint = setpointInches;
  }

  @Override
  protected void initialize() {
    Robot.chassis.drivetrain.resetEncoderPosition();
  }

  @Override
  protected void execute() {
    currentPositionLeft = Robot.chassis.drivetrain.getEncoderPositionLeft();
    currentPositionRight = Robot.chassis.drivetrain.getEncoderPositionRight();
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