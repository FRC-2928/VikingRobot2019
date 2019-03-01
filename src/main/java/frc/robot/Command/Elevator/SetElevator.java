package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;
import static java.lang.System.currentTimeMillis;

//Moves the elevator to whatever setpoint you give it via PID
public class SetElevator extends Command {
  private double setpointInches;
  private double currentPosition;
  private double error;
  private double elevatorMovement;
  private double kP;
  private double min_Command;
  private double kI;
  private double kD;
  private double derivative;
  private double errorSum;
  private double previousError;
  private boolean isFinished;
  private long stopTime;
  private long currentTime;
  
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
    errorSum = 0;
    derivative = 0;
    isFinished = false;

  }

  @Override
  protected void execute() {

    currentPosition = Robot.elevator.lift.getLiftPosition();
    SmartDashboard.putNumber("Elevator position", currentPosition);
    error = setpointInches - currentPosition;
    if(Math.abs(error) < 3){
      errorSum += (error * 0.2);
    }
    else{
      errorSum = 0;
    }
    derivative = (error - previousError);

    if(error > 0){
      kP = 0.055; //0.07
      kI = 0.005;
      kD = 0.15;
      min_Command = 0.005; //0.05
    }
    

    if(error < 0){
      kP = 0.01;
      kI = 0.001;
      kD = 0;
    }

    if(Math.abs(error) >= 2){

      elevatorMovement = (kP* error) + (kI * errorSum) - (kD * derivative);

    }

    if(Math.abs(error) < 2){

      elevatorMovement = ((kP + min_Command) * error) + (kI * errorSum) + (kD * derivative);
        
    }

    // if(inZone()){
    //   Robot.elevator.lift.shiftBrake(BrakeState.ON);
    // }
    // else{
    //   Robot.elevator.lift.shiftBrake(BrakeState.OFF);
    // }

    Robot.elevator.lift.setLiftPower(elevatorMovement); 
    previousError = error;
    SmartDashboard.putNumber("Elevator PID movement", elevatorMovement);
    SmartDashboard.putNumber("Elevator PID Error", error);
    SmartDashboard.putNumber("Elevator Integral", errorSum * kI);
    SmartDashboard.putNumber("Elevator PID Setpoint", setpointInches);
    System.out.println(derivative * kD);

  }

  private boolean inZone(){
    if(Math.abs(error) < 0.5){
      return true;
    }
    else{
      return false;
    }
  }

  @Override
  protected boolean isFinished() {

  //   if(error > 0){
  //     if(Math.abs(error) < 0.5){
  //       return true;
  //     }
  // }

  //   if(error < 0){
  //     if(Math.abs(error) < 0.5){
  //       return true;
  //     }
  //   }

  if(inZone() == true){
    if(stopTime == 0){
      stopTime = currentTimeMillis();
    }
    if(currentTimeMillis() - stopTime > 250){
      return true;
    }
   
  }
  else{
    stopTime = 0;
  }
  
  
    // if (isFinished == true){
    //   System.out.println("TIMEEEEE AHHH");
    //   return true;
    // }

    // if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches + currentPosition)){
    //   System.out.println("Elevator setpoint is too high dude, aborting");
    //   return true;
    // }

    // if(currentPosition < RobotConstants.ELEVATOR_MAX_ENCODER_TICKS - RobotConstants.ELEVATOR_STOP_THRESHOLD){
    //   return true;
    // }

    // if (currentPosition > RobotConstants.ELEVATOR_MIN_ENCODER_TICKS + RobotConstants.ELEVATOR_STOP_THRESHOLD){
    //   return true;
    // }
   
    return false;

  }

  @Override
  protected void end() {
    System.out.println("Elevator PID is stopping YAAAAAA");
    Robot.elevator.lift.setLiftPower(0);
    Robot.elevator.lift.shiftBrake(BrakeState.ON);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
