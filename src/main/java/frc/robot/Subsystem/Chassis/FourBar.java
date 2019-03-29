package frc.robot.Subsystem.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/** 
 * Four bar system for climbing
 */
public class FourBar extends Subsystem {
  private CANSparkMax fourBarMotor;
  private CANEncoder fourBarEncoder;
  public boolean stopDefaultFourBar;

  public FourBar() {
    fourBarMotor = new CANSparkMax(RobotMap.SPARK_FOUR_BAR, MotorType.kBrushless);
    fourBarEncoder = fourBarMotor.getEncoder();
    fourBarMotor.setOpenLoopRampRate(0.75);
  }

  public void setFourBarPower(double power) {
    fourBarMotor.set(power);
  }

  public void setDefaultFourBar(boolean state){
    stopDefaultFourBar = state;
  }

  public boolean getDefaultFourBarState(){
    return stopDefaultFourBar;
  }

  public double getFourBarPosition() {
    SmartDashboard.putNumber("FourBar position", fourBarEncoder.getPosition());
    return fourBarEncoder.getPosition();
  }

  @Override
  public void initDefaultCommand() {
  }
}