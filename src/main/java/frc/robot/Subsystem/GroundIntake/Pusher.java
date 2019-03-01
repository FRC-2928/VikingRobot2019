package frc.robot.Subsystem.GroundIntake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

//The pusher is a set of pnuematics on the ground hatch grabber
//It's used to push the hatch off the velcro

public class Pusher extends Subsystem {
private Solenoid pushermotor;

  public enum PusherState{
    IN,
    OUT;

    PusherState toggle(){
      return this.equals(OUT) ? PusherState.IN : PusherState.OUT;
    }
  }

  public Pusher(){
    pushermotor = new Solenoid(RobotMap.SOLENOID_GROUND_HATCH);
    set(PusherState.OUT);
  }

  public void set(PusherState state) {
    pushermotor.set(state.equals(PusherState.IN));
}

  public void in(){
    pushermotor.set(true);
  }

  public void out(){
    pushermotor.set(false);
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
