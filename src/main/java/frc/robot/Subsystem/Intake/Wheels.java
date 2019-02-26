/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * Runs wheels at a certain power
 */
public class Wheels extends Subsystem {
 
  public VictorSPX leftWheel;
  public VictorSPX rightWheel;

  public Wheels(){
        
    leftWheel = new VictorSPX(RobotMap.TALON_LEFT_INTAKE);
    rightWheel = new VictorSPX(RobotMap.TALON_RIGHT_INTAKE);
    leftWheel.setInverted(true);

  }

  public void setWheelPower(double power){

    rightWheel.set(ControlMode.PercentOutput, power);
    leftWheel.set(ControlMode.PercentOutput, power);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
