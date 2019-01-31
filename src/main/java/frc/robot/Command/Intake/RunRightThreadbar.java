package frc.robot.Command.Intake;

import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunRightThreadbar extends Command {
  private double power;

  public RunRightThreadbar(double power) {
    //requires(Robot.intake.rightThreadbar);
    this.power = power;
  }

  @Override
  protected void initialize() {
    Robot.intake.rightThreadbar.setRightPower(power);
  }

  @Override
  protected void execute() {
    Robot.intake.rightThreadbar.getRightEncoder();
    
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.rightThreadbar.setRightPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }

  public void resetEncoders(){

    while(!false){

      Robot.intake.rightThreadbar.setRightPower(power);

    }



  }
}
