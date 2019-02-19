package frc.robot.Command.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotConstants;

public class SetFourBar extends Command {
  private double currentPosition;
  private double setpoint;
  private double error;
  private boolean setpointDirection; 
  public boolean stopFourBar;

  //True = forward, false = backwards
  public SetFourBar(Boolean direction) {

    this.setpointDirection = direction;
    stopFourBar = false;

  }
  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    
    currentPosition = Robot.chassis.fourbar.getFourBarPosition();
    setpoint = RobotConstants.FOUR_BAR_MAX_ENCODER_TICKS;
    error = setpoint - currentPosition;
  
    if(setpointDirection == true){
      //Starts driving the four bar up until it hits max
      Robot.chassis.fourbar.setFourBarPower(0.2); //Placeholder, low for testing
  }

      //Goes backwards if we aren't too far
    if(setpointDirection == false ){

      if(currentPosition < 10000){ //Placeholder, needs testing

        Robot.chassis.fourbar.setFourBarPower(-0.2);
      }
      else{
        stopFourBar = true;
      }
  }
}

  @Override
  protected boolean isFinished() {
    if(error > 100000){//placeholder
      return true;
    }
    if(stopFourBar == true){
      return true;
    }
    if(setpointDirection == false && error < 1000){
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
    Robot.chassis.fourbar.setFourBarPower(0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
