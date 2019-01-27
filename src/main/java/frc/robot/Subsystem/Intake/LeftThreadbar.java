package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


public class LeftThreadbar extends Subsystem {
  
  //motor setup
  public WPI_TalonSRX leftThreadbarMotor;
  
  //encoder setup
  public Encoder leftEncoder;

  public LeftThreadbar(){
    
    //Setting up the threadbar motor
    leftThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_LEFT_THREADBAR);

    leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

  }




  public void setLeftPower(double power){

    leftThreadbarMotor.set(ControlMode.PercentOutput, power);

  }

  public void setLeftEncoderTicks(double intake){
    intake = leftEncoder.get();
    System.out.println(leftEncoder.get());
  }

  public void getLeftEncoder(){

    leftEncoder.get();

  }

  public void resetLeftEncoder(){

    leftEncoder.reset();

  }

  @Override
  public void initDefaultCommand() {
  }
}
