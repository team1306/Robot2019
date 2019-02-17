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
public class CargoCommand extends Command {

  public CargoCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.cargoTake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Set goal position to something read from control
    Robot.cargoTake.setPosition(Robot.oi.getArmPosition());
    //Set to follow goal position
    //Robot.cargoTake.updatePos();
    Robot.cargoTake.setArmOutput(Robot.oi.getArmOutput());
    //Set wheel output
    Robot.cargoTake.spinWheel(Robot.oi.getWheelRotation());
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
