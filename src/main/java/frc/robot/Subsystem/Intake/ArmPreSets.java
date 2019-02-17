package frc.robot.Subsystem.Intake;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
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
    midpoint = (Robot.intake.threadbar.getLeftThreadbarEncoder() - Robot.intake.threadbar.getRightThreadbarEncoder()) / 2;
    SmartDashboard.putNumber("Threadbar midpoint", midpoint);
    return midpoint;
  }

  public ArmState getArmState(){
    SmartDashboard.putString("Arm State", currentState == ArmState.BALL ? "BALL" : "HATCH");
    return currentState;
  }

  public void toggle(){
    currentState = currentState == ArmState.HATCH ? ArmState.BALL : ArmState.HATCH;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
