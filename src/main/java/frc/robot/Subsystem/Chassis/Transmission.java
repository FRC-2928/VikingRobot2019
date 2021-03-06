package frc.robot.Subsystem.Chassis;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
import static java.lang.System.currentTimeMillis;
import static frc.robot.Subsystem.Chassis.Transmission.GearState.HIGH;
import static frc.robot.Subsystem.Chassis.Transmission.GearState.LOW;

public class Transmission extends Subsystem {
    private final Solenoid shiftSolenoid;
    private GearState currentState;

    private long lastShift = 0;

    public enum GearState {
        HIGH, LOW;
    }

    public Transmission(){
        shiftSolenoid = new Solenoid(RobotMap.SOLENOID_TRANSMISSION);
    }

    public void shift(GearState state) {
        long time = currentTimeMillis();
        if ((time - lastShift) > RobotConstants.SHIFT_DELAY_MS) {
            if (state == HIGH) {
                Robot.chassis.drivetrain.setBrakeMode(false);
                shiftSolenoid.set(true);
            }
            if (state == LOW) {
                Robot.chassis.drivetrain.setBrakeMode(true);                
                shiftSolenoid.set(false);
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
