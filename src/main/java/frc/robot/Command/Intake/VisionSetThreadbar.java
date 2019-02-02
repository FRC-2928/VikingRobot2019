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

/*TO DO:
Read both left and right encoders, and cross reference (currently reading only left side)
Tune the various constants

*/

public class VisionSetThreadbar extends Command {
      //Setting up Limelight variables
      NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      NetworkTableEntry ta = table.getEntry("ta");
  
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double area = ta.getDouble(0.0);
  

  public double getVisionError(){
    
    double setpointInches = x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES;
    double positionInches = Robot.intake.leftThreadbar.leftEncoderPosition / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    double errorInches = setpointInches - positionInches;
    return errorInches;
  }
  
  public VisionSetThreadbar() {

    // SmartDashboard.putNumber("X, Limelight", x);
    // SmartDashboard.putNumber("Y, Limelight", y);
    // SmartDashboard.putNumber("Area, Limelight", area);

    // //Set up LEDs, 0 = current pipeline, 1 = off, 2 = blink, 3 = on
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
  }

  @Override
  protected void initialize() {
    // SmartDashboard.putNumber("X, Limelight", x);
    SmartDashboard.putNumber("Y, Limelight", y);
    SmartDashboard.putNumber("Area, Limelight", area);

    //Set up LEDs, 0 = current pipeline, 1 = off, 2 = blink, 3 = on
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
  }

  @Override
  protected void execute() {
    double threadbarInches = 0;
    // Based on the assumption that we're looking at both vision tapes, and finding an average in between
    // Convert everything to inches, then to encoder ticks
    double currentPositionInches = Robot.intake.leftThreadbar.leftEncoderPosition / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    double desiredSetpoint = x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES;
    double errorInches = desiredSetpoint - currentPositionInches;
    
    //kP is multiplied by error to get the power
    //min_Command helps give a little extra power when the threadbar is close
    double kP = 0.5;
    double min_Command = 0.05;

    //Setting up how much power we're giving the threadbars
    if(errorInches > 1){

      threadbarInches = kP * errorInches - min_Command * errorInches;

    }

    if(errorInches < 1){

      threadbarInches = kP * errorInches + min_Command * errorInches; 

    }
    
    double threadbar_Movement = threadbarInches * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    
    Robot.intake.leftThreadbar.setLeftPower(threadbar_Movement);
    //Robot.intake.rightThreadbar.setRightPower(threadbar_Movement);
    SmartDashboard.putNumber("X, Limelight", x);
    SmartDashboard.putNumber("Left Threadbar current position inches", currentPositionInches);
    SmartDashboard.putNumber("Limelight desired setpoint", desiredSetpoint);
    SmartDashboard.putNumber("Limelight error", errorInches);
  }

  @Override
  protected boolean isFinished() {
    //Stops if within 0.5 inches
    if ( Math.abs(getVisionError()) < 0.5){
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

