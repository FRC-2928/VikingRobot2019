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

    private static final JoystickButton gearButton = new JoystickButton(driverConsole, 7);
    private static final JoystickButton groundButtonUp = new JoystickButton(operatorConsole, 12);
    private static final JoystickButton groundButtonDown = new JoystickButton(operatorConsole, 11);
    private static final JoystickButton pusherButton = new JoystickButton(operatorConsole, 10);

    // Testing autos
    private static final JoystickButton VisionButton = new JoystickButton(driveStick, 5);
    // private static final JoystickButton LifterTest = new
    // JoystickButton(driveStick, 8);

    // Intake
    private ArmState armCurrentState; // true == cargo, false == hatch
    private static final JoystickButton threadbarLeftLeft = new JoystickButton(driveStick, 9);
    private static final JoystickButton threadbarLeftRight = new JoystickButton(driveStick, 10);
    private static final JoystickButton threadbarLeft = new JoystickButton(driveStick, 11);
    private static final JoystickButton threadbarRight = new JoystickButton(driveStick, 12);
    private static final JoystickButton threadbarState = new JoystickButton(operatorConsole, 9);
    private static final JoystickButton threadbarHatch = new JoystickButton(driverConsole, 9); // placeholder
    private static final JoystickButton threadbarBall = new JoystickButton(driverConsole, 8); // placeholder
    private static final JoystickButton opThreadbar = new JoystickButton(operatorConsole, 9);
    private static final JoystickButton intake = new JoystickButton(driverConsole, 5);
    private static final JoystickButton outtake = new JoystickButton(driveStick, 2);
    private static final JoystickButton opIntake = new JoystickButton(operatorConsole, 7);
    private static final JoystickButton opOuttake = new JoystickButton(operatorConsole, 8);
    private DrawbridgeState drawbridgeState = Robot.intake.drawbridge.getDrawbridgeState();
    private static final JoystickButton drawbridge = new JoystickButton(driverConsole, 6);

    // Elevator

    private static final JoystickButton elevatorUp = new JoystickButton(driveStick, 8);
    private static final JoystickButton elevatorDown = new JoystickButton(driveStick, 7);
    private static final JoystickButton elevatorLvl1 = new JoystickButton(operatorConsole, 6);// placeholder
    private static final JoystickButton elevatorLvl2 = new JoystickButton(operatorConsole, 5);// placeholder
    private static final JoystickButton elevatorLvl3 = new JoystickButton(operatorConsole, 4);// placeholder
    private static final JoystickButton elevatorLvlGround = new JoystickButton(operatorConsole, 3);
    private static final JoystickButton elevatorLvlCargoBall = new JoystickButton(operatorConsole, 1);
    private static final JoystickButton elevatorLvlCargoLoader = new JoystickButton(operatorConsole, 2);
    private static final JoystickButton elevatorBrakeOn = new JoystickButton(driverConsole, 3);
    private static final JoystickButton elevatorBrakeOff = new JoystickButton(driverConsole, 4);
    private static final JoystickButton elevatorState = new JoystickButton(driverConsole, 6);

    // Endgame
    private static final JoystickButton endgame = new JoystickButton(operatorConsole, 2928);
    private static final JoystickButton endgameStop = new JoystickButton(operatorConsole, 987);
    private static final JoystickButton fourBarUp = new JoystickButton(driveStick, 4);
    private static final JoystickButton fourBarDown = new JoystickButton(driveStick, 3);

    OperatorInterface() {
        gearButton.whenPressed(new Shift(Transmission.GearState.HIGH));
        gearButton.whenInactive(new Shift(Transmission.GearState.LOW));

        // groundButtonUp.whileHeld(new RunGroundIntake(0.8));
        // groundButtonDown.whileHeld(new RunGroundIntake(-0.5));
        // pusherButton.whenPressed(new SetPusher(PusherState.IN));
        // pusherButton.whenReleased(new SetPusher(PusherState.OUT));

        // Left: - for moving left, + for moving right
        // Right: - for moving left, + for moving right
        // if(drawbridgeState == DrawbridgeState.DOWN){
        threadbarLeft.whileHeld(new RunLeftThreadbar(-0.8));
        // threadbarLeft.whileHeld(new RunRightThreadbar(-0.8));
        threadbarRight.whileHeld(new RunLeftThreadbar(0.8));
        // threadbarRight.whileHeld(new RunRightThreadbar(0.8));
        threadbarLeftLeft.whileHeld(new RunRightThreadbar(-0.8));
        threadbarLeftRight.whileHeld(new RunRightThreadbar(0.8));
        opThreadbar.whenPressed(new SetArm(ArmState.HATCH));
        opThreadbar.whenInactive(new SetArm(ArmState.BALL));

        threadbarHatch.whenPressed(new SetArm(ArmState.HATCH));
        threadbarBall.whenPressed(new SetArm(ArmState.BALL));
        // threadbarState.whenPressed(new SetArm(ArmState.BALL));
        // threadbarState.whenInactive(new SetArm(ArmState.HATCH));

        VisionButton.whenPressed(new VisionSetThreadbar());

        intake.whileHeld(new RunWheels(0.9));
        outtake.whileHeld(new RunWheels(-0.9));
        opIntake.whileHeld(new RunWheels(0.9));
        opOuttake.whileHeld(new RunWheels(-0.9));

        drawbridge.whenPressed(new SetDrawbridge(DrawbridgeState.UP));
        drawbridge.whenInactive(new SetDrawbridge(DrawbridgeState.DOWN));

        armCurrentState = getArmState();
        elevatorBrakeOn.whenPressed(new SetElevatorBrake(BrakeState.ON));
        elevatorBrakeOff.whenPressed(new SetElevatorBrake(BrakeState.OFF));
        elevatorUp.whileHeld(new RunElevator(0.5));
        elevatorDown.whileHeld(new RunElevator(-0.1));
        elevatorLvlGround.whenPressed(new SetElevator(LiftState.GROUND_LEVEL));
        elevatorLvlCargoBall.whenPressed(new SetElevator(LiftState.CARGO_SHIP_BALL));
        elevatorLvlCargoLoader.whenPressed(new SetElevator(LiftState.CARGO_LOADER_BALL));
        elevatorLvl1.whenPressed(new SetElevator(LiftState.LEVEL_1));
        elevatorLvl2.whenPressed(new SetElevator(LiftState.LEVEL_2));
        elevatorLvl3.whenPressed(new SetElevator(LiftState.LEVEL_3));

        // endgame.whenPressed(new Endgame());
        // endgameStop.whenPressed(new SetFourBar(false));
        fourBarUp.whileHeld(new RunFourBar(-0.8));
        fourBarDown.whileHeld(new RunFourBar(0.8));
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
