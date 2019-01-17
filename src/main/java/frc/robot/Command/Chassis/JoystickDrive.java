
package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OperatorInterface;
import frc.robot.Subsystem.Chassis.Drivetrain;
import frc.robot.Robot;



public class JoystickDrive extends Command {
  public JoystickDrive() {
    
     requires(Robot.chassis.drivetrain);
  
  }

  @Override
  protected void initialize() {
    
  }
 
  @Override
  protected void execute() {
    double driveX = Robot.oi.getDriveX();
    double driveY = Robot.oi.getDriveY();
    Robot.chassis.drivetrain.drive(driveY, driveX);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }


  @Override
  protected void interrupted() {
  }
}
