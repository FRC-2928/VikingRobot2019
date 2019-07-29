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
    double driveX = Robot.oi.getDriveXR();
    if (Math.abs(Robot.oi.getDriveXR()) < 0.075) {
      driveX = 0;
    }

    double driveY = Robot.oi.getDriveYL();
    if (Math.abs(Robot.oi.getDriveYL()) < 0.075) {
      driveY = 0;
    }

    if(currentGear == GearState.HIGH){
      if(Math.abs(driveY) > 0.8){
        driveY *= 0.8;
      }
      if(Math.abs(driveX) > 0.8){
        driveX *= 0.8;
      }
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