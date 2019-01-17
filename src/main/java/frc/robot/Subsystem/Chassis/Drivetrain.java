package frc.robot.Subsystem.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.Command.Chassis.JoystickDrive;


public class 
Drivetrain extends Subsystem {
 
public WPI_TalonSRX left;
public WPI_VictorSPX left_follower_1;
public WPI_VictorSPX left_follower_2;

public WPI_TalonSRX right;
public WPI_VictorSPX right_follower_1;
public WPI_VictorSPX right_follower_2;

public PigeonIMU pigeon;
public DifferentialDrive drive;

@Override
public void initDefaultCommand() {
  setDefaultCommand(new JoystickDrive());
}
public Drivetrain()
  {

    left = new WPI_TalonSRX(RobotMap.TALON_BACK_LEFT);
    left_follower_1 = new WPI_VictorSPX(RobotMap.VICTOR_MIDDLE_LEFT);
    left_follower_1.set(ControlMode.Follower, RobotMap.TALON_BACK_LEFT);
    left_follower_2 = new WPI_VictorSPX(RobotMap.VCTOR_FRONT_LEFT);
    left_follower_2.set(ControlMode.Follower, RobotMap.TALON_BACK_LEFT);

    right= new WPI_TalonSRX(RobotMap.TALON_BACK_RIGHT);
    right_follower_1 = new WPI_VictorSPX(RobotMap.VICTOR_MIDDLE_RIGHT);
    // right_follower_1.set(ControlMode.Follower, RobotMap.TALON_BACK_RIGHT);
    right_follower_2 = new WPI_VictorSPX(RobotMap.VICTOR_FRONT_RIGHT);
    right_follower_2.set(ControlMode.Follower, RobotMap.TALON_BACK_RIGHT);
    System.out.println(right_follower_1);
    System.out.println("Hi there");

    left.setInverted(true);
    left_follower_1.setInverted(true);
    left_follower_2.setInverted(true);

    drive = new DifferentialDrive(left, right);

    }

    public void drive(double move, double rotate) {
      this.drive(move, rotate, true);
      right_follower_1.set(0.8);
      }
  
    public void drive(double move, double rotate, boolean squaredInputs)
    {
      drive.arcadeDrive(rotate, move, squaredInputs); // WPILIB is still backwards
    }


}