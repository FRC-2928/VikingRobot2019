package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class LeftThreadbar extends Subsystem {
  
  //motor setup
  public WPI_TalonSRX leftThreadbarMotor;
  //Setting up distance PID
  private double errorSum;
  public double leftEncoderPosition;
  public boolean leftInPosition;
  private double error;


  public LeftThreadbar(){
    
    //Setting up the threadbar motor
    leftThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_LEFT_THREADBAR);
    //Setting up threadbar encoder
    leftThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    leftEncoderPosition = leftThreadbarMotor.getSelectedSensorPosition(); 
    this.error = 0;

  }

  public void setLeftPower(double power){

    leftThreadbarMotor.set(ControlMode.PercentOutput, power);
    SmartDashboard.putNumber("Left Threadbar Power", power);

  }

  public void setLeftPID(double kP, double kI, double kD){

    leftThreadbarMotor.config_kP(0, kP);
    leftThreadbarMotor.config_kI(0, kI);
    leftThreadbarMotor.config_kD(0, kD);

  }
  

  public double getLeftEncoder(){

      double leftPosition = leftThreadbarMotor.getSelectedSensorPosition();
      System.out.println(leftThreadbarMotor.getSelectedSensorPosition());
      SmartDashboard.putNumber("Left encoder", leftThreadbarMotor.getSelectedSensorPosition());
      return leftPosition;

  }

  public void resetLeftEncoder(){
    leftThreadbarMotor.setSelectedSensorPosition(0);
  }

  public void setLeftPosition(double leftSetpoint, double Kp, double Ki){

    // getLeftEncoder();
    // leftInPosition = false;
    // error = leftSetpoint - leftEncoderPosition; 
    // this.errorSum += error;
    // double output = (Kp * error) + (Ki * errorSum);
    // leftThreadbarMotor.set(ControlMode.PercentOutput, output);

    // SmartDashboard.putNumber("Left error", error);
    // SmartDashboard.putNumber("Left output", output);
    // SmartDashboard.putNumber("Left P", Kp * error);
    // SmartDashboard.putNumber("Left I", Ki * error);

    // if (Math.abs(error) <=10000){

    //   leftInPosition = true;
    //   SmartDashboard.putBoolean("leftInPosition", leftInPosition);

    // }

  }

  @Override
  public void initDefaultCommand() {
  }
}
