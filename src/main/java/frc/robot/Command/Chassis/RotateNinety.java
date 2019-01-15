package frc.robot.Command.Chassis;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotConstants;
import frc.robot.RobotMap;
/* we need to make the robot turn 90 degrees and angle+90 if x>360  -360
gyro stuff
*/

public class RotateNinety extends Command {

    private double counter;
    private double setpoint; // For the right talon, left talon is -setpoint.
    private boolean motorSafetyBackup = true;
    private double previousVelocity;
    private int decelCounter;
    private boolean hasStartedDecel;
    private PigeonIMU pigeon;
    public RotateNinety(double degrees) {
        // requires(Robot.chassis.drivetrain);
        //this.setpoint = (int)(RobotConstants.DRIVE_TICKS_PER_FOOT * (degrees / 360 * Math.PI * RobotConstants.AXLE_LENGTH_FEET));
        this.setpoint = degrees;
        this.counter = 0;
    }

    @Override
    protected boolean isFinished() {
        return hasStartedDecel && Robot.chassis.drivetrain.getAverageVelocityMagnitude() < RobotConstants.TALON_CRUISE_VELOCITY * 0.02;
    }

    public void rotateToAngel(double targetBoi){
        double angle = Robot.chassis.drivetrain.getYaw();
        double error = targetBoi - angle;
        Robot.chassis.drivetrain.setAngleSetpoint(error);
    }

    @Override
    public void initialize(){
        motorSafetyBackup = Robot.chassis.drivetrain.getMotorSafetyEnabled();
        // Safety has to be disabled whenever we use a mode that isn't
        Robot.chassis.drivetrain.setMotorSafetyEnabled(false);
        Robot.chassis.drivetrain.zeroEncoders();
        previousVelocity = -1;
        decelCounter = 0;
        hasStartedDecel = false;
        // We can do this because we disabled the motor safety.
        // Robot.chassis.drivetrain.setTalons(ControlMode.MotionMagic, -setpoint, setpoint);
    }

    @Override
    public void execute(){

        double currentAngle = Robot.chassis.drivetrain.getYaw();
        double error = this.setpoint - currentAngle;
        double kp = 1;
        Robot.chassis.drivetrain.drive(1000 , kp*error);
        rotateToAngel(this.setpoint);
        this.counter++;
        System.out.println(this.counter);
        SmartDashboard.putNumber("countyboi",this.counter);
    }

    @Override
    public void interrupted(){
        end();
    }

    @Override
    public void end(){
        Robot.chassis.drivetrain.resetTalons();
        Robot.chassis.drivetrain.setMotorSafetyEnabled(motorSafetyBackup);
    }
}
