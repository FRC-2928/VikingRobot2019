package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import static java.lang.System.currentTimeMillis;

/**
 * Moves the elevator to whatever setpoint you give it via PID
 */
public class SetElevator extends Command {
  private LiftState setpoint;
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
  private ArmState armState;
  private long stopTime;
  private long currentTime;
  private int counter;

  public enum LiftState { // TODO: Add in enum stuff, currently not in use
    LEVEL_1, LEVEL_2, LEVEL_3, CARGO_SHIP_BALL, CARGO_LOADER_BALL, GROUND_LEVEL;
  }

  // Inches
  public SetElevator(LiftState setpoint) {
    requires(Robot.elevator.lift);
    this.setpoint = setpoint;

  }

  @Override
  protected void initialize() {
    Robot.elevator.lift.shiftBrake(BrakeState.OFF);
    armState = Robot.intake.armPresets.getArmState();

    switch (setpoint) {
    case LEVEL_1:
      if (armState == ArmState.HATCH) {
        setpointInches = 8.35;
      } else {
        setpointInches = 13;
      }
      break;
    case LEVEL_2:
      if (armState == ArmState.HATCH) {
        setpointInches = 27.5;
      } else {
        setpointInches = 32.5;
      }
      break;
    case LEVEL_3:
      if (armState == ArmState.HATCH) {
        setpointInches = 47;
      } else {
        setpointInches = 47;
      }
      break;
    case GROUND_LEVEL:
      setpointInches = 0;
      break;
    case CARGO_SHIP_BALL:
      setpointInches = 23.25;
      break;
    case CARGO_LOADER_BALL:
      setpointInches = 19.5;
      break;
    default:
      break;
    }

    errorSum = 0;
    derivative = 0;
    isFinished = false;
    counter = 0;
  }

  @Override
  protected void execute() {
    currentPosition = Robot.elevator.lift.getLiftPosition();
    SmartDashboard.putNumber("Elevator position", currentPosition);
    error = setpointInches - currentPosition;
    if (Math.abs(error) < 5) {
      errorSum += (error * 0.2);
    } else {
      errorSum = 0;
    }

    derivative = (error - previousError);

    if (error > 0) {
      kP = 0.0675; // 0.0675
      kI = 0.035;
      kD = 0.2;
    }

    if (error < 0) {
      kP = 0.02;
      kI = 0.001;
      kD = 0;
    }

    if (Math.abs(error) >= 2) {
      elevatorMovement = (kP * error) + (kI * errorSum) - (kD * derivative);

    }

    if (Math.abs(error) < 2) {
      elevatorMovement = (kP * error) + (kI * errorSum) + (kD * derivative);
    }

    // if(inZone()){
    // Robot.elevator.lift.shiftBrake(BrakeState.ON);
    // }
    // else{
    // Robot.elevator.lift.shiftBrake(BrakeState.OFF);
    // }

    Robot.elevator.lift.setLiftPower(elevatorMovement);
    previousError = error;
    SmartDashboard.putNumber("Elevator PID movement", elevatorMovement);
    SmartDashboard.putNumber("Elevator PID Error", error);
    SmartDashboard.putNumber("Elevator PID Setpoint", setpointInches);

  }

  private boolean inZone() {
    return Math.abs(error) < 0.4;
  }

  @Override
  protected boolean isFinished() {

    // if(error > 0){
    // if(Math.abs(error) < 0.5){
    // return true;
    // }
    // }

    // if(error < 0){
    // if(Math.abs(error) < 0.5){
    // return true;
    // }
    // }

    if (inZone()) {
      return true;
    }

    // if (isFinished == true){
    // System.out.println("TIMEEEEE AHHH");
    // return true;
    // }

    // if(RobotConstants.ELEVATOR_MAX_ENCODER_TICKS < (setpointInches +
    // currentPosition)){
    // System.out.println("Elevator setpoint is too high dude, aborting");
    // return true;
    // }

    // if(currentPosition < RobotConstants.ELEVATOR_MAX_ENCODER_TICKS -
    // RobotConstants.ELEVATOR_STOP_THRESHOLD){
    // return true;
    // }

    // if (currentPosition > RobotConstants.ELEVATOR_MIN_ENCODER_TICKS +
    // RobotConstants.ELEVATOR_STOP_THRESHOLD){
    // return true;
    // }

    return false;

  }

  @Override
  protected void end() {
    Robot.elevator.lift.shiftBrake(BrakeState.ON);
    Robot.elevator.lift.setLiftPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
