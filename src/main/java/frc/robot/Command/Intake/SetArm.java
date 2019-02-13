package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;

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

  private boolean variableIsDone;

  public SetArm(ArmState state) {
    

    this.target = state;
  }

  @Override
  protected void initialize() {
    if (!variableIsDone){
    leftPreviousPosition = Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightPreviousPosition = Robot.intake.rightThreadbar.getRightEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    
    leftSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - leftPreviousPosition;
    rightSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - rightPreviousPosition;
    
    variableIsDone = true;
    }
  }

  @Override
  protected void execute() {
    leftCurrentPosition = Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightCurrentPosition = Robot.intake.rightThreadbar.getRightEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    leftError = (leftSetpoint - leftCurrentPosition);
    rightError = (rightSetpoint - rightCurrentPosition);
    double kP = 0.8;
    double min_Command =0.2;
    double leftOutput;
    double rightOutput;

    if (leftError > 1){
     leftOutput = kP * leftError;
    }

    else {
     leftOutput = kP * leftError + min_Command;
    }

    if (rightError > 1){
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
    SmartDashboard.putNumber("Arm switch left setpoint", leftSetpoint);
    SmartDashboard.putNumber("Arm switch left error", leftError);
    SmartDashboard.putNumber("Arm switch left output", leftOutput);
    SmartDashboard.putNumber("Arm switch right output", rightOutput);
  }

  @Override
  protected boolean isFinished() {
    double stopError = 0.5;
    leftCurrentPosition = Robot.intake.leftThreadbar.getLeftEncoder();
    rightCurrentPosition = Robot.intake.rightThreadbar.getRightEncoder();
    leftError = (leftSetpoint - leftCurrentPosition) / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightError = (rightSetpoint - rightCurrentPosition) / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    
    if(Math.abs(leftError) > stopError && Math.abs(rightError) > stopError){
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