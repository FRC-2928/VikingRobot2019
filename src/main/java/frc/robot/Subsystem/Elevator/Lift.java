package frc.robot.Subsystem.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;


public class Lift extends Subsystem {
  private WPI_TalonSRX liftMotor;
  private Solenoid brake;
  private BrakeState currentState;
  

  //Enum for the Elevator brake, set off before moving, set on to stay in place
  public enum BrakeState{

    OFF,
    ON;

      BrakeState toggle(){
        return this.equals(OFF) ? BrakeState.ON : BrakeState.OFF;
      }

  }

  public Lift(){

    liftMotor = new WPI_TalonSRX(RobotMap.TALON_ELEVATOR);
    brake = new Solenoid(RobotMap.SOLENOID_ELEVATOR_BRAKE);
    
    liftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0, 10);
    liftMotor.setNeutralMode(NeutralMode.Brake);
    // currentstate = something;, idk if we start with brakes on or off  
  }

  public void shiftBrake(BrakeState state){

    switch(state){
      
      case OFF: 
      brake.set(false);
      break;

      case ON: 
      brake.set(true);
      break;
    }
    currentState = state;
  }

  public void toggleLiftBrake(){
    shiftBrake(getBrakeState().toggle());
  }

  public BrakeState getBrakeState(){
    return currentState;
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
