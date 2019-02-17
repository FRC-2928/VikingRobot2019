package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;

public class RangefinderDrive extends Command {
  private double setpoint;
  private double currentPosition;
  private double error;

  public RangefinderDrive() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {
    // setpoint = whateverTF
    Robot.chassis.transmission.shift(GearState.LOW);
  }

  @Override
  protected void execute() {

    currentPosition = Robot.chassis.rangefinder.getRangefinder();
    error = setpoint - currentPosition;

    if(currentPosition < setpoint){
      Robot.chassis.drivetrain.drive(-0.5, 0);
    }

    if(currentPosition > setpoint){
      Robot.chassis.drivetrain.drive(0.5, 0);
    }
  }

  @Override
  protected boolean isFinished() {
    if(Math.abs(error) < 10000){
      return true;
    }

    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.drivetrain.drive(0,0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
