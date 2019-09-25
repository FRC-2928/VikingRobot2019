package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;

/**
 * Here's where we create the command to drive via joystick
 * It's taking the X and Y values from the joystick, which was set up in Operator Interface
 */
public class JoystickDrive extends Command {
  private GearState currentGear;
  public JoystickDrive() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    currentGear = Robot.chassis.transmission.getGear();
    double driveX = Robot.oi.getDriveX();
    if (Math.abs(Robot.oi.getDriveX()) < 0.075) {
      driveX = 0;
    }

    double driveY = Robot.oi.getDriveY();
    if (Math.abs(Robot.oi.getDriveY()) < 0.075) {
      driveY = 0;
    }
    Robot.chassis.drivetrain.drive(driveY, -driveX);
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