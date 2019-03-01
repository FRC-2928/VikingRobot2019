package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 -Subsystem not yet implemented, planning to be a merger of left and right threadbar
 -Will make it easier on everything else ot have both threadbars in one subsystem
 */
public class Threadbar extends Subsystem {
  public WPI_TalonSRX leftThreadbarMotor;
  public WPI_TalonSRX rightThreadbarMotor;

  Threadbar(){
    leftThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_LEFT_THREADBAR);
    rightThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_RIGHT_THREADBAR);
    leftThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    rightThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);

    }

  public void setThreadbarPower(double powerLeft, double powerRight){
    leftThreadbarMotor.set(ControlMode.PercentOutput, powerLeft);
    rightThreadbarMotor.set(ControlMode.PercentOutput, powerRight);
  }

  public boolean getLeftThreadbarLimitSwitch(){
    boolean leftLimit = leftThreadbarMotor.getSensorCollection().isFwdLimitSwitchClosed();
    return leftLimit;
  }

  public boolean getRightThreadbarLimitSwitch(){
    boolean rightLimit = rightThreadbarMotor.getSensorCollection().isFwdLimitSwitchClosed();
    return rightLimit;
  }

  public void setLeftThreadbarPower(double power){
    leftThreadbarMotor.set(ControlMode.PercentOutput, power);
  }

  public void setRightThreadbarPower(double power){
    rightThreadbarMotor.set(ControlMode.PercentOutput, power);
  }

  public double getLeftThreadbarEncoder(){
    SmartDashboard.putNumber("Left Threadbar encoder", leftThreadbarMotor.getSelectedSensorPosition());
    return leftThreadbarMotor.getSelectedSensorPosition();
  }

  public double getRightThreadbarEncoder(){
    SmartDashboard.putNumber("Left Threadbar encoder", rightThreadbarMotor.getSelectedSensorPosition());
    return rightThreadbarMotor.getSelectedSensorPosition();
  }

  public void resetThreadbarEncoders(){
    leftThreadbarMotor.setSelectedSensorPosition(0);
    rightThreadbarMotor.setSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
