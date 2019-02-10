package frc.robot.Subsystem.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;


public class Lift extends Subsystem {
  private WPI_TalonSRX liftMotor;

  public Lift(){

    liftMotor = new WPI_TalonSRX(RobotMap.TALON_ELEVATOR);
    
    liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
    
  }

  public void setLiftPower(double power){

    liftMotor.set(ControlMode.PercentOutput, power);
  }

  public double getLiftPositionInches(){

    double liftPosition = liftMotor.getSelectedSensorPosition() * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH;
    SmartDashboard.putNumber("Lift position inches", liftPosition);
    return liftPosition;

  }

  public void moveToSetpoint(double setpointInches){

    double setpoint = setpointInches * RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH;
    double currentPosition = liftMotor.getSelectedSensorPosition();
  
  }

  public void resetLiftEncoders(){

    liftMotor.setSelectedSensorPosition(0);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
