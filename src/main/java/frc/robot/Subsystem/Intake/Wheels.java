package frc.robot.Subsystem.Intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Command.Intake.RunWheels;

/**
 * Runs wheels at a certain power
 */
public class Wheels extends Subsystem {
 
  public VictorSPX leftWheel;
  public VictorSPX rightWheel;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunWheels());
  }

  public Wheels(){
    
    leftWheel = new VictorSPX(RobotMap.TALON_LEFT_INTAKE);
    rightWheel = new VictorSPX(RobotMap.TALON_RIGHT_INTAKE);

  }

  public void setWheelPower(double power){

    rightWheel.set(ControlMode.PercentOutput, power);
    leftWheel.set(ControlMode.PercentOutput, power);

  }
}
