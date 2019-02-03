/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Command to drive the robot based on controller input.
 */
public class DriveCommand extends Command {

  private final boolean isArcade = true;

  public DriveCommand() {
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
    //Depending on the driver's preference, pass joystick values to drivetrain.
    if (isArcade) {
      Robot.driveTrain.arcadeDrive(-Robot.oi.primaryJoystick.getRawAxis(1), Robot.oi.primaryJoystick.getRawAxis(0));
    } else {
      Robot.driveTrain.drive(-Robot.oi.primaryJoystick.getRawAxis(1), -Robot.oi.primaryJoystick.getRawAxis(5));
    }
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
