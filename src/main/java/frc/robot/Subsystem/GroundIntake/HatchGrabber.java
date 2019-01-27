package frc.robot.Subsystem.GroundIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchGrabber extends Subsystem {
  private WPI_TalonSRX groundmotor;
  //DigitalInput bottomLimitSwitch; 
  //DigitalInput topLimitSwitch; 
  
    public HatchGrabber(){
      
    groundmotor = new WPI_TalonSRX(RobotMap.TALON_GROUND_HATCH);
    //groundmotor.setNeutralMode(WPI_TalonSRX.motorcontrol.setNeutralMode.Brake);
    }
    // public void Init(){
  
    //   bottomLimitSwitch = new DigitalInput(1);
    //   topLimitSwitch = new DigitalInput(2);
  
    //}
  
    public void setpower(double power){
  
      groundmotor.set(ControlMode.PercentOutput, power);
  
    }
  
    // public void setlimit(boolean limit, boolean upOrDown){
      
    //   if(upOrDown = true){
  
    //     limit = topLimitSwitch.get(); // *possibly incorrect?
  
    //   }else{
  
    //     limit = bottomLimitSwitch.get(); // *possibly incorrect?
  
    //   }
  
    // }
  
    @Override
    public void initDefaultCommand() {
      // Set the default command for a subsystem here.
      // setDefaultCommand(new MySpecialCommand());
    }
  }