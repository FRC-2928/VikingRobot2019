package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class RunElevator extends Command {
  private double power;
  private double currentPosition;
  public RunElevator(double power) {
    requires(Robot.elevator.lift);
    this.power = power;
  }

  @Override
  protected void initialize() {
    Robot.elevator.lift.setLiftPower(power);
  }

  @Override
  protected void execute() { 
    currentPosition = Robot.elevator.lift.getLiftPosition();

  }

  @Override
  protected boolean isFinished() {
    // if(currentPosition < RobotConstants.ELEVATOR_MAX_ENCODER_TICKS - 10000){
    //   return true;
    // }
    // if(currentPosition > RobotConstants.ELEVATOR_MIN_ENCODER_TICKS + 10000){
    //   return true;
    // }
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.lift.setLiftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
