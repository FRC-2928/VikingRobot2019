package frc.robot.Subsystem.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;


public class Lift extends Subsystem {
  private WPI_TalonSRX liftMotorLeft;
  private WPI_TalonSRX liftMotorRight;

  public Lift(){

    liftMotorLeft = new WPI_TalonSRX(RobotMap.TALON_LEFT_ELEVATOR);
    liftMotorRight = new WPI_TalonSRX(RobotMap.TALON_RIGHT_ELEVATOR);
    liftMotorLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
    liftMotorRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
  }

  public void setLiftPower(double power){

    liftMotorLeft.set(ControlMode.PercentOutput, power);
    liftMotorRight.set(ControlMode.PercentOutput, power);
  }

  public double getLiftPositionInches(){

    double liftPosition = liftMotorLeft.getSelectedSensorPosition() * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH;
    SmartDashboard.putNumber("Lift position inches", liftPosition);
    return liftPosition;

  }

  public void moveToSetpoint(double setpointInches){

    double setpoint = setpointInches * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH;
    double currentPosition = liftMotorLeft.getSelectedSensorPosition();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
