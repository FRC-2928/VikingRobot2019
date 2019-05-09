package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.Chassis.Transmission.GearState;
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
        //sensors = new Sensors();

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
        chassis.transmission.shift(GearState.LOW);

    }

    @Override
    public void autonomousPeriodic() { 
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().run(); //Test out running the scheduler into teleop
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
