package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPreSets;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;
import frc.robot.Subsystem.Intake.Threadbar;
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
    System.out.println("------------------------- Yeet");
    System.out.println(Robot.intake);
    requires(Robot.intake.threadbar);
  
  }

  @Override
  protected void initialize() {
    leftPreviousPosition = Robot.intake.threadbar.getLeftThreadbarEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightPreviousPosition = Robot.intake.threadbar.getRightThreadbarEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    midpoint = (leftPreviousPosition + rightPreviousPosition) / 2;
    
    // Left positive, right negative
    switch(target){
      case HATCH:
      setpointLeft = midpoint + RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH;
      setpointRight = midpoint - RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH;
      break;

      case BALL :
      setpointLeft = midpoint + RobotConstants.THREAD_ENCODER_TICKS_TO_BALL;
      setpointRight = midpoint - RobotConstants.THREAD_ENCODER_TICKS_TO_BALL;
      break;

    }
    Robot.intake.armPresets.toggle();

    // SmartDashboard.putString("Current State", Robot.intake.armPresets.currentState.toString());
    SmartDashboard.putNumber("Arm State Midpoint", midpoint);
    SmartDashboard.putNumber("Arm State Left Previous Position", leftPreviousPosition);
    SmartDashboard.putNumber("Arm State Right Previous Position", rightPreviousPosition);
    SmartDashboard.putNumber("Arm State Left Setpoint", setpointLeft);
    SmartDashboard.putNumber("Arm State Right Setpoint", setpointRight);
  
  }

  @Override
  protected void execute(){
    currentPositionLeft = Robot.intake.threadbar.getLeftThreadbarEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;  
    currentPositionRight = Robot.intake.threadbar.getRightThreadbarEncoder()  / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    errorLeft = -setpointLeft + currentPositionLeft;
    errorRight = -setpointRight + currentPositionRight;
    double kP = 0.5;
    double min_Command = 0.15;
    // Very basic P, will expand later but need to test it first
    if (Math.abs(errorLeft) > 1){
      outputLeft = errorLeft * kP;
    } 
    
    if (Math.abs(errorLeft) < 1){
      outputLeft = (errorLeft * kP) + (min_Command * errorLeft);
    }
    
    if(Math.abs(errorRight) > 1){
      outputRight = errorRight * kP;
    }

    if(Math.abs(errorRight) < 1){
      outputRight = (errorRight * kP) + (min_Command * errorRight);
    }

    Robot.intake.threadbar.setLeftThreadbarPower(outputLeft);
    Robot.intake.threadbar.setRightThreadbarPower(outputRight);

    SmartDashboard.putNumber("Arm State current position left", currentPositionLeft);
    SmartDashboard.putNumber("Arm State current position right", currentPositionRight);
    SmartDashboard.putNumber("Arm State Error Left", errorLeft);
    SmartDashboard.putNumber("Arm State Error Right", errorRight);
    SmartDashboard.putNumber("Arm State Output Left", outputLeft);
    SmartDashboard.putNumber("Arm State Output Right", outputRight);
    Robot.intake.armPresets.getArmState();
  }

  @Override
  protected boolean isFinished() {
    double counter;
    SmartDashboard.putNumber("Is Finished error left", errorLeft);
    SmartDashboard.putNumber("Is Finished error right", errorRight);
    if(Math.abs(errorLeft) < 0.3 && Math.abs(errorRight) < 0.3){
      counter = 2;
      SmartDashboard.putNumber("Threadbar Arm State is finished", counter);
      return true;
    }
    else{
      counter = 1;
      SmartDashboard.putNumber("Threadbar Arm State is finished", counter);
    return false;
    }
  }

  @Override
  protected void end() {
    Robot.intake.threadbar.setLeftThreadbarPower(0);
    Robot.intake.threadbar.setRightThreadbarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}