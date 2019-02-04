package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Command.Intake.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Command.GroundIntake.*;
// import frc.robot.Command.Intake.RunLeftThreadbar;
// import frc.robot.Command.Intake.RunRightThreadbar;
import frc.robot.Command.Chassis.Shift;
import frc.robot.Subsystem.Chassis.Transmission;
import frc.robot.Subsystem.GroundIntake.Pusher.PusherState;

public class OperatorInterface {

    private static final Joystick driveStick = new Joystick(0);
    private static final Joystick operatorConsole = new Joystick(1);

    private static final JoystickButton gearButtonHigh = new JoystickButton(operatorConsole , 2);
    private static final JoystickButton gearButtonLow = new JoystickButton(operatorConsole, 3);
    //placeholder1
    private static final JoystickButton groundButtonUp = new JoystickButton(driveStick, 3);
    private static final JoystickButton groundButtonDown = new JoystickButton(driveStick, 4);
    private static final JoystickButton pusherButtonIn = new JoystickButton(driveStick, 5);
    private static final JoystickButton pusherButtonOut = new JoystickButton(driveStick, 6);

    private static final JoystickButton VisionButton = new JoystickButton(driveStick, 7);
    private static final JoystickButton PIDButton = new JoystickButton(driveStick, 8);

    private static final JoystickButton threadbarLeftLeft = new JoystickButton(driveStick, 9);
    private static final JoystickButton threadbarLeftRight = new JoystickButton(driveStick, 10);
    private static final JoystickButton threadbarLeft = new JoystickButton(driveStick, 11);
    private static final JoystickButton threadbarRight = new JoystickButton(driveStick, 12);
    private static final JoystickButton intake = new JoystickButton(operatorConsole, 1);
    private static final JoystickButton outtake = new JoystickButton(driveStick, 2);

    OperatorInterface() {

        gearButtonHigh.whenPressed(new Shift(Transmission.GearState.HIGH));
        gearButtonLow.whenPressed(new Shift(Transmission.GearState.LOW));

        groundButtonUp.whileHeld(new RunGroundIntake(-0.6));
        groundButtonDown.whileHeld(new RunGroundIntake(0.6));
        pusherButtonIn.whenPressed(new SetPusher(PusherState.IN));
        pusherButtonOut.whenPressed(new SetPusher(PusherState.OUT));

        //Left: - for moving left, + for moving right
        //Right: - for moving left, + for moving right
        threadbarLeft.whileHeld(new RunLeftThreadbar(-0.8));
        threadbarLeft.whileHeld(new RunRightThreadbar(-0.8));
        threadbarRight.whileHeld(new RunLeftThreadbar(0.8));
        threadbarRight.whileHeld(new RunRightThreadbar(0.8));
        threadbarLeftLeft.whileHeld(new RunLeftThreadbar(-0.8));
        threadbarLeftRight.whileHeld(new RunLeftThreadbar(0.8));

        intake.whileHeld(new RunWheels(0.95));
        outtake.whileHeld(new RunWheels(-0.95));

        //Testing commands
        VisionButton.whileHeld(new VisionSetThreadbar());
        PIDButton.whileHeld(new ThreadbarDistancePID(50000, .2, .002));
    }

    
    public double getDriveY() {
        return driveStick.getY(); 
    }

    public double getDriveX() {
        return driveStick.getX();
    }
}

