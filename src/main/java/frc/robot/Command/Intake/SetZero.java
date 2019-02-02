package frc.robot.Command.Intake;
import java.lang.Math;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Command.Intake.ThreadbarDistancePID;

public class SetZero extends Command {
  public SetZero() {
    /*
    midpoint = sensors /2
    if limSwitch == true{this is +-x; midpoint = x+____;}
    */
    requires(Robot.intake.leftThreadbar);
    requires(Robot.intake.rightThreadbar);
  }
  public double goToXcord(double currentPos, double wantedPos) {
    double oldPos= currentPos;
    double movement = -oldPos + wantedPos; 
    
    /*
      ex. we want to go to position 5 and are at position 2:
        change location by (-(-2) + 5) * ticks per unit
    */
    //ThreadbarDistancePID.ThreadbarDistancePID(movement, 0.5, 0.5);
    requires(Robot.intake.leftThreadbar);
    requires(Robot.intake.rightThreadbar);
   
    return currentPos;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {

    Robot.intake.leftThreadbar.setLeftPower(0);
    Robot.intake.rightThreadbar.setRightPower(0);

  }

  @Override
  protected void interrupted() {
    end();
  }
}
