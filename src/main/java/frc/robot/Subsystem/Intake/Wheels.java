/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Wheels extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VictorSPX leftIntake;
  public VictorSPX rightIntake;

  public Wheels(){
        
    leftIntake = new WPI_VictorSPX(RobotMap.VICTOR_LEFT_INTAKE);
    rightIntake = new WPI_VictorSPX(RobotMap.VICTOR_RIGHT_INTAKE);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
