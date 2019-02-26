package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;


//Moves the elevator to whatever setpoint you give it via PID
public class SetElevator extends Command {
  private double setpointInches;
  private double currentPosition;
  private double error;
  private double elevatorMovement;
  private double kP;
  private double min_Command;
  
  // public enum LiftState{ //TODO: Add in enum stuff, currently not in use

  //   BALL_LEVEL_1,
  //   BALL_LEVEL_2,
  //   BALL_LEVEL_3,
  //   HATCH_LEVEL_1,
  //   HATCH_LEVEL_2,
  //   HATCH_LEVEL_3,
  //   CARGO_SHIP_BALL,
  //   GROUND_LEVEL;

  // }

  //Inches
  public SetElevator(double setpointInches) {

    requires(Robot.elevator.lift);
    this.setpointInches = setpointInches; 
    
  }

  @Override
  protected void initialize() {

    Robot.elevator.lift.shiftBrake(BrakeState.OFF);
    
  }

  @Override
  protected void execute() {
    currentPosition = Robot.elevator.lift.getLiftPosition();
    SmartDashboard.putNumber("Elevator position", currentPosition);
    error = setpointInches - currentPosition;

    if(error > 0){
      kP = 0.07;
      min_Command = 0.05;
    }

    if(error < 0){
      kP = 0.007;
      min_Command = 0.002;
    }
    

    // if(setpointInches + currentPosition < RobotConstants.ELEVATOR_MAX_ENCODER_TICKS){

      if(Math.abs(error) > 2){

        elevatorMovement = kP * error;

      }

      if(Math.abs(error) < 2){

        elevatorMovement = (kP * error) + (min_Command * error);
      }

      Robot.elevator.lift.setLiftPower(elevatorMovement); 
      SmartDashboard.putNumber("Elevator PID movement", elevatorMovement);
      SmartDashboard.putNumber("Elevator PID Error", error);
      SmartDashboard.putNumber("Elevator PID Setpoint", setpointInches);

    }
  // }

  @Override
  protected boolean isFinished() {

    if(error > 0){
      if(Math.abs(error) < 0.1){
        SmartDashboard.putNumber("Stopping error", error);
        return true;
      }
  }

    if(error < 0){
      if(Math.abs(error) < 0.5){
        return true;
      }
    }
  
    // if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches + currentPosition)){
    //   System.out.println("Elevator setpoint is too high dude, aborting");
    //   return true;
    // }

    if(currentPosition < RobotConstants.ELEVATOR_MAX_ENCODER_TICKS - RobotConstants.ELEVATOR_STOP_THRESHOLD){
      return true;
    }

    // if (currentPosition > RobotConstants.ELEVATOR_MIN_ENCODER_TICKS + RobotConstants.ELEVATOR_STOP_THRESHOLD){
    //   return true;
    // }
   
    return false;

  }

  @Override
  protected void end() {
    Robot.elevator.lift.setLiftPower(0);
    Robot.elevator.lift.shiftBrake(BrakeState.ON);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
