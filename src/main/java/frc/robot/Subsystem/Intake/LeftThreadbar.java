package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class LeftThreadbar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  //motor setup
  public WPI_TalonSRX leftThreadbarMotor;

  public LeftThreadbar(){
    
    leftThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_LEFT_THREADBAR);

  }

  //encoder setup
  Encoder leftEncoder;
  //DigitalInput leftThreadbarInnerLimit; //limitswitch on inside of the left threadbar arm
  //DigitalInput leftThreadbarOuterLimit; //limitswitch on outside of the left threadbar arm
  
  public void Init(){

    leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    //leftThreadbarInnerLimit = new DigitalInput(1);
    //leftThreadbarOuterLimit = new DigitalInput(2);

  }

  public void setLeftPower(double power){

    leftThreadbarMotor.set(ControlMode.PercentOutput, power);

  }

  public void setLeftEncoderTicks(double intake){

    intake = leftEncoder.get();

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
