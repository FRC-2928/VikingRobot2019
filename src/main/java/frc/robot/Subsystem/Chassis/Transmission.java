package frc.robot.Subsystem.Chassis;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
import static java.lang.System.currentTimeMillis;
import static frc.robot.Subsystem.Chassis.Transmission.GearState.HIGH;
import static frc.robot.Subsystem.Chassis.Transmission.GearState.LOW;

public class Transmission extends Subsystem {
    private final Solenoid shiftSolenoidHigh;
    private final Solenoid shiftSolenoidLow;
    private GearState currentState;

    private long lastShift = 0;

    public enum GearState {
        HIGH, LOW;
    }

    public Transmission() {
        shiftSolenoidHigh = new Solenoid(RobotMap.SOLENOID_TRANSMISSION_HIGH);
        shiftSolenoidLow = new Solenoid(RobotMap.SOLENOID_TRANSMISSION_LOW);
        shiftSolenoidHigh.set(false);
        shiftSolenoidLow.set(true);
        currentState = LOW;
    }

    public void shift(GearState state) {
        long time = currentTimeMillis();
        if ((time - lastShift) > RobotConstants.SHIFT_DELAY_MS) {
            if (state == HIGH) {
                shiftSolenoidHigh.set(true);
                shiftSolenoidLow.set(false);
            }
            if (state == LOW) {
                shiftSolenoidHigh.set(false);
                shiftSolenoidLow.set(true);
            }
            currentState = state;
            lastShift = time;
        }
        SmartDashboard.putString("Gear", state == GearState.LOW ? "Low" : "High");
    }

    public void toggle() {
        shift(currentState == GearState.LOW ? GearState.HIGH : GearState.LOW);
    }

    public GearState getGear() {
        return currentState;
    }

    @Override
    public void initDefaultCommand() {

    }
}
