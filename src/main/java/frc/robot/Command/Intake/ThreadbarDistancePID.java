package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class ThreadbarDistancePID extends Command {
  private double setpoint;
  private double P;
  private double I;
  private double errorSum;

  
  public ThreadbarDistancePID(double inchesSetpoint, double kP, double kI) {
    this.setpoint = inchesSetpoint;
    this.P = kP;
    this.I = kI;
    this.errorSum = 0;
  }

  @Override
  protected void initialize() {
    Robot.intake.leftThreadbar.leftThreadbarMotor.setSelectedSensorPosition(0);
    Robot.intake.rightThreadbar.rightThreadbarMotor.setSelectedSensorPosition(0);
  }

  @Override
  protected void execute() {
    //Todo, unify the two PIDS

    //Setting up the error
    double encoderSetpoint = setpoint * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    double leftPosition = Robot.intake.leftThreadbar.leftEncoderPosition;
    double leftError = encoderSetpoint - leftPosition;
    this.errorSum += leftError;
    double output = (P * leftError) + (I * errorSum);
    Robot.intake.leftThreadbar.setLeftPower(output);
    
    SmartDashboard.putNumber("Left Error", leftError);
    SmartDashboard.putNumber("Left output", output);
    System.out.println("Left output");
    System.out.println(output);
  }

  @Override
  protected boolean isFinished() {
    if(Math.abs(Robot.intake.leftThreadbar.leftEncoderPosition) <= Math.abs(10000)){

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
