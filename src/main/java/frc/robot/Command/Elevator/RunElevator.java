package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunElevator extends Command {
  private double power;
  public RunElevator(double power) {
    this.power = power;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() { 
    Robot.elevator.lift.setLiftPower(power);
  }

  @Override
  protected boolean isFinished() {
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
