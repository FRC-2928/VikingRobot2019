package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//Using the limelight to see vision tape on rocket
//We're then bring the threadbar over

public class VisionSetThreadbar extends Command {
      //Setting up Limelight variables
      NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      NetworkTableEntry ta = table.getEntry("ta");
  
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double area = ta.getDouble(0.0);
  

  
  public VisionSetThreadbar() {
    SmartDashboard.putNumber("X, Limelight", x);
    SmartDashboard.putNumber("Y, Limelight", y);
    SmartDashboard.putNumber("Area, Limelight", area);

    //Set up LEDs, 0 = current pipeline, 1 = off, 2 = blink, 3 = on
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double threadbar_Movement = 0;
    // Based on the assumption that we're using the right vision tape, requires more testing
    // Convert everything to inches, then to encoder ticks
    double currentPositionInches = Robot.intake.leftThreadbar.leftEncoderPosition / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    double errorInches = currentPositionInches - (x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES);

    
    
    Robot.intake.leftThreadbar.setLeftPower(threadbar_Movement);
    Robot.intake.rightThreadbar.setRightPower(threadbar_Movement);
  }

  @Override
  protected boolean isFinished() {
    if (x < 0.2){
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    Robot.intake.leftThreadbar.setLeftPower(0);
    Robot.intake.rightThreadbar.setRightPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
