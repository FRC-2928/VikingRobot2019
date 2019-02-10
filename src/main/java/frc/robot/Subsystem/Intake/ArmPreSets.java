package frc.robot.Subsystem.Intake;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

import static frc.robot.Subsystem.Intake.ArmPreSets.ArmState.HATCH;
import static frc.robot.Subsystem.Intake.ArmPreSets.ArmState.BALL;

public class ArmPreSets extends Subsystem {
  public double midpoint;
  private double error;

  public enum ArmState{

    HATCH,
    BALL;
  
      public ArmState switchPosition(){
        
          return this.equals(HATCH)? HATCH:BALL;
        }
    }

  public ArmPreSets(){

    midpoint = (Robot.intake.leftThreadbar.getLeftEncoder() + Robot.intake.rightThreadbar.getRightEncoder()) / 2;
    

  }

  public void switchState(ArmState state){
    
    if(state == HATCH){

    
      Robot.intake.leftThreadbar.setLeftPower(error);
      Robot.intake.rightThreadbar.setRightPower(error);

    }

    if(state == BALL){

      Robot.intake.leftThreadbar.setLeftPower(error);
      Robot.intake.rightThreadbar.setRightPower(error);

    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
