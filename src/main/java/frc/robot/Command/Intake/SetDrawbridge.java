package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

/**
 * Represents the folded intake going up or down
 */
public class SetDrawbridge extends Command {
  private DrawbridgeState target;
  private double targetState;

  public SetDrawbridge() {
    requires(Robot.intake.drawbridge);
    requires(Robot.intake.threadbar);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    if(Robot.oi.getPOV() == 0){
      Robot.intake.drawbridge.switchBridge(DrawbridgeState.UP);
    }

    if(Robot.oi.getPOV() == 180){
      Robot.intake.drawbridge.switchBridge(DrawbridgeState.DOWN);
    }

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
