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
    //Reading values from the limelight

    
    // Based on the assumption that we're looking at both vision tapes, and finding an average in between
    // Convert everything to inches, then to encoder ticks
    private double currentPositionInchesLeft; //= -Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    private double currentPositionInchesRight;// = Robot.intake.rightThreadbar.getRightEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    private double desiredSetpoint;// = x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES;
    private double errorInchesLeft;// = desiredSetpoint - currentPositionInchesLeft;
    private double errorInchesRight;// = desiredSetpoint - currentPositionInchesRight;
 
    private double threadbarInchesLeft;// = 0;
    private double threadbarInchesRight;// = 0;

  public VisionSetThreadbar() {

    //requires(Robot.intake.leftThreadbar);
    //requires(Robot.intake.rightThreadbar);

    //Set up LEDs, 0 = current pipeline, 1 = off, 2 = blink, 3 = on
    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
  }

  public double getVisionErrorLeft(){
    SmartDashboard.putNumber("Stop error left",errorInchesLeft);
    return errorInchesLeft;
  }

  public double getVisionErrorRight(){
    SmartDashboard.putNumber("Stop error right", errorInchesRight);
    return errorInchesRight;
  }

  @Override
  protected void initialize() {
    

    //Set up LEDs, 0 = current pipeline, 1 = off, 2 = blink, 3 = on
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    //NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
  }

  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);

    currentPositionInchesLeft = -Robot.intake.leftThreadbar.getLeftEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    currentPositionInchesRight = -Robot.intake.rightThreadbar.getRightEncoder() / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    desiredSetpoint = x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES;
    errorInchesLeft = desiredSetpoint - currentPositionInchesLeft;
    errorInchesRight = desiredSetpoint - currentPositionInchesRight;

    
    //kP is multiplied by error to get the power
    //min_Command helps give a little extra power when the threadbar is close
    double kP = 0.4;
    double min_Command = 0.1;

    //Setting up how much power we're giving the threadbars
    if(Math.abs(errorInchesLeft) > 2){

      threadbarInchesLeft = kP * errorInchesLeft;

    }

    if(Math.abs(errorInchesLeft) < 2){

      threadbarInchesLeft = kP * errorInchesLeft + min_Command * errorInchesLeft; 

    }



    if(Math.abs(errorInchesRight) > 2){

      threadbarInchesRight = kP * errorInchesRight;

    }

    if(Math.abs(errorInchesRight) < 2){

      threadbarInchesRight = kP * errorInchesRight + min_Command * errorInchesRight;

    }
    
  
    
    double threadbarMovementLeft = threadbarInchesLeft * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    double threadbarMovementRight = threadbarInchesRight * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;

    Robot.intake.leftThreadbar.setLeftPower(threadbarMovementLeft);
    Robot.intake.rightThreadbar.setRightPower(threadbarMovementRight);
    SmartDashboard.putNumber("X, Limelight", x);
    SmartDashboard.putNumber("Left Threadbar current position inches left", currentPositionInchesLeft);
    SmartDashboard.putNumber("Right Threadbar current position inches right", currentPositionInchesRight);
    SmartDashboard.putNumber("Limelight desired setpoint", desiredSetpoint);
    SmartDashboard.putNumber("Limelight error left", errorInchesLeft);
    SmartDashboard.putNumber("Limelight error right", errorInchesRight);
  }

  @Override
  protected boolean isFinished() {
    //Stops if within 1 inches
    if (Math.abs(getVisionErrorLeft()) < 1){
      SmartDashboard.putString("Threadbar is Finished", "Left side is done");
      return true;
    }
    else if (Math.abs(getVisionErrorRight()) < 1){
      SmartDashboard.putString("Threadbar is Finished", "Right side is done");
      return true;
    }
    else{
      return false;
    }
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

