package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;

public class SetArm extends Command {
  private double midpoint;
  public SetArm() {

  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    midpoint = (Robot.intake.leftThreadbar.getLeftEncoder() + Robot.intake.rightThreadbar.getRightEncoder()) / 2;

    
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
