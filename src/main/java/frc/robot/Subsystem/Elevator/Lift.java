package frc.robot.Subsystem.Elevator;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class Lift extends Subsystem {
  private CANSparkMax liftMotor;
  private CANSparkMax lfitMotorSlave;
  private CANEncoder liftEncoder;
  private Solenoid brake;
  private BrakeState currentState;

  // Enum for the Elevator brake, set off before moving, set on to stay in place
  public enum BrakeState {
    OFF, ON;
  }

  public Lift() {
    liftMotor = new CANSparkMax(RobotMap.SPARK_ELEVATOR_BOTTOM, MotorType.kBrushless);
    lfitMotorSlave = new CANSparkMax(RobotMap.SPARK_ELEVATOR_TOP, MotorType.kBrushless);
    lfitMotorSlave.follow(liftMotor);
    liftMotor.setSmartCurrentLimit(45, 55, 750);
    lfitMotorSlave.setSmartCurrentLimit(45, 55, 750);
    brake = new Solenoid(RobotMap.SOLENOID_ELEVATOR_BRAKE);
    liftEncoder = liftMotor.getEncoder();
    currentState = BrakeState.ON;
  }

  public void shiftBrake(BrakeState state) {
    switch (state) {
      case OFF: 
      brake.set(true);
      break;

      case ON: 
      brake.set(false);
      break;

      default:
      break;
    }

    currentState = state;
  }

  public BrakeState getBrakeState() {
    return currentState;
  }

  public void setLiftPower(double power) {
    liftMotor.set(power);
  }

  public double getLiftPosition() {
    double liftPosition = liftEncoder.getPosition();
    return liftPosition;
  }

  public void resetLiftEncoders() {
    liftEncoder.setPosition(0);
  }

  @Override
  public void initDefaultCommand() {
  }
}
