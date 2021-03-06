/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Names;

/**
 * Command to drive the robot based on vision targets for precise placement.
 * Relies on code running on a rasberry pi from
 * https://github.com/team1306/Vision2019
 */
public class VisionDrive extends Command {

  public VisionDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.reverse = true;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double magnitude = Robot.oi.getDriveVelocity();// the forward speed of the robot

    // Get values from network table
    NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
    NetworkTable ntable = ntinst.getTable(Names.visionTableName);
    NetworkTableEntry angleEntry = ntable.getEntry(Names.visionCenterAngleName);

    // start calculations for pathing
    double turnVal = 1.3*Math.min(Math.max(1.5 * (double) (angleEntry.getNumber(0.0)), -0.45), 0.45);
    // If left, add to value to turn right. If right, subtract.
    String position = ntable.getEntry(Names.visionPositionName).getString("u");// default to key unknown
    /*
     * Regarding below switchcase: 0.85 is a magic number that seems to work. The
     * general logic behind this block if code is: -we want to approach from center
     * line. -if left of target, turn right to get center -if right of target, turn
     * left to get center -we want to keep vision targets in view -the maximum turn
     * value is called right before we lose vision targets at ~0.9 -adding 0.85 to
     * the turn value will cause us to approach the center line at an angle of 0.85
     * (0.85 - 0.85 from camera = 0, or strait)
     */
    switch (position) {
    case "c":
      break;// Centered, do nothing
    case "r":
      turnVal -= 0.25;
      break;// left, see above multiline comment
    case "l":
      turnVal += 0.25;
      break;// right, see above multiline comment
    case "u":
      break;// unkown, do nothing
    // If a key other than center, left, right, or unkown is found, tell the driver
    default:
      System.err.println("Unexpected position key in VisionDrive.execute");
      break;
    }

    // clamp turnVal to a magic number to avoid overcorrection.
    turnVal = Math.min(Math.max(1.5 * (double) (angleEntry.getNumber(0.0)), -0.45), 0.45);

    // set path
    Robot.driveTrain.arcadeDrive(magnitude, turnVal);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.oi.visionEnabled();
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
