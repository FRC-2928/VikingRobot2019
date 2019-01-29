package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.GroundIntake.*;
import frc.robot.Subsystem.Intake.*;

public class Robot extends IterativeRobot {
    private Compressor compressor;
    public static Chassis chassis;
    public static GroundIntake groundintake;
    public static OperatorInterface oi;
    public static Intake intake;
    public static Sensors sensors;

    @Override
    public void robotInit() {

        compressor = new Compressor();
        compressor.start();
        chassis = new Chassis();
        groundintake = new GroundIntake();
        oi = new OperatorInterface();
        intake = new Intake();
        sensors = new Sensors();

    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
    
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
