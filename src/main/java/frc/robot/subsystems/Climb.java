// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import frc.robot.Robot;
// import frc.robot.commands.DriveCommand;
// import frc.robot.util.RobotMap;

// /**
//  * Drivetrain subsystem to drive the robot. Uses DifferentialDrive
//  */
// public class Climb extends Subsystem {
//   // Put methods for controlling this subsystem
//   // here. Call these from Commands.
//   private WPI_TalonSRX motor1;
//   private WPI_TalonSRX motor2;
//   private DoubleSolenoid piston;

//   public Climb() {
//     //Initialize motors and Solenoids
//     motor1 = new WPI_TalonSRX(RobotMap.CamMotorLeft);
//     motor2 = new WPI_TalonSRX(RobotMap.CamMotorRight);
//     //piston = new DoubleSolenoid(RobotMap.ForwardChannel4,RobotMap.ReverseChannel4);

//   }

//   public void climbup()
//   {
//    Robot.reset();
   
//    piston.set(Value.kForward);
//    motor1.set(1);
//    motor2.set(1);
//   }





  

//   @Override
//   /**
//    * Initializes the Drivetrain default (when not run) to driving.
//    */
//   public void initDefaultCommand() {
//     setDefaultCommand(new DriveCommand());
//   }
// }
