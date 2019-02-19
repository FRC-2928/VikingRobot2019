package frc.robot.Subsystem.Elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;


public class Lift extends Subsystem {
  private CANSparkMax liftMotor;
  private CANEncoder liftEncoder;
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

    liftMotor = new CANSparkMax(RobotMap.TALON_ELEVATOR,MotorType.kBrushless);
    brake = new Solenoid(RobotMap.SOLENOID_ELEVATOR_BRAKE);
    
    liftEncoder = liftMotor.getEncoder();
    currentState = BrakeState.ON;  
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

    liftMotor.set(power);
  }

  public double getLiftPositionInches(){

    liftEncoder.setPositionConversionFactor(RobotConstants.ELEVATOR_ENCODER_TICKS_PER_INCH);
    double liftPosition = liftEncoder.getPosition();
    SmartDashboard.putNumber("Lift position inches", liftPosition);
    return liftPosition;
    
  }

  public void resetLiftEncoders(){

    liftEncoder.setPosition(0);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
