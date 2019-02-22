package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.Drawbridge.DrawbridgeState;

public class SetDrawbridge extends Command {
  private DrawbridgeState target;

  public SetDrawbridge(DrawbridgeState target) {
    requires(Robot.intake.drawbridge);
    requires(Robot.intake.threadbar);
    this.target = target;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.intake.drawbridge.switchBridge(target);
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
