/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Names;
import frc.robot.OI;
import frc.robot.Robot;

/**
 * Command to drive the robot based on controller input.
 */
public class VisionDrive extends Command {

  long time=0l;

  public VisionDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.time=System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double magnitude=-Robot.oi.primaryJoystick.getRawAxis(OI.driveSpeedAxis);//the forward speed of the robot
    if(System.currentTimeMillis()<time+2000l){
      //If less than one second in to the command, implement minumum speed to facilitate convergance.
      magnitude=Math.max(magnitude,0.5);
      System.out.println("Overriding Stick Input for Vision Drive");
    }
    System.out.println(System.currentTimeMillis()+" - "+time);
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable ntable = ntinst.getTable(Names.visionTableName);
    NetworkTableEntry angleEntry = ntable.getEntry(Names.visionCenterAngleName);
    double turnVal=Math.min(Math.max((double)(angleEntry.getNumber(0.0)),-0.55),0.55);
    Robot.driveTrain.arcadeDrive(magnitude,turnVal);
    System.out.println("Vision Drive Magnitude: "+magnitude);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
