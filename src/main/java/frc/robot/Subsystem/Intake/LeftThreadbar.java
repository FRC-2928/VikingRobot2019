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
  
  //encoder setup
  // public Encoder leftEncoder;

  public LeftThreadbar(){
    
    //Setting up the threadbar motor
    leftThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_LEFT_THREADBAR);
    leftThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
    System.out.println(leftThreadbarMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10));
  

    // leftEncoder = new Encoder(0, 1, false, EncodingType.k4X);

  }

  public void setLeftPower(double power){

    leftThreadbarMotor.set(ControlMode.PercentOutput, power);

  }

  public void getLeftEncoder(){

      leftThreadbarMotor.getSelectedSensorPosition();
      System.out.println(leftThreadbarMotor.getSelectedSensorPosition());
      SmartDashboard.putNumber("Left encoder", leftThreadbarMotor.getSelectedSensorPosition());

  }

  // public void setLeftEncoderTicks(double intake){
  //   intake = leftEncoder.get();
  //   System.out.println(leftEncoder.get());
  // }

  // public void getLeftEncoder(){

  //   leftEncoder.get();

  // }

  // public void resetLeftEncoder(){

  //   leftEncoder.reset();

  // }

  @Override
  public void initDefaultCommand() {
  }
}
