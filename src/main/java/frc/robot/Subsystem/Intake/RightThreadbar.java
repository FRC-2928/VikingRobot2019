package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class RightThreadbar extends Subsystem {
  //Setting up right motor
  public WPI_TalonSRX rightThreadbarMotor;
  //Setting up distance PID
  public double rightEncoderPosition;
  private double errorSum;
  public boolean rightInPosition;
  private double error;

  public RightThreadbar(){
    
    //Mapping motor to correct talon
    rightThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_RIGHT_THREADBAR);
    //Setting up encoder
    rightThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
    //Setting it up so + is right and - is left
    rightThreadbarMotor.setInverted(true);
    rightEncoderPosition = rightThreadbarMotor.getSelectedSensorPosition(); 
    this.error = 0;
    
  }

  public void setRightPower(double power){

    rightThreadbarMotor.set(ControlMode.PercentOutput, power);
    SmartDashboard.putNumber("Right Threadbar power", power);

  }

  public void getRightEncoder(){

    rightThreadbarMotor.getSelectedSensorPosition();
    SmartDashboard.putNumber("Right Encoder", rightThreadbarMotor.getSelectedSensorPosition());

  }
  
  public void resetRightEncoder(){
    rightThreadbarMotor.setSelectedSensorPosition(0);
  }

  public void setRightPosition(double rightSetpoint, double Kp, double Ki){

    getRightEncoder();
    rightInPosition = false;
    error = rightSetpoint - rightEncoderPosition; 
    this.errorSum += error;
    double output = Kp * error + Ki * errorSum;
    rightThreadbarMotor.set(ControlMode.PercentOutput, output);

    SmartDashboard.putNumber("Right error", error);
    SmartDashboard.putNumber("Right output", output);
    
    if (Math.abs(error) <= 10000){

      rightInPosition = true;
      SmartDashboard.putBoolean("Right In Position", rightInPosition);

    }


  }


  @Override
  public void initDefaultCommand() {
  }
}
