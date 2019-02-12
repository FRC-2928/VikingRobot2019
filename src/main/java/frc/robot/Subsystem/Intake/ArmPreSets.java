package frc.robot.Subsystem.Intake;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Command.Intake.ThreadbarDistancePID;

import static frc.robot.Subsystem.Intake.ArmPreSets.ArmState.HATCH;
import static frc.robot.Subsystem.Intake.ArmPreSets.ArmState.BALL;

public class ArmPreSets extends Subsystem {
  public double midpoint;
  private double leftCurrentPosition;
  private double rightCurrentPosition;
  private double leftPreviousPosition;
  private double rightPreviousPosition;
  private double leftSetpoint;
  private double rightSetpoint; 
  public double leftError;
  public double rightError;

  public ArmState currentState;
  

  public enum ArmState{

    HATCH,
    BALL;
  
      public ArmState switchPosition(){
        
          return this.equals(HATCH)? HATCH:BALL;
        }
    }

  public ArmPreSets(){

    this.midpoint = (Robot.intake.leftThreadbar.getLeftEncoder() + Robot.intake.rightThreadbar.getRightEncoder()) / 2;

    leftPreviousPosition = Robot.intake.leftThreadbar.getLeftEncoder();
    rightPreviousPosition = Robot.intake.rightThreadbar.getRightEncoder();

    leftSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - leftPreviousPosition;
    rightSetpoint = RobotConstants.THREAD_ENCODER_TICKS_PER_SWITCH - rightPreviousPosition;

  }

  public void switchState(ArmState state){

    leftCurrentPosition = Robot.intake.leftThreadbar.getLeftEncoder();
    rightCurrentPosition = Robot.intake.rightThreadbar.getRightEncoder();
    leftError = leftSetpoint - leftCurrentPosition;
    rightError = rightSetpoint - rightCurrentPosition;

    switch(state){
      
      case BALL:
      Robot.intake.leftThreadbar.setLeftPower(leftError);
      Robot.intake.rightThreadbar.setRightPower(rightError);
      break;

      case HATCH:
      Robot.intake.leftThreadbar.setLeftPower(-leftError);
      Robot.intake.rightThreadbar.setRightPower(-rightError);
      break;
    }
    currentState = state;
  }

  public double[] getError(){
    
       return new double[]{leftError, rightError};
  }

  public double getRightError(){
    rightCurrentPosition = Robot.intake.rightThreadbar.getRightEncoder();
    rightError = rightSetpoint - rightCurrentPosition;
    return rightError;
  }

  public double getLeftError(){
    leftCurrentPosition = Robot.intake.leftThreadbar.getLeftEncoder();
    leftError = leftSetpoint - leftCurrentPosition;
    return leftError;
  }

  public double getMidpoint(){

    SmartDashboard.putNumber("Threadbar midpoint", midpoint);
    return midpoint;

  }

  public ArmState getArmState(){
    return currentState;
  }

  public void toggleArm(){
    switchState(getArmState().switchPosition());

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
