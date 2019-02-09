package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class SetElevator extends Command {
  private double setpointInches;
  private double currentPosition;
  private double error;

  //Inches
  public SetElevator(double setpointInches) {
    this.setpointInches = setpointInches; 
  }

  @Override
  protected void initialize() {
    
  }


  @Override
  protected void execute() {
    currentPosition = Robot.elevator.lift.getLiftPositionInches();
    error = setpointInches - currentPosition;

    if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH)){
      
      if(error > 0){

      Robot.elevator.lift.setLiftPower(0.2);

      }

      if(error < 0){

      Robot.elevator.lift.setLiftPower(-0.2);

      }
    }
    else{
      System.out.println("Elevator setpoint is too high dude, aborting");
    }
  }

  @Override
  protected boolean isFinished() {

    if(Math.abs(error) < 1){
      return true;
    }
    if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH)){
      System.out.println("Elevator setpoint is too high dude, aborting");
      return true;
    }
   
    return false;
  }

  @Override
  protected void end() {
    Robot.elevator.lift.setLiftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
