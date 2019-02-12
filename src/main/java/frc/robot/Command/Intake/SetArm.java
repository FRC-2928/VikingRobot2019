package frc.robot.Command.Intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystem.Intake.ArmPreSets.ArmState;

public class SetArm extends Command {
  private ArmState target;
  private double[] error;

  public SetArm(ArmState state) {
    this.target = state;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.intake.armPresets.switchState(target);
  }

  @Override
  protected boolean isFinished() {
    double stopError = 10000;
    if(Robot.intake.armPresets.getLeftError() > stopError && Robot.intake.armPresets.getRightError() > stopError){
      return true;
    }
    return false;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {
    end();
  }
}
