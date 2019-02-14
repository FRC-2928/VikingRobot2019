package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;
import static java.lang.Math.abs;

public class SetArm extends Command {
  private ArmState target;
  private double leftCurrentPosition;
  private double rightCurrentPosition;
  private double leftPreviousPosition;
  private double rightPreviousPosition;
  private double leftSetpoint;
  private double rightSetpoint; 
  private double leftError;
  private double rightError;
  public ArmState currentState;

  public SetArm(ArmState state) {
    
    this.target = state;
  
  }

  @Override
  protected void initialize() {
    leftPreviousPosition = Math.abs( Robot.intake.leftThreadbar.leftThreadbarMotor.getSelectedSensorPosition() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH);
    rightPreviousPosition = Math.abs( Robot.intake.rightThreadbar.rightThreadbarMotor.getSelectedSensorPosition() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH);
    
    switch(target){

      case BALL:
      leftSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH + leftPreviousPosition;
      rightSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - rightPreviousPosition;
      break;

      case HATCH:
      leftSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - leftPreviousPosition;
      rightSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH + rightPreviousPosition;
      break;
    }
    SmartDashboard.putNumber("Left Arm Previous Position", leftPreviousPosition);
    SmartDashboard.putNumber("Right Arm Previous Position", rightPreviousPosition);
    SmartDashboard.putNumber("Right Arm Preset setpoint", rightSetpoint);
    SmartDashboard.putNumber("Left Arm Preset setpoint", leftSetpoint);
  }

  @Override
  protected void execute() {
    leftCurrentPosition = Robot.intake.leftThreadbar.leftThreadbarMotor.getSelectedSensorPosition() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightCurrentPosition = Robot.intake.rightThreadbar.rightThreadbarMotor.getSelectedSensorPosition() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    leftError = (leftSetpoint + leftCurrentPosition);
    rightError = (rightSetpoint - rightCurrentPosition);
    double kP = 0.4;
    double min_Command =0.1;
    double leftOutput;
    double rightOutput;

    if (Math.abs(leftError) > 1){
     leftOutput = kP * leftError;
    }

    else {
     leftOutput = kP * leftError + min_Command;
    }

    if (Math.abs(rightError) > 1){
     rightOutput = kP * rightError;
    }

    else {
     rightOutput = kP * rightError + min_Command;
    }

    switch(target){
      
      case BALL:
      Robot.intake.leftThreadbar.setLeftPower(leftOutput);
      Robot.intake.rightThreadbar.setRightPower(-rightOutput);
      break;

      case HATCH:
      Robot.intake.leftThreadbar.setLeftPower(-leftOutput);
      Robot.intake.rightThreadbar.setRightPower(rightOutput);
      break;
    }
    currentState = target;
    SmartDashboard.putNumber("Arm switch left current position",leftCurrentPosition);
    SmartDashboard.putNumber("Arm switch right current position", rightCurrentPosition);
    SmartDashboard.putNumber("Arm switch left error", leftError);
    SmartDashboard.putNumber("Arm switch right error", rightError);
    SmartDashboard.putNumber("Arm switch left output", leftOutput);
    SmartDashboard.putNumber("Arm switch right output", rightOutput);

  }

  @Override
  protected boolean isFinished() {
    double stopError = 0.5;
    leftCurrentPosition = Robot.intake.leftThreadbar.leftThreadbarMotor.getSelectedSensorPosition();
    rightCurrentPosition = Robot.intake.rightThreadbar.rightThreadbarMotor.getSelectedSensorPosition();
    leftError = (leftSetpoint + leftCurrentPosition) / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightError = (rightSetpoint - rightCurrentPosition) / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    
    if(Math.abs(leftError) > stopError && Math.abs(rightError) > stopError){
      leftError = 0;
      rightError = 0;
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.leftThreadbar.setLeftPower(0);
    Robot.intake.rightThreadbar.setRightPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}