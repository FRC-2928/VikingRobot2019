package frc.robot.Subsystem.Intake;

import static java.lang.System.currentTimeMillis;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;

public class Drawbridge extends Subsystem {
  private Solenoid drawbridgeUp;
  private Solenoid drawbridgeDown;
  public DrawbridgeState bridgeState;
  private double lastChange = 0;

  public enum DrawbridgeState {
    UP, DOWN;
  }

  public DrawbridgeState getDrawbridgeState() {
    return bridgeState;
  }

  public Drawbridge() {
    drawbridgeUp = new Solenoid(RobotMap.SOLENOID_INTAKE_DRAWBRIDGE_UP);
    drawbridgeDown = new Solenoid(RobotMap.SOLENOID_INTAKE_DRAWBRIDGE_DOWN);
    bridgeState = DrawbridgeState.UP;
  }

  public void switchBridge(DrawbridgeState state) {
    double time = currentTimeMillis();
    if ((time - lastChange) > RobotConstants.DRAWBRIDGE_DELAY_MS) {

      switch (state) {
        case UP:  
        drawbridgeUp.set(false);
        drawbridgeDown.set(true);
        break;

        case DOWN:
        drawbridgeUp.set(true);
        drawbridgeDown.set(false);
        break;
        default:
          break;
      }

      lastChange = time;
      bridgeState = state;
    }
  }

  @Override
  public void initDefaultCommand() {
  }
}
