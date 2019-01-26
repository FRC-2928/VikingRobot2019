package frc.robot.Subsystem.GroundIntake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class HatchGrabber extends Subsystem {
private WPI_VictorSPX groundmotor;

  public HatchGrabber(){
    groundmotor = new WPI_VictorSPX(RobotMap.VICTOR_GROUND_HATCH);
    groundmotor.setNeutralMode(com.ctre.WPI_VictorSPx.motorcontrol.NeutralMode.Brake)
  }

  public void setpower(double power){

    groundmotor.set(ControlMode.PercentOutput, power);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
