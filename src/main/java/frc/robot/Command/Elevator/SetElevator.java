package frc.robot.Command.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

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
  private double kI;
  private double kD;
  private double derivative;
  private double errorSum;
  private double previousError;
  private double oldError;
  private int counter = 0;
  private double mainTime;
  private ArmState armState;

  public enum LiftState { 
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
        setpointInches = 8;
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
        setpointInches = 49;
      } else {
        setpointInches = 49;
      }
      break;
    case GROUND_LEVEL:
      setpointInches = 0;
      if (armState == ArmState.BALL){
        Robot.intake.drawbridge.switchBridge(DrawbridgeState.DOWN);
      }
      break;
    case CARGO_SHIP_BALL:
      setpointInches = 23;
      break;
    case CARGO_LOADER_BALL:
      setpointInches = 19.5;
      break;
    default:
      break;
    }

    errorSum = 0;
    derivative = 0;
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
      kP = 0.045; // 0.07
      kI = 0.035;
      kD = 0.4;
    }

    if (error < 0) {
      kP = 0.02;
      kI = 0.015;
      kD = 0;
    }

      elevatorMovement = (kP * error) + (kI * errorSum) - (kD * derivative);


    // if(inZone()){
    // Robot.elevator.lift.shiftBrake(BrakeState.ON);
    // }
    // else{
    // Robot.elevator.lift.shiftBrake(BrakeState.OFF);
    // }

    if (elevatorMovement < -0.4){
      elevatorMovement = -0.4;
    }

    Robot.elevator.lift.setLiftPower(elevatorMovement);
    previousError = error;
    SmartDashboard.putNumber("Elevator PID movement", elevatorMovement);
    SmartDashboard.putNumber("Elevator PID Error", error);
    SmartDashboard.putNumber("Elevator Integral", errorSum * kI);
    SmartDashboard.putNumber("Elevator PID Setpoint", setpointInches);
  }

  private boolean inZone() {
    if(setpointInches == 0){
      return Math.abs(error) < 1;
    }
    else if(error < 0){
      return Math.abs(error) < 2;
    }
    else{
      return Math.abs(error) < 0.5;
    }
  }

  private boolean isStalled(){
    if(setpointInches == 0){
      if (counter == 0) {
            mainTime = currentTimeMillis();
            oldError = error;
            counter = 1;
          }
      if(currentTimeMillis() - mainTime > 750){
        if(Math.abs(error - oldError) < 0.1){
          counter = 0;
          return true;
        }
        counter = 0;
      } 
      return false;
    }
    counter = 0;
    return false;
  }

  @Override
  protected boolean isFinished() {

    if (isStalled() == true){
      Robot.elevator.lift.resetLiftEncoders();
      return true;
    }

    if (inZone()) {
      return true;
    }

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
