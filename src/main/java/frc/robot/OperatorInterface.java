package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Command.Intake.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Command.Chassis.Shift;
import frc.robot.Command.Elevator.*;
import frc.robot.Command.Elevator.SetElevator.LiftState;
// import frc.robot.Command.GroundIntake.RunGroundIntake;
// import frc.robot.Command.GroundIntake.SetPusher;
import frc.robot.Command.Endgame.RunFourBar;
import frc.robot.Command.Endgame.ClimbWithFourBar;
import frc.robot.Subsystem.Chassis.Transmission;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;
// import frc.robot.Subsystem.GroundIntake.Pusher.PusherState;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

public class OperatorInterface {

    // Driving
    private static final Joystick driveStick = new Joystick(0);
    private static final Joystick operatorConsole = new Joystick(1);
    private static final Joystick driverConsole = new Joystick(2);

    private static final JoystickButton gearButtonLow = new JoystickButton(driveStick, 9);
    private static final JoystickButton gearButtonHigh = new JoystickButton(driveStick,10);

    OperatorInterface() {
        gearButtonLow.whenPressed(new Shift(Transmission.GearState.LOW));
        gearButtonHigh.whenPressed(new Shift(Transmission.GearState.HIGH));
    }

    public double getDriveY() {
        return driveStick.getY();
    }

    public double getDriveX() {
        return driveStick.getX();
    }

    public double getDriveZ() {
        return driveStick.getZ();
    }

    public double getSlider(){
        return driveStick.getThrottle();
    }

    private ArmState getArmState() {
        return Robot.intake.armPresets.getArmState();
    }
}
