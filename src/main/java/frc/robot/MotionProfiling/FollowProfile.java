package frc.robot.MotionProfiling;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FollowProfile extends Command {

    private Profile[] profiles;
    private boolean motorSafetyBackup = true;

    public FollowProfile(String profileName) {
        requires(Robot.chassis.drivetrain);
        ProfileLoader profileLoader = new ProfileLoader();
        profiles = profileLoader.loadProfile(profileName);
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        for (StackTraceElement ste : st){
            System.out.println(ste);
        }
    }

    public void initialize() {
        motorSafetyBackup = Robot.chassis.drivetrain.getMotorSafetyEnabled();
        Robot.chassis.drivetrain.setMotorSafetyEnabled(false);
        Robot.chassis.drivetrain.setProfiles(profiles);
        // Robot.chassis.drivetrain.startProfileDrive();
    }

    // We don't need an execute method because all the profile following happens in a Notifier, on a different thread

    @Override
    public boolean isFinished() {
        return false;
    }

    public void end() {
        Robot.chassis.drivetrain.stopProfileDrive();
        Robot.chassis.drivetrain.setMotorSafetyEnabled(motorSafetyBackup);
    }

    public void interrupted() {
        end();
    }
}
