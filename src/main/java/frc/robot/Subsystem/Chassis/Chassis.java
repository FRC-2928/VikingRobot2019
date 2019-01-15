package frc.robot.Subsystem.Chassis;

public class Chassis {

    public Drivetrain drivetrain;
    public Transmission transmission;

    public Chassis()
    {
        drivetrain = new Drivetrain();
        transmission = new Transmission();
    }
}
