package frc.robot.Subsystem.Chassis;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Rangefinder extends Subsystem {
  public AnalogInput rangefinder;

  Rangefinder(){
    rangefinder = new AnalogInput(3);
    rangefinder.setOversampleBits(8);
    rangefinder.setAverageBits(4);
    AnalogInput.setGlobalSampleRate(62500);
  }

  public double getRangefinder() {
    SmartDashboard.putNumber("Rangefinder average value", rangefinder.getAverageValue());
    return rangefinder.getAverageValue();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
