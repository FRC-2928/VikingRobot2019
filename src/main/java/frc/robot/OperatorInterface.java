package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Command.Intake.*;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.Command.Chassis.Shift;
import frc.robot.Command.Elevator.*;
import frc.robot.Command.GroundIntake.RunGroundIntake;
import frc.robot.Command.GroundIntake.SetPusher;
import frc.robot.Command.Chassis.RunFourBar;
import frc.robot.Command.Chassis.SetFourBar;
import frc.robot.Subsystem.Chassis.Transmission;
import frc.robot.Subsystem.GroundIntake.Pusher.PusherState;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;
import frc.robot.Autonomous.Endgame;


public class OperatorInterface {

    //Driving
    private static final Joystick driveStick = new Joystick(0);
    private static final Joystick operatorConsole = new Joystick(1);
    private static final JoystickButton gearButtonHigh = new JoystickButton(driveStick, 4);
    private static final JoystickButton gearButtonLow = new JoystickButton(driveStick, 3);
    
    // private static final JoystickButton groundButtonDown = new JoystickButton(driveStick, 3);
    // private static final JoystickButton groundButtonUp = new JoystickButton(driveStick, 4);
    // private static final JoystickButton pusherButtonIn = new JoystickButton(driveStick, 5);
    // private static final JoystickButton pusherButtonOut = new JoystickButton(driveStick, 6);

    //Testing autos
    // private static final JoystickButton VisionButton = new JoystickButton(driveStick, 7);
    // private static final JoystickButton LifterTest = new JoystickButton(driveStick, 8);

    //Intake
    // private static final JoystickButton threadbarLeftRight = new JoystickButton(driveStick, 11);
    // private static final JoystickButton threadbarLeftLeft = new JoystickButton(driveStick, 12);
    // private static final JoystickButton threadbarLeft = new JoystickButton(driveStick, 5);
    // private static final JoystickButton threadbarRight = new JoystickButton(driveStick, 6);
    // private static final JoystickButton threadbarHatch = new JoystickButton(driveStick, 7); //placeholder
    // private static final JoystickButton threadbarBall = new JoystickButton(driveStick, 8); //placeholder
    private static final JoystickButton intake = new JoystickButton(driveStick, 1);
    private static final JoystickButton outtake = new JoystickButton(driveStick, 2);
    private DrawbridgeState drawbridgeCurrentState = Robot.intake.drawbridge.getDrawbridgeState();
    private static final JoystickButton drawbridgeUp = new JoystickButton(driveStick, 9); //placeholder
    private static final JoystickButton drawbridgeDown = new JoystickButton(driveStick, 10); //placeholder

    //Elevator
    private ArmState armCurrentState = Robot.intake.armPresets.getArmState();
    private static final JoystickButton elevatorUp = new JoystickButton(driveStick, 11);
    private static final JoystickButton elevatorDown = new JoystickButton(driveStick, 12);
    private static final JoystickButton elevatorLvl1 = new JoystickButton(operatorConsole, 254);//placeholder
    private static final JoystickButton elevatorLvl2 = new JoystickButton(operatorConsole, 118);//placeholder
    private static final JoystickButton elevatorLvl3 = new JoystickButton(operatorConsole, 148);//placeholder
    private static final JoystickButton elevatorLvlGround = new JoystickButton(operatorConsole, 973);
    private static final JoystickButton elevatorLvlCargoBall = new JoystickButton(operatorConsole, 971);

    //Endgame
    private static final JoystickButton endgame = new JoystickButton(operatorConsole, 2928);
    private static final JoystickButton endgameStop = new JoystickButton(operatorConsole, 987);
    private static final JoystickButton fourBarUp = new JoystickButton(driveStick, 9);
    private static final JoystickButton fourBarDown = new JoystickButton(driveStick, 10);

    OperatorInterface() {
        gearButtonHigh.whenPressed(new Shift(Transmission.GearState.HIGH));
        gearButtonLow.whenPressed(new Shift(Transmission.GearState.LOW));

        // groundButtonUp.whileHeld(new RunGroundIntake(-0.4));
        // groundButtonDown.whileHeld(new RunGroundIntake(0.4));
        // pusherButtonIn.whenPressed(new SetPusher(PusherState.IN));
        // pusherButtonOut.whenPressed(new SetPusher(PusherState.OUT));

        //Left: - for moving left, + for moving right
        //Right: - for moving left, + for moving right
        // if(drawbridgeCurrentState == DrawbridgeState.DOWN){ 
            // threadbarLeft.whileHeld(new RunLeftThreadbar(-0.2));
            // threadbarLeft.whileHeld(new RunRightThreadbar(-0.2));
            // threadbarRight.whileHeld(new RunLeftThreadbar(0.2));
            // threadbarRight.whileHeld(new RunRightThreadbar(0.2));
            // threadbarLeftLeft.whileHeld(new RunLeftThreadbar(-0.2));
            // threadbarLeftRight.whileHeld(new RunLeftThreadbar(0.2));
    
            // threadbarHatch.whenPressed(new SetArm(ArmState.HATCH));
            // threadbarBall.whenPressed(new SetArm(ArmState.BALL));

            // VisionButton.whenPressed(new VisionSetThreadbar());

            intake.whileHeld(new RunWheels(0.4));
            outtake.whileHeld(new RunWheels(-0.4));
            // }

        drawbridgeDown.whenPressed(new SetDrawbridge(DrawbridgeState.DOWN));
        drawbridgeUp.whenPressed(new SetDrawbridge(DrawbridgeState.UP));

        elevatorUp.whileHeld(new RunElevator(0.6));
        elevatorDown.whileHeld(new RunElevator(-0.3));
        // elevatorLvlGround.whenPressed(new SetElevator(Idfk));
        // elevatorLvlCargoBall.whenPressed(command);
        if(armCurrentState == ArmState.BALL){
        //     elevatorLvl1.whenPressed(new SetElevator(Idfk));
        //     elevatorLvl2.whenPressed(new SetElevator(Idfk));
        //     elevatorLvl3.whenPressed(new SetElevator(Idfk));
        }
        if(armCurrentState == ArmState.HATCH){
        //     elevatorLvl1.whenPressed(new SetElevator(Idfk));
        //     elevatorLvl2.whenPressed(new SetElevator(Idfk));
        //     elevatorLvl3.whenPressed(new SetElevator(Idfk));
        }

        endgame.whenPressed(new Endgame());
        endgameStop.whenPressed(new SetFourBar(false));
        fourBarUp.whileHeld(new RunFourBar(0.2));
        fourBarDown.whileHeld(new RunFourBar(-0.2));
    }
    
    public double getDriveY() {
        return driveStick.getY(); 
    }

    public double getDriveX() {
        return driveStick.getX();
    }

    public double getDriveZ(){
        return driveStick.getZ();
    }
}

