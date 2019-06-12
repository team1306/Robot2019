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
 * Command to grab hatch from loading station
 * 
 * For placing on the rocket, see: GrabHatchRocket
 */
public class  ToggleHatchGrab extends Command {

  public ToggleHatchGrab() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.hatchTake);
  }
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.hatchTake.toggleGrab();
    try {
    //  NetworkTableInstance.getDefault().getEntry(Names.toggleHatchName).setBoolean(DriveTrain.reverse);
  } catch (Exception e) {
      //under no conditions crash to robot for feedback
      e.printStackTrace();
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }
}
