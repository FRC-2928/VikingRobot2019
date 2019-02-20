package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Command.Intake.VisionSetThreadbar;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.Chassis.Drivetrain;
import frc.robot.Subsystem.Elevator.Elevator;
import frc.robot.Subsystem.GroundIntake.*;
import frc.robot.Subsystem.Intake.*;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//The main robot class, during a match the robot goes through everything in this class

public class Robot extends TimedRobot {
    private SendableChooser<ArmState> armPresetSelector;
    private Compressor compressor;
    public static Chassis chassis;
    public static GroundIntake groundintake;
    public static OperatorInterface oi;
    public static Intake intake;
    public static Elevator elevator;

    
    //public static Sensors sensors;

    @Override
    public void robotInit() {

        compressor = new Compressor();
        compressor.start();
        chassis = new Chassis();
        groundintake = new GroundIntake();
        elevator = new Elevator();
        intake = new Intake();
        //sensors = new Sensors();
        armPresetSelector = new SendableChooser<>();
        armPresetSelector.setDefaultOption("Hatch State", ArmState.HATCH);
        armPresetSelector.addOption("Ball State", ArmState.BALL);
        SmartDashboard.putData("Threadbar State", armPresetSelector);
        

        // This has to be at the bottom or things crash
        // OI requires everything to be initialized
        oi = new OperatorInterface(); 
    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        Robot.chassis.drivetrain.resetEncoderPosition();
        Robot.chassis.transmission.shift(Transmission.GearState.LOW);

    }

    @Override
    public void autonomousPeriodic() { 
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
        intake.threadbar.resetThreadbarEncoders();
        elevator.lift.resetLiftEncoders();
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("stream").setNumber(0);
        //chassis.drivetrain.setMotorSafetyEnabled(true);
        
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
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
