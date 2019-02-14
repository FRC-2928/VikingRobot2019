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
  public ArmState currentState;

  public enum ArmState{

    HATCH,
    BALL;
  
      public ArmState switchPosition(){
        
          return this.equals(HATCH)? HATCH:BALL;
        }
    }

  public ArmPreSets(){

  }

  public double getMidpoint(){
    midpoint = (Robot.intake.leftThreadbar.getLeftEncoder() + Robot.intake.rightThreadbar.getRightEncoder()) / 2;
    SmartDashboard.putNumber("Threadbar midpoint", midpoint);
    return midpoint;

  }

  public ArmState getArmState(){
    return currentState;
  }

  public void toggle(){
    getArmState().switchPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
