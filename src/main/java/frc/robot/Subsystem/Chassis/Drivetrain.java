package frc.robot.Subsystem.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {
 
public WPI_TalonSRX left;
public WPI_VictorSPX left_follower_1;
public WPI_VictorSPX left_follower_2;

public WPI_TalonSRX right;
public WPI_VictorSPX right_follower_1;
public WPI_VictorSPX right_follower_2;

public PigeonIMU pigeon;
private DifferentialDrive drive;

@Override
public void initDefaultCommand() {
  // Set the default command for a subsystem here.
  // setDefaultCommand(new MySpecialCommand());
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
    right_follower_1.set(ControlMode.Follower, RobotMap.TALON_BACK_RIGHT);
    right_follower_2 = new WPI_VictorSPX(RobotMap.VICTOR_FRONT_RIGHT);
    right_follower_2.set(ControlMode.Follower, RobotMap.TALON_BACK_RIGHT);

    right.setInverted(true);
    right_follower_1.setInverted(true);
    right_follower_2.setInverted(true);

    
    }

    public void drive(double move, double rotate) {
      this.drive(move, rotate, true);
      }
  
    public void drive(double move, double rotate, boolean squaredInputs)
    {
        drive.arcadeDrive(rotate, move, squaredInputs); // WPILIB is still backwards
    }


}
