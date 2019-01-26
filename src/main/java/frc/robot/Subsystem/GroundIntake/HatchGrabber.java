package frc.robot.Subsystem.GroundIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class HatchGrabber extends Subsystem {
private WPI_VictorSPX groundmotor;

  public HatchGrabber(){
    groundmotor = new WPI_VictorSPX(RobotMap.VICTOR_GROUND_HATCH);

  }

  DigitalInput bottomLimitSwitch; 
  DigitalInput topLimitSwitch; 

  public void Init(){

    bottomLimitSwitch = new DigitalInput(1);
    topLimitSwitch = new DigitalInput(2);

  }

  public void setpower(double power){

    groundmotor.set(ControlMode.PercentOutput, power);

  }

  public void setlimit(boolean limit, boolean upOrDown){
    
    if(upOrDown = true){

      limit = topLimitSwitch.get();

    }else{

      limit = bottomLimitSwitch.get();

    }

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
