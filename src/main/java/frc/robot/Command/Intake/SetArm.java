package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;

public class SetArm extends Command {
  private double leftPreviousPosition;
  private double rightPreviousPosition;
  private double setpointLeft;
  private double setpointRight;
  private double currentPositionLeft;
  private double currentPositionRight;
  private double errorLeft;
  private double errorRight;
  private double outputLeft;
  private double outputRight;
  private ArmState target;
  public ArmState currentState;

  public SetArm(ArmState state) {
    this.target = state;
    requires(Robot.intake.threadbar);

  }

  @Override
  protected void initialize() {
    leftPreviousPosition = Robot.intake.threadbar.getLeftThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    rightPreviousPosition = Robot.intake.threadbar.getRightThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    currentState = Robot.intake.armPresets.currentState;

    // Left positive, right negative
    switch (target) {
    case HATCH:
      setpointLeft = RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH; //  +
                                                                   // RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH;
      setpointRight = RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH;// -
                                                                   // RobotConstants.THREAD_ENCODER_TICKS_TO_HATCH;
      break;

    case BALL:
      setpointLeft = -RobotConstants.THREAD_ENCODER_TICKS_TO_BALL;
      setpointRight = -RobotConstants.THREAD_ENCODER_TICKS_TO_BALL;
      break;

    }
    if (target != currentState) {
      Robot.intake.armPresets.toggle();
    }

    // SmartDashboard.putString("Current State",
    // Robot.intake.armPresets.currentState.toString());
    SmartDashboard.putNumber("Arm State Left Previous Position", leftPreviousPosition);
    SmartDashboard.putNumber("Arm State Right Previous Position", rightPreviousPosition);
    SmartDashboard.putNumber("Arm State Left Setpoint", setpointLeft);
    SmartDashboard.putNumber("Arm State Right Setpoint", setpointRight);

  }

  @Override
  protected void execute() {
    currentPositionLeft = Robot.intake.threadbar.getLeftThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    currentPositionRight = Robot.intake.threadbar.getRightThreadbarEncoder()
        / RobotConstants.THREAD_ENCODER_TICKS_PER_INCH;
    errorLeft = setpointLeft - currentPositionLeft;
    errorRight = setpointRight - currentPositionRight;
    
    double kP = 0.35; // Normally 0.5, testing rn
    double min_Command = 0.1;
    double kI = 0.01;
    // Very basic P, will expand later but need to test it first
    if (Math.abs(errorLeft) >= 1) {
      outputLeft = errorLeft * kP;
    }

    if (Math.abs(errorLeft) < 1) {

      double errorSumLeft =+ errorLeft * 0.2;
      outputLeft = (errorLeft * kP) + (min_Command * errorLeft) + (kI * errorSumLeft);
    }

    if (Math.abs(errorRight) >= 1) {
      outputRight = errorRight * kP;
    }

    if (Math.abs(errorRight) < 1) {
      double errorSumRight = +errorRight * 0.2;
      outputRight = (errorRight * kP) + (min_Command * errorRight) + (kI * errorSumRight);
    }

    Robot.intake.threadbar.setLeftThreadbarPower(outputLeft);
    Robot.intake.threadbar.setRightThreadbarPower(-outputRight);

    SmartDashboard.putNumber("Arm State current position left", currentPositionLeft);
    SmartDashboard.putNumber("Arm State current position right", currentPositionRight);
    SmartDashboard.putNumber("Arm State Error Left", errorLeft);
    SmartDashboard.putNumber("Arm State Error Right", errorRight);
    SmartDashboard.putNumber("Arm State Output Left", outputLeft);
    SmartDashboard.putNumber("Arm State Output Right", outputRight);
    Robot.intake.armPresets.getArmState();
  }

  @Override
  protected boolean isFinished() {
    SmartDashboard.putNumber("Is Finished error left", errorLeft);
    SmartDashboard.putNumber("Is Finished error right", errorRight);
    return Math.abs(errorLeft) < 0.2 && Math.abs(errorRight) < 0.2;
  }

  @Override
  protected void end() {
    Robot.intake.threadbar.setLeftThreadbarPower(0);
    Robot.intake.threadbar.setRightThreadbarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}