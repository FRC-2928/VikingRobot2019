// package frc.robot.Subsystem.Intake;

// import edu.wpi.first.wpilibj.I2C;
// import edu.wpi.first.wpilibj.command.Subsystem;

// public class Sensors extends Subsystem {

//   I2C I2CBus;

//   // 0x3E  // SX1509 I2C address (00)
//   // 0x3F  // SX1509 I2C address (01)
//   // 0x70  // SX1509 I2C address (10)
//   // 0x71  // SX1509 I2C address (11)

//   I2C sensor;

//   byte sensorOutput[];

//   public Sensors(){

//     // I2CBus = new I2C(I2C.Port.kOnboard, 0x1E);
//     // I2CBus.write(0x02, 0x00);

//     sensor = new I2C(I2C.Port.kOnboard, 0x3E);

//     //READS THE I^2C OUTPUT FROM PORT 0x3E WHICH IS 1 BYTE OF INPORTMATION AND THEN STORES IT IN THE ARRAY BUFFER -- sensorOutput
//     sensor.read(0x3E, 1 , sensorOutput);

//     //should print out an array of bytes output by sensor
//     System.out.println(sensorOutput);

//   }

//   public void setSensor(I2C newSensor){

//     newSensor = sensor;

//   }

//   @Override
//   public void initDefaultCommand() {
//   }
// }
