package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class RightThreadbar extends Subsystem {
  public WPI_TalonSRX rightThreadbarMotor;

  public RightThreadbar(){
    
    rightThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_RIGHT_THREADBAR);
    rightThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
    

    
  }

  public void setRightPower(double power){

    rightThreadbarMotor.set(ControlMode.PercentOutput, power);

  }

  public void getRightEncoder(){

    rightThreadbarMotor.getSelectedSensorPosition();
    SmartDashboard.putNumber("Right Encoder", rightThreadbarMotor.getSelectedSensorPosition());

  }

  @Override
  public void initDefaultCommand() {
  }
}
