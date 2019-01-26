package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Subsystem.Chassis.*;
import frc.robot.Subsystem.GroundIntake.*;

public class Robot extends TimedRobot {
    private Compressor compressor;
    public static Chassis chassis;
    public static GroundIntake groundintake;
    public static OperatorInterface oi;

    @Override
    public void robotInit() {
        compressor = new Compressor();
        compressor.start();
        chassis = new Chassis();
        groundintake = new GroundIntake();
        oi = new OperatorInterface();
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