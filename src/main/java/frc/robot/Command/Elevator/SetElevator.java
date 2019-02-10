package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Command.Elevator.RunElevator;

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
    double kP = 0.2;
    double min_Command = 0.05;
    double elevatorMovement = 0;
    currentPosition = Robot.elevator.lift.getLiftPositionInches();
    error = setpointInches - currentPosition;
    
    if(setpointInches + currentPosition > RobotConstants.ELEVATOR_MAX_ENCODER_TICKS){

      if(Math.abs(error) > 1){

        elevatorMovement = kP * error;

      }

      if(Math.abs(error) < 1){

        elevatorMovement = (kP * error) + (min_Command * error);

      }

      Robot.elevator.lift.setLiftPower(elevatorMovement);
      SmartDashboard.putNumber("Elevator position", currentPosition);
      SmartDashboard.putNumber("Elevator PID movement", elevatorMovement);
      
    }
  }

  @Override
  protected boolean isFinished() {

    if(Math.abs(error) < 0.5){
      return true;
    }
    if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches + currentPosition)){
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
