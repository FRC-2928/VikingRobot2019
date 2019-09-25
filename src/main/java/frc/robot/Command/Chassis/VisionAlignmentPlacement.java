package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionAlignmentPlacement extends Command {
  private double setpoint;
  private NetworkTable table;
  private double x;
  private double y;
  private double kP;
  private double kI;
  private double kD;
  private double errorSum;
  private double derivative;
  private double previousError;
  private double rotationOutput;
  private double driveOutput;
  private GearState currentGear;

  public VisionAlignmentPlacement() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {
    currentGear = Robot.chassis.transmission.getGear();
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
    setpoint = 0;
    errorSum = 0;
    
  }

  @Override
  protected void execute() {
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    y = ty.getDouble(0.0);
    x = tx.getDouble(0.0);
    // if (x < Math.abs(8)){
      errorSum += x;
    // }
    derivative = x - previousError;

    if(currentGear == GearState.LOW){
    kP = 0.045;
    kI = 0.002;
    kD = 0.15;
    }

    if(currentGear == GearState.HIGH){
    kP = 0.05;
    kI = 0.003;
    kD = 0.15;
    }

    if(currentGear == GearState.LOW){
      if(Math.abs(x) < 3){
        if(y == 0){
          driveOutput = 0.2;
        }
        else if(y > -30){
          driveOutput = 0.5;
        }
        else if(y > -10){
          driveOutput = 0.55;
        }
        else if(y > 5){
          driveOutput = 0.7;
        }
        rotationOutput = 0;
        errorSum = 0;
      }
    }  
    
    if(currentGear == GearState.HIGH){
      if(Math.abs(x) < 3){
        if(y == 0){
          driveOutput = 0.2;
        }
        else if(y > -30){
          driveOutput = 0.5;
        }
        else if(y > -10){
          driveOutput = 0.6;
        }
        else if(y > 5){
          driveOutput = 0.7;
        }
        rotationOutput = 0;
        errorSum = 0;
      }
    } 

    rotationOutput = (kP * x) + (kI * errorSum) + (kD *derivative);
    if(currentGear == GearState.HIGH){
      if(rotationOutput > Math.abs(0.8)){
        rotationOutput = 0.8;
      }
    }
    // if(currentGear == GearState.HIGH){
    //   if(rotationOutput > Math.abs(0.6)){
    //     rotationOutput = 0.7;
    //   }
    // }

    Robot.chassis.drivetrain.drive(-driveOutput, -rotationOutput);
    previousError = x;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    // Robot.chassis.transmission.shift(GearState.HIGH);
  }

  @Override
  protected void interrupted() {
    end();
  }
}