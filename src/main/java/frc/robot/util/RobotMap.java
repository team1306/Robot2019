/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.

  // Drive train
  public static final int LeftDriveLeader = 1;
  public static final int RightDriveLeader = 2;
  public static final int LeftDriveFollower = 3;
  public static final int RightDriveFollower = 4;

  // Cam motor
  public static final int CamMotorLeft = 6;
  public static final int CamMotorRight = 7;

  // Pneumatics for hatchtake
  public static final int ExtendForward = 4;
  public static final int ExtendReverse = 5;
  public static final int GrabChannel = 1;
  public static final int ReleaseChannel = 2;

  // Cargo system
  public static final int CargoArmMotor = 5;
  public static final int CargoWheelMotor = 0;//PWM, not can
}
