package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import static java.lang.System.currentTimeMillis;

//Using the limelight to see vision tape on rocket
//We're then bring the threadbar over

public class VisionSetThreadbar extends Command {
  // Reading values from the limelight

  // Based on the assumption that we're looking at both vision tapes, and finding
  // an average in between
  // Convert everything to inches, then to encoder ticks
  // Initiates variables
  private double currentPositionInchesLeft;
  private double currentPositionInchesRight;
  private double desiredSetpoint;
  private double errorInchesLeft;
  private double errorInchesRight;

  private double threadbarInchesLeft;
  private double threadbarInchesRight;
  private double threadbarMovementLeft;
  private double threadbarMovementRight;

  private boolean leftLimitSwitch;
  private boolean rightLimitSwitch;
  private boolean finished;

  private double errorSumLeft;
  private double errorSumRight;
  private long stopTime;

  public VisionSetThreadbar() {

    requires(Robot.intake.threadbar);

  }

  public double getVisionErrorLeft() {
    SmartDashboard.putNumber("Stop error left", errorInchesLeft);
    return errorInchesLeft;
  }

  public double getVisionErrorRight() {
    SmartDashboard.putNumber("Stop error right", errorInchesRight);
    return errorInchesRight;
  }

  @Override
  protected void initialize() {

    System.out.println("If this is firing more than once it's bad");
    finished = false;
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
    errorSumLeft = 0;
    errorSumRight = 0;
    stopTime = 0;
    
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0.0);
    
    // Creates a setpoint that's in the middle of the two arms
    desiredSetpoint = x * RobotConstants.LIMELIGHT_ROCKET_TAPE_INCHES_PER_DEGREES;
    SmartDashboard.putNumber("X, Limelight", x);
  }

  @Override
  protected void execute() {

    // leftLimitSwitch = Robot.intake.threadbar.getLeftThreadbarLimitSwitch();
    // rightLimitSwitch = Robot.intake.threadbar.getRightThreadbarLimitSwitch();

    currentPositionInchesLeft = Robot.intake.threadbar.getLeftThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    currentPositionInchesRight = Robot.intake.threadbar.getRightThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;


    // Creates setpoints for each arm, differs based on Hatch or Ball state
    if (Robot.intake.armPresets.currentState == ArmState.HATCH) {
      errorInchesLeft = desiredSetpoint - currentPositionInchesLeft;
      errorInchesRight = -desiredSetpoint - currentPositionInchesRight;
    }

    if (Robot.intake.armPresets.currentState == ArmState.BALL) {
      errorInchesLeft = (desiredSetpoint - RobotConstants.THREAD_ENCODER_TICKS_TO_BALL) - currentPositionInchesLeft;
      errorInchesRight = (desiredSetpoint + RobotConstants.THREAD_ENCODER_TICKS_TO_BALL) - currentPositionInchesRight;

    }

    // kP is multiplied by error to get the power
    // min_Command helps give a little extra power when the threadbar is close
    double kP = 0.6;
    double min_Command = 0.2;
    double kI = 0.01;

    // Setting up how much power we're giving the threadbars
    if (Math.abs(errorInchesLeft) >= 1) {

      threadbarInchesLeft = kP * errorInchesLeft;

    }

    if (Math.abs(errorInchesLeft) < 1) {

      errorSumLeft =+ errorInchesLeft * 0.2;
      threadbarInchesLeft = kP * errorInchesLeft + min_Command * errorInchesLeft + kI * errorSumLeft;

    }

    if (Math.abs(errorInchesRight) >= 1) {

      threadbarInchesRight = kP * errorInchesRight;

    }

    if (Math.abs(errorInchesRight) < 1) {

      errorSumRight =+ errorInchesRight * 0.2;
      threadbarInchesRight = kP * errorInchesRight + min_Command * errorInchesRight + kI * errorSumRight;

    }

    threadbarMovementLeft = threadbarInchesLeft * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    threadbarMovementRight = threadbarInchesRight * RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;

    // if(leftLimitSwitch == true && (threadbarMovementLeft < 0 ||
    // threadbarMovementRight < 0)){
    // threadbarMovementLeft = 0;
    // threadbarMovementRight = 0;
    // finished = true;
    // } //Not finished, need to confirm the limit switch

    // if(rightLimitSwitch == true && (threadbarMovementLeft > 0 ||
    // threadbarMovementRight > 0)){
    // threadbarMovementLeft = 0;
    // threadbarMovementRight = 0;
    // finished = true;
    // }

    Robot.intake.threadbar.setLeftThreadbarPower(threadbarMovementLeft);
    Robot.intake.threadbar.setRightThreadbarPower(threadbarMovementRight);
    SmartDashboard.putNumber("Left Threadbar current position inches left",currentPositionInchesLeft);
    SmartDashboard.putNumber("Right Threadbar current position inches right",currentPositionInchesRight);
    SmartDashboard.putNumber("Left Threadbar Error", errorInchesLeft);
    SmartDashboard.putNumber("Right Threadbar Error", errorInchesRight);
  }

  private boolean inZone() {
    System.out.println("Threadbar in zone yeet");
    return Math.abs(getVisionErrorLeft()) < 0.1 && Math.abs(getVisionErrorRight()) < 0.3;
  }

  @Override
  protected boolean isFinished() {
    if (inZone()) {
      return true;
    //   if (stopTime == 0) {
    //     System.out.println("111111");
    //     stopTime = currentTimeMillis();
    //   }
    //   if (currentTimeMillis() - stopTime > 500) {
    //     System.out.println("Threadbar isFinished");
    //     return true;
    //   }

    // } else {
    //   System.out.println("222222222222");
    //   stopTime = 0;
    }
    return false;
  }

  @Override
  protected void end() {
    System.out.println("Theadbar has ended ");
    Robot.intake.threadbar.setThreadbarPower(0, 0);
    // NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(1);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
