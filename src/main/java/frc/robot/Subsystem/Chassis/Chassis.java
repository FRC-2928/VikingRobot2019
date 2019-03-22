package frc.robot.Subsystem.Chassis;

public class Chassis {
    public Drivetrain drivetrain;
    public Transmission transmission;
    public Rangefinder rangefinder;

    public Chassis() {
        drivetrain = new Drivetrain();
        transmission = new Transmission();
        rangefinder = new Rangefinder();
    }
}