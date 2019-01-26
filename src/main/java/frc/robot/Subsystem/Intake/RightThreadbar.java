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
public class RightThreadbar extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX rightThreadbarMotor;

  public RightThreadbar(){
    
    rightThreadbarMotor = new WPI_TalonSRX(RobotMap.TALON_RIGHT_THREADBAR);

  }

  Encoder rightEncoder;
  DigitalInput rightThreadbarInnerLimit; //limitswitch on inside of the right threadbar arm
  DigitalInput rightThreadbarOuterLimit; //limitswitch on outside of the right threadbar arm

  
  public void Init(){

    rightEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

    rightThreadbarInnerLimit = new DigitalInput(1);
    rightThreadbarOuterLimit = new DigitalInput(2);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
