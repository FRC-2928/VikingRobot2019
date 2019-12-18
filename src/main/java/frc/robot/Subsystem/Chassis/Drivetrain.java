package frc.robot.Subsystem.Chassis;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.Command.Chassis.JoystickDrive;

/**
 * This is where we set up the drivetrain
 * We set the motor controllers up, along with the drive functions
 */
public class Drivetrain extends Subsystem {

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

  public Drivetrain() {
    pigeon = new PigeonIMU(RobotMap.PIGEON);

    left = new WPI_TalonSRX(RobotMap.TALON_BACK_LEFT);
    left_follower_1 = new WPI_VictorSPX(RobotMap.VICTOR_MIDDLE_LEFT);
    left_follower_1.follow(left);
    left_follower_2 = new WPI_VictorSPX(RobotMap.VCTOR_FRONT_LEFT);
    left_follower_2.follow(left);

    right = new WPI_TalonSRX(RobotMap.TALON_BACK_RIGHT);
    right_follower_1 = new WPI_VictorSPX(RobotMap.VICTOR_MIDDLE_RIGHT);
    right_follower_1.follow(right);
    right_follower_2 = new WPI_VictorSPX(RobotMap.VICTOR_FRONT_RIGHT);
    right_follower_2.follow(right);

    left.setInverted(true);
    left_follower_1.setInverted(true);
    left_follower_2.setInverted(true);

    right.setInverted(false);
    right_follower_1.setInverted(false);
    right_follower_2.setInverted(false);

    left.configPeakCurrentLimit(55, 10);
    right.configPeakCurrentLimit(55, 10);

    left.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    right.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    left.configNeutralDeadband(0.01);
    right.configNeutralDeadband(0.01);

    left.configPeakCurrentLimit(50, 10);
    right.configPeakCurrentLimit(50, 10);

    drive = new DifferentialDrive(left, right);
  }

  public void drive(double move, double rotate) {
    this.drive(move, rotate, true);
  }

  public void drive(double move, double rotate, boolean squaredInputs) {
    drive.arcadeDrive(rotate, move, squaredInputs); // WPILIB is still backwards
  }

  public void setMotorSafetyEnabled(boolean safety) {
    drive.setSafetyEnabled(safety);
  }

  public void setRampRate(double rampRate){
    left.configOpenloopRamp(rampRate);
    right.configOpenloopRamp(rampRate);
  }

  public void setBrakeMode(boolean mode){
    if(mode == true){
      left.setNeutralMode(NeutralMode.Brake);
      left_follower_1.setNeutralMode(NeutralMode.Brake);
      left_follower_2.setNeutralMode(NeutralMode.Brake);
      right.setNeutralMode(NeutralMode.Brake);
      right_follower_1.setNeutralMode(NeutralMode.Brake);
      right_follower_2.setNeutralMode(NeutralMode.Brake);
    }
    if(mode == false){
      left.setNeutralMode(NeutralMode.Coast);
      left_follower_1.setNeutralMode(NeutralMode.Coast);
      left_follower_2.setNeutralMode(NeutralMode.Coast);
      right.setNeutralMode(NeutralMode.Coast);
      right_follower_1.setNeutralMode(NeutralMode.Coast);
      right_follower_2.setNeutralMode(NeutralMode.Coast);
    }
  }

  public void setAmpLimit(int amps){
    left.configPeakCurrentLimit(amps, 10);
    right.configPeakCurrentLimit(amps, 10);
  }

  public double getYaw() {
    double[] angles = { 0, 0, 0 };
    pigeon.getYawPitchRoll(angles);
    return angles[0];
  }

  public void zeroGyro(){
    pigeon.setYaw(0);
  }

  public double getEncoderPositionLeft() {
    SmartDashboard.putNumber("Left Drivetrain Encoder Position", left.getSelectedSensorPosition());
    return left.getSelectedSensorPosition();
  }

  public double getEncoderPositionRight() {
    SmartDashboard.putNumber("Right Drivetrain Encoder Position", right.getSelectedSensorPosition());
    return right.getSelectedSensorPosition();
  }

  public void resetEncoderPosition() {
    left.setSelectedSensorPosition(0);
    right.setSelectedSensorPosition(0);
  }
}