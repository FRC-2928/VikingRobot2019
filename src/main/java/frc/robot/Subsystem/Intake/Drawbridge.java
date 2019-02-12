package frc.robot.Subsystem.Intake;

import static java.lang.System.currentTimeMillis;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;

public class Drawbridge extends Subsystem {
  private Solenoid drawbridge;
  public DrawbridgeState bridgeState;

  private double lastChange = 0;

  public enum DrawbridgeState{

    UP,
    DOWN;

    DrawbridgeState toggle(){

      return this.equals(UP) ? DOWN: UP;

    }

  }

  public DrawbridgeState getDrawbridgeState(){
    return bridgeState;
  }

  public Drawbridge(){
    drawbridge = new Solenoid(RobotMap.SOLENOID_INTAKE_DRAWBRIDGE);
  }

  public void switchBridge(DrawbridgeState state){

    double time = currentTimeMillis();
    if ((time - lastChange) > RobotConstants.DRAWBRIDGE_DELAY_MS){

      switch(state){

        case UP:  
        drawbridge.set(true);

        case DOWN:
        drawbridge.set(false);

      }
      lastChange = time;
      bridgeState = state;
    }
  }

  @Override
  public void initDefaultCommand() {
  }
}
