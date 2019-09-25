package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;

public class RunWheelsForTime extends Command {
  private double power;
  private double timeMS;
  private double previousTime;
  private double currentTime;
  private double elapsedTime;

  public RunWheelsForTime(double power, double time){
    requires(Robot.intake.wheels);
    this.power = power;
    this.timeMS = time;
}

@Override
protected void initialize() {
  if (Robot.intake.armPresets.currentState == ArmState.HATCH) {
    Robot.intake.wheels.setWheelPower(-power);
  }
  
  if (Robot.intake.armPresets.currentState == ArmState.BALL) {
    Robot.intake.wheels.setWheelPower(power);
  }

previousTime = System.currentTimeMillis();

}

@Override
protected void execute() {
currentTime = System.currentTimeMillis();

elapsedTime = currentTime - previousTime;

}

@Override
protected boolean isFinished(){ 
if (elapsedTime >= timeMS){
    return true;
  }
  {
    return false;
  }
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