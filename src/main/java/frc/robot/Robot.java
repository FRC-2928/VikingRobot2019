package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Command.Endgame.DefaultRunFourBar;
import frc.robot.Command.Endgame.Endgame;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.Elevator.Elevator;
// import frc.robot.Subsystem.GroundIntake.*;
import frc.robot.Subsystem.Intake.*;
import frc.robot.Subsystem.Intake.ArmPresets.ArmState;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

//The main robot class, during a match the robot goes through everything in this class

public class Robot extends TimedRobot {
    private SendableChooser<ArmState> armPresetSelector;
    private Compressor compressor;
    public static Chassis chassis;
    // public static GroundIntake groundintake;
    public static OperatorInterface oi;
    public static Intake intake;
    public static Elevator elevator;

    
    //public static Sensors sensors;

    @Override
    public void robotInit() {

        compressor = new Compressor();
        compressor.start();
        chassis = new Chassis();
        // groundintake = new GroundIntake();
        elevator = new Elevator();
        intake = new Intake();
        chassis.fourbar.setDefaultFourBar(true);
        
        // This has to be at the bottom or things crash
        // OI requires everything to be initialized
        oi = new OperatorInterface(); 
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        chassis.drivetrain.resetEncoderPosition();
        intake.drawbridge.switchBridge(DrawbridgeState.DOWN);
        intake.threadbar.resetThreadbarEncoders();
        elevator.lift.resetLiftEncoders();


    }

    @Override
    public void autonomousPeriodic() { 
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
        //chassis.drivetrain.setMotorSafetyEnabled(true);
        
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        chassis.fourbar.getFourBarPosition();
        // NetworkTableEntry tx = table.getEntry("tx");
        // double x = tx.getDouble(0.0);
        // SmartDashboard.putNumber("Limelight X value  from Robot.java", x);
    }

   
    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }
}
