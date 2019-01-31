//  package frc.robot.Subsystem.Intake;

//  import frc.robot.Command.Intake.*;
//  import edu.wpi.first.wpilibj.I2C;
//  import edu.wpi.first.wpilibj.command.Subsystem;
// // import SensorBar.h;
// import frc.robot.Command.Intake.RunSensors;

//  public class Sensors extends Subsystem {
//  public I2C sensor1;
// //  public Sensorbar MySensorBar1(0x3E)
// //  public I2C MySensorBar2(ox3f)
// //  public I2C MySensorBar3(ox70)
// //  public I2C MySensorBar4(ox71)



// byte lbuffer[];
// byte mbuffer[];
// byte rbuffer[];

//  public Sensors(){

//     lbuffer = new byte[1]; //left, middle, and right sensors
//     mbuffer = new byte[1];
//     rbuffer = new byte[1];

//  }

//  public byte[] readSensors(){

//     //error with buffer
//     sensor1.read(0x3E, 1, lbuffer);
//     sensor1.read(0x70, 1, mbuffer);
//     sensor1.read(0x3F, 1, rbuffer);

//     byte[] readings =  new byte[] {lbuffer[0] , mbuffer[0] , rbuffer[0]}; 

//     return readings;

//  }



//    @Override
//    public void initDefaultCommand() {

//     System.out.println(readSensors());

//     setDefaultCommand(new RunSensors());
    
//    }
//  }