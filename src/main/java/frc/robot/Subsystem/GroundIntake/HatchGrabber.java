// package frc.robot.Subsystem.GroundIntake;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// //import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import frc.robot.RobotMap;

// public class HatchGrabber extends Subsystem {
//   private WPI_TalonSRX groundMotorLeft;
//   private WPI_TalonSRX groundMotorRight;

//   public HatchGrabber() {
//     groundMotorLeft = new WPI_TalonSRX(RobotMap.TALON_GROUND_HATCH_LEFT);
//     groundMotorRight = new WPI_TalonSRX(RobotMap.TALON_GROUND_HATCH_RIGHT);
//     groundMotorLeft.setInverted(true);
//     // groundmotor.setNeutralMode(WPI_TalonSRX.motorcontrol.setNeutralMode.Brake);
//   }

//   public void setpower(double power) {
//     groundMotorLeft.set(ControlMode.PercentOutput, power);
//     groundMotorRight.set(ControlMode.PercentOutput, power);
//   }

//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     // setDefaultCommand(new MySpecialCommand());
//   }
// }