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
import frc.robot.Robot;

/**
 * Command to drive the robot based on controller input.
 */
public class VisionDrive extends Command {
  public VisionDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable ntable = ntinst.getTable(Names.visionTableName);
    NetworkTableEntry angleEntry = ntable.getEntry(Names.visionCenterAngleName);
    double cosval = Math.cos(Math.PI/2+(double) (angleEntry.getNumber(Math.PI / 2)));
    System.out.println(angleEntry.getNumber(0.0));
    Robot.driveTrain.drive(0.25 - 0.25 * cosval, 0.25 + 0.25 * cosval);
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
