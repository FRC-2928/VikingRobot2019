package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;

public class RunWheelsForTime extends Command {
  private double power;
  private double time;
  public RunWheelsForTime(double power, double time){
  requires(Robot.intake.wheels);
  this.power = power;
}

@Override
protected void initialize() {
  if (Robot.intake.armPresets.currentState == ArmState.HATCH) {
    Robot.intake.wheels.setWheelPower(-power);
  }
  
  if (Robot.intake.armPresets.currentState == ArmState.BALL) {
    Robot.intake.wheels.setWheelPower(power);
  }
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
