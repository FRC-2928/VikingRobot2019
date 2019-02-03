package frc.robot.Subsystem.Intake;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ThreadbarLimelight extends Subsystem {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  ThreadbarLimelight(){
 
    
  }

  public double getLimelightX(){

  double LimelightX = x;
  SmartDashboard.putNumber("Limelight X", LimelightX);
  return LimelightX;
  }

  @Override
  public void initDefaultCommand() {

  }
}
