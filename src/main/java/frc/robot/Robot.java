package frc.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Autonomous.*;
import frc.robot.Command.Chassis.*;
import frc.robot.Command.CommandGroupBuilder;
import frc.robot.Subsystem.Chassis.Chassis;

/**
 * Robot for 2018.
 */
@SuppressWarnings("FieldCanBeLocal")
public class Robot extends IterativeRobot {

    private double counter;
    private SendableChooser<Auto> autoSelector;
    private SendableChooser<Field.FieldPosition> startingPositionSelector;
    private Compressor compressor;
    public static Chassis chassis;
    public static OperatorInterface oi;
    Command rotateninety;

    @Override
    public void robotInit() {
        compressor = new Compressor();
        chassis = new Chassis();
        compressor.start();
        SmartDashboard.putNumber("Erika's test @Jett",10);
        rotateninety = new RotateNinety(90);
        oi = new OperatorInterface();
    }

    @Override
    public void teleopInit() {
        Scheduler.getInstance().removeAll();
        chassis.drivetrain.resetTalons();
        chassis.drivetrain.setMotorSafetyEnabled(true);
        chassis.drivetrain.stopProfileDrive();
        rotateninety.start();
        new ResetSensors().start();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();


    }

    @Override
    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        chassis.drivetrain.setMotorSafetyEnabled(false);
       // new SpinnyBoi().start();



        }



    @Override
    public void autonomousPeriodic() { //Scheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
        Scheduler.getInstance().removeAll();
        chassis.drivetrain.stopProfileDrive();
        new ResetSensors().start();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }
}
