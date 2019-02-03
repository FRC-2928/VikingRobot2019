package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunWheels extends Command {
  private double power;

  public RunWheels(double power) {

    //requires(Robot.intake.wheels);
    this.power = power;

  }

  @Override
  protected void initialize() {
    Robot.intake.wheels.setWheelPower(power);
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
    Robot.intake.wheels.setWheelPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
