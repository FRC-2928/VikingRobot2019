package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class SetFourBar extends Command {
  private double currentPosition;
  private boolean setpointDirection;
  private boolean inPosition;

  //True = forward, false = backwards
  public SetFourBar(Boolean direction) {

    this.setpointDirection = direction;

  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    
    double currentPosition = Robot.chassis.fourbar.getFourBarPosition();
    double setpoint = RobotConstants.FOUR_BAR_ENCODER_TICKS_PER_INCH;
    double error = setpoint - currentPosition;
    
    while(inPosition = false){ 

        

    }

    while(inPosition = true){
      
      if(setpointDirection = true){
      //Starts driving the four bar up until it hits max
        while (error < 100){

        Robot.chassis.fourbar.setFourBarPower(0.2); //Placeholder, low for testing

        }
      //TODO: Connect to infrared sensor, stop driving once it reads something
      }

      //Goes backwards if we aren't too far
      if(setpointDirection = false ){

        if(currentPosition < 10000){ //Placeholder, needs testing

        Robot.chassis.fourbar.setFourBarPower(-0.2);

        }

        else{

        Robot.chassis.fourbar.setFourBarPower(0);

        }
      }
    }
  }
  

  @Override
  protected boolean isFinished() {
    if(currentPosition > RobotConstants.FOUR_BAR_MAX_ENCODER_TICKS){
      return true;
    }

    /*if(InfraredSensor < 13){ //TODO: Setup infrared sensor
      return true;
    }
    */

    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.chassis.fourbar.setFourBarPower(0);
    Robot.chassis.drivetrain.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
