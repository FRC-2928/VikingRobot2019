package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Subsystem.Chassis.Transmission.GearState;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionAlignment extends Command {
  private double setpoint;
  private NetworkTable table;
  private double x;
  private double kP;
  private double kI;
  private double kD;
  private double errorSum;
  private double derivative;
  private double previousError;
  private double rotationOutput;
  private GearState currentGear;

  public VisionAlignment() {
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
    x = tx.getDouble(0.0);
    errorSum += x;
    derivative = x - previousError;

    if(currentGear == GearState.LOW){
    kP = 0.03;
    kI = 0.00135;
    kD = 0.045;
    }

    if(currentGear == GearState.HIGH){
    kP = 0.055;
    kI = 0.001;
    }

    rotationOutput = (kP * x) + (kI * errorSum) + (kD *derivative);

    Robot.chassis.drivetrain.drive(0, -rotationOutput);

    SmartDashboard.putNumber("Vision Aligment P", kP*x);
    SmartDashboard.putNumber("Vision Alignment I", kI*errorSum);
    SmartDashboard.putNumber("Vision Alignment derivative",derivative);
    SmartDashboard.putNumber("Vision Alignment D", kD *derivative);
    SmartDashboard.putNumber("Vision Alignment error",x);
    SmartDashboard.putNumber("Vision Alignment Rotation", rotationOutput);
    previousError = x;
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    Robot.chassis.drivetrain.drive(0,0);
    // Robot.chassis.transmission.shift(GearState.HIGH);
  }

  @Override
  protected void interrupted() {
    end();
  }
}