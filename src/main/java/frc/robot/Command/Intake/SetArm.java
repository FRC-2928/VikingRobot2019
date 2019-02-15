package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPreSets;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;
import static java.lang.Math.abs;

public class SetArm extends Command {
  private double leftPreviousPosition;
  private double rightPreviousPosition;
  private double midpoint;
  private double setpointLeft;
  private double setpointRight;
  private double currentPositionLeft;
  private double currentPositionRight;
  private double errorLeft;
  private double errorRight;
  private double outputLeft;
  private double outputRight;
  private ArmState target;
  public ArmState currentState;

  public SetArm(ArmState state) {
    
    this.target = state;
  
  }

  @Override
  protected void initialize() {
    midpoint = Robot.intake.armPresets.getMidpoint() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    leftPreviousPosition = Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightPreviousPosition = Robot.intake.rightThreadbar.getRightEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;

    switch(target){
      case HATCH:
      setpointLeft = midpoint + RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH;
      setpointRight = midpoint - RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH;
      break;

      case BALL:
      setpointLeft = midpoint - RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH;
      setpointRight = midpoint + RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH;
      break;

    }
    SmartDashboard.putNumber("Arm State Midpoint", midpoint);
    SmartDashboard.putNumber("Arm State Left Previous Position", leftPreviousPosition);
    SmartDashboard.putNumber("Arm State Right Previous Position", rightPreviousPosition);
    SmartDashboard.putNumber("Arm State Left Setpoint", setpointLeft);
    SmartDashboard.putNumber("Arm State Right Setpoint", setpointRight);
  }

  @Override
  protected void execute(){
    currentPositionLeft = Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;  
    currentPositionRight = Robot.intake.rightThreadbar.getRightEncoder()  / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    errorLeft = setpointLeft + currentPositionLeft;
    errorRight = setpointRight + currentPositionRight;
    double kP = 0.3;
    double min_Command = 0.1;
    // Very basic P, will expand later but need to test it first
    if (Math.abs(errorLeft) > 1){
      outputLeft = errorLeft * kP;
    }
    
    if (Math.abs(errorLeft) < 1){
      outputLeft = (errorLeft * kP) + min_Command;
    }
    
    if(Math.abs(errorRight) > 1){
      outputRight = errorRight * kP;
    }

    if(Math.abs(errorRight) < 1){
      outputRight = (errorRight * kP) + min_Command;
    }
    Robot.intake.leftThreadbar.setLeftPower(outputLeft);
    Robot.intake.rightThreadbar.setRightPower(outputRight);

    SmartDashboard.putNumber("Arm State current position left", currentPositionLeft);
    SmartDashboard.putNumber("Arm State current position right", currentPositionRight);
    SmartDashboard.putNumber("Arm State Error Left", errorLeft);
    SmartDashboard.putNumber("Arm State Error Right", errorRight);
    SmartDashboard.putNumber("Arm State Output Left", outputLeft);
    SmartDashboard.putNumber("Arm State Output Right", outputRight);
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("Is Finished error left", errorLeft);
    SmartDashboard.putNumber("Is Finished error right", errorRight);
    if(Math.abs(errorLeft) < 0.5 && Math.abs(errorRight) < 0.5){
      return true;
    }
    else{
    return false;
    }
    
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