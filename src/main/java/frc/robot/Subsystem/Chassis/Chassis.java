package frc.robot.Subsystem.Chassis;

public class Chassis {

    public Drivetrain drivetrain;
    public Transmission transmission;
    public FourBar fourbar;

    public Chassis()
    {
        drivetrain = new Drivetrain();
        transmission = new Transmission();
        fourbar = new FourBar();
    }
}