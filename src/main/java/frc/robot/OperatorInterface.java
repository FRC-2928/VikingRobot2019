package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.Command.GroundIntake.*;
import frc.robot.Command.Chassis.Shift;
import frc.robot.Subsystem.Chassis.Transmission;

public class OperatorInterface {

    private static final Joystick driveStick = new Joystick(0);
    // private static final double Joy_Deadzone = 0.05;

    private static final JoystickButton gearButton = new JoystickButton(driveStick, 9);
    //placeholder1
    private static final JoystickButton groundButtonUp = new JoystickButton(driveStick, 1234);
    private static final JoystickButton groundButtonDown = new JoystickButton(driveStick, 1234);

    OperatorInterface() {

        gearButton.whenPressed(new Shift(Transmission.GearState.LOW));
        gearButton.whenReleased(new Shift(Transmission.GearState.HIGH));
        groundButtonUp.whenPressed(new RunGroundIntake(0.2, true, true));
        groundButtonDown.whenPressed(new RunGroundIntake(-0.2, true, false));

    }

    
    public double getDriveY() {
        return -driveStick.getY(); 
    }

    public double getDriveX() {
        return driveStick.getX();
    }
}