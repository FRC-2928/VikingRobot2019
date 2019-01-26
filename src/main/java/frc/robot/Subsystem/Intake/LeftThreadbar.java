/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystem.Intake;

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
  DigitalInput leftThreadbarInnerLimit; //limitswitch on inside of the left threadbar arm
  DigitalInput leftThreadbarOuterLimit; //limitswitch on outside of the left threadbar arm

  
  public void Init(){

    leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    leftThreadbarInnerLimit = new DigitalInput(1);
    leftThreadbarOuterLimit = new DigitalInput(2);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
