package frc.robot.Subsystem.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/** 
 * Four bar system for climbing
 */
public class FourBar extends Subsystem {
  private WPI_TalonSRX fourBarMotor;

  public FourBar() {
    fourBarMotor = new WPI_TalonSRX(RobotMap.TALON_FOUR_BAR);
    fourBarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    fourBarMotor.configOpenloopRamp(0.75);
  }

  public void setFourBarPower(double power) {
    fourBarMotor.set(ControlMode.PercentOutput, power);
  }

  public double getFourBarPosition() {
    SmartDashboard.putNumber("FourBar position", fourBarMotor.getSelectedSensorPosition());
    return fourBarMotor.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {

  }
}