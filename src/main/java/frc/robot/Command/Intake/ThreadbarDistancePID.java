package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ThreadbarDistancePID extends Command {
  private double setpoint;
  private double P;
  private double I;
  public ThreadbarDistancePID(double allSetpoint, double kP, double kI) {
    this.setpoint = allSetpoint;
    this.P = kP;
    this.I = kI;
  }

  @Override
  protected void initialize() {
    Robot.intake.leftThreadbar.leftThreadbarMotor.setSelectedSensorPosition(0);
    Robot.intake.rightThreadbar.rightThreadbarMotor.setSelectedSensorPosition(0);
  }

  @Override
  protected void execute() {

    double leftPosition = 

    double error =  

  }

  @Override
  protected boolean isFinished() {
    if(Robot.intake.leftThreadbar.leftInPosition == true || Robot.intake.rightThreadbar.rightInPosition == true){

      return true;

    }
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.rightThreadbar.setRightPower(0);
    Robot.intake.leftThreadbar.setLeftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
