package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Autonomous.*;
import frc.robot.Command.Intake.VisionSetThreadbar;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.GroundIntake.*;
import frc.robot.Subsystem.Intake.*;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

//The main robot class, during a match the robot goes through everything in this class

public class Robot extends IterativeRobot {
    private Compressor compressor;
    public static Chassis chassis;
    public static GroundIntake groundintake;
    public static OperatorInterface oi;
    public static Intake intake;
    //public static Sensors sensors;

    @Override
    public void robotInit() {

        compressor = new Compressor();
        compressor.start();
        chassis = new Chassis();
        groundintake = new GroundIntake();
        oi = new OperatorInterface();
        intake = new Intake();
        //sensors = new Sensors();
        intake.leftThreadbar.resetLeftEncoder();
        intake.rightThreadbar.resetRightEncoder();

    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
        intake.leftThreadbar.resetLeftEncoder();
        intake.rightThreadbar.resetRightEncoder();
        chassis.drivetrain.setMotorSafetyEnabled(true);

    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();

    }

    @Override
    public void autonomousPeriodic() { 
        Scheduler.getInstance().run();
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
