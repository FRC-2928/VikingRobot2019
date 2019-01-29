package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunLeftThreadbar extends Command {

private I2C sensor;
private double power;
private double encoderTicks;
private byte output[];

  public RunLeftThreadbar(double power) {
    //requires(Robot.intake.leftThreadbar);
    this.power = power;
  }

  @Override
  protected void initialize() {
    
    Robot.intake.leftThreadbar.setLeftPower(power);
    // Robot.intake.sensors.setSensor(sensor);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // sensor.read(0x3E, 1 , output);

    System.out.println(output);
    Robot.intake.leftThreadbar.getLeftEncoder();

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.intake.leftThreadbar.setLeftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
