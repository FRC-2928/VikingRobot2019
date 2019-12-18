package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Command.Intake.*;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Command.Chassis.*;
import frc.robot.Command.Elevator.*;
import frc.robot.Command.Elevator.SetElevator.LiftState;
import frc.robot.Subsystem.Chassis.Transmission;
import frc.robot.Subsystem.Elevator.Lift.BrakeState;
// import frc.robot.Subsystem.GroundIntake.Pusher.PusherState;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

public class OperatorInterface {

    // Driving
    private static final XboxController driveStick = new XboxController(0);
    private static final Joystick operatorConsole = new Joystick(1);
    private static final Joystick driverConsole = new Joystick(2);

    private static final JoystickButton gearButton = new JoystickButton(driveStick, 9);
    private static final JoystickButton gearButtonHigh = new JoystickButton(driveStick, 10);

    // Testing autos
    private static final JoystickButton VisionButtonIntake = new JoystickButton(driveStick, 5);
    private static final JoystickButton VisionButtonOuttake = new JoystickButton(driveStick, 6);
    // private static final JoystickButton LifterTest = new
    // JoystickButton(driveStick, 8);

    // Intake
    private ArmState armCurrentState; // true == cargo, false == hatch
    // private static final JoystickButton threadbarLeftLeft = new JoystickButton(driveStick, 3);
    private static final JoystickButton threadbarLeftRight = new JoystickButton(driveStick, 2);
    private static final JoystickButton threadbarLeft = new JoystickButton(driveStick, 4);
    // private static final JoystickButton threadbarRight = new JoystickButton(driveStick, 4);
    private static final JoystickButton threadbarHatch = new JoystickButton(driveStick, 7); // placeholder
    private static final JoystickButton threadbarBall = new JoystickButton(driveStick, 8); // placeholder
    private static final JoystickButton opThreadbar = new JoystickButton(operatorConsole, 9);
    // private static final JoystickButton drawbridgeDown = new JoystickButton(driveStick, 1);


    // Elevator
    private static final JoystickButton elevatorUp = new JoystickButton(driveStick, 3);
    private static final JoystickButton elevatorDown = new JoystickButton(driveStick, 1);

    OperatorInterface() {
        gearButtonHigh.whenPressed(new Shift(Transmission.GearState.HIGH));
        gearButton.whenPressed(new Shift(Transmission.GearState.LOW));

        // groundButtonUp.whileHeld(new RunGroundIntake(0.8));
        // groundButtonDown.whileHeld(new RunGroundIntake(-0.5));
        // pusherButton.whenPressed(new SetPusher(PusherState.IN));
        // pusherButton.whenReleased(new SetPusher(PusherState.OUT));

        // Left: - for moving left, + for moving right
        // Right: - for moving left, + for moving right
        // if(drawbridgeState == DrawbridgeState.DOWN){
        threadbarLeft.whileHeld(new RunLeftThreadbar(-0.8));
        // threadbarLeft.whileHeld(new RunRightThreadbar(-0.8));
        // threadbarRight.whileHeld(new RunLeftThreadbar(0.8));
        // threadbarRight.whileHeld(new RunRightThreadbar(0.8));
        // threadbarLeftLeft.whileHeld(new RunRightThreadbar(-0.8));
        threadbarLeftRight.whileHeld(new RunRightThreadbar(-0.8));
        opThreadbar.whenPressed(new SetArm(ArmState.HATCH));
        opThreadbar.whenReleased(new SetArm(ArmState.BALL));

        threadbarHatch.whenPressed(new SetArm(ArmState.HATCH));
        threadbarBall.whenPressed(new SetArm(ArmState.BALL));

        VisionButtonIntake.whileHeld(new VisionAlignmentIntake());
        VisionButtonIntake.whenReleased(new RunWheelsForTime(0.3,750));
        VisionButtonOuttake.whileHeld(new VisionAlignmentPlacement());

        elevatorUp.whileHeld(new RunElevator(0.35));
        elevatorDown.whileHeld(new RunElevator(-0.2));
    }

    public double getDriveY() {
        return driveStick.getY();
    }

    public double getDriveYL(){
        return driveStick.getY(Hand.kLeft);
    }

    public double getDriveX() {
        return driveStick.getX();
    }

    public double getDriveXR(){
        return driveStick.getX(Hand.kRight);
    }

    public double getLeftTrigger(){
        return driveStick.getTriggerAxis(Hand.kLeft);
    }

    public double getRightTrigger(){
        return driveStick.getTriggerAxis(Hand.kRight);
    }

    public double getPOV(){
        return driveStick.getPOV();
    }
   
    public void setRumble(double rumble){
        driveStick.setRumble(RumbleType.kLeftRumble, rumble);
        driveStick.setRumble(RumbleType.kRightRumble, rumble);
    }

    private ArmState getArmState() {
        return Robot.intake.armPresets.getArmState();
    }
}