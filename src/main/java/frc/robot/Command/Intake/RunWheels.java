package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;

public class RunWheels extends Command {
  private double intakePower;
  private double outtakePower;
  private double power;

  public RunWheels() {
    requires(Robot.intake.wheels);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("XBOX left trigger",Robot.oi.getLeftTrigger());
    SmartDashboard.putNumber("XBOX right trigger", Robot.oi.getRightTrigger());
    intakePower = Robot.oi.getLeftTrigger();
    outtakePower = Robot.oi.getRightTrigger();

    if(intakePower > 0){
      power = intakePower;
      Robot.oi.setRumble(0.6);
    }
    else if(outtakePower > 0){
      power = -outtakePower;
      Robot.oi.setRumble(0.6);
    }
    else{
      power = 0;
      Robot.oi.setRumble(0);
    }

    if (Robot.intake.armPresets.currentState == ArmState.HATCH) {
      Robot.intake.wheels.setWheelPower(-power);
    }
    
    if (Robot.intake.armPresets.currentState == ArmState.BALL) {
      Robot.intake.wheels.setWheelPower(power);
    }
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
    end();
  }
}
