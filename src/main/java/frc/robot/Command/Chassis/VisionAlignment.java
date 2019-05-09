package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class VisionAlignment extends Command {

  private NetworkTable table;
  private double XSetpoint;
  private double XOutput;
  private double errorSum;


  public VisionAlignment() {
    requires(Robot.chassis.drivetrain);
  }

  @Override
  protected void initialize() {

    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);

  }

  @Override
  protected void execute() {

    NetworkTableEntry tx = table.getEntry("tx");
    XSetpoint = tx.getDouble(0.0);

    if(Math.abs(XSetpoint) < 5){
      errorSum += (XSetpoint * 0.2);
    }
    else{
      errorSum = 0;
    }

    double kP = 0.2;
    double kI = 0.01;
    
    XOutput = (kP * XSetpoint) + (kI * errorSum);
    SmartDashboard.putNumber("Vision alignment X output", XOutput);

    // Robot.chassis.drivetrain.drive(0, XOutput);
    
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
    Robot.chassis.drivetrain.drive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
