package frc.robot.Subsystem.Intake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Drawbridge extends Subsystem {
  private Solenoid drawbridgeMotor;

  public enum DrawbridgeState{

    UP,
    DOWN;

  }

  public Drawbridge(){
    drawbridgeMotor = new Solenoid(RobotMap.SOLENOID_INTAKE_DRAWBRIDGE);
  }

  @Override
  public void initDefaultCommand() {
  }
}
