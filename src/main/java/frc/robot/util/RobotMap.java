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

  public static final int LeftDriveLeader = 1;

  public static final int RightDriveLeader = 2;
  public static final int RightDriveQuadA = 21;
  public static final int RightDriveQuadB = 20;

  public static final int LeftDriveFollower = 3;
  public static final int RightDriveFollower = 4;
  
  public static final int CamMotorLeft = 5;
  public static final int CamMotorRight = 6;
  
  public static final int ForwardChannel1 = 1;
  public static final int ReverseChannel1 = 2;
  public static final int ForwardChannel2 = 3;
  public static final int ReverseChannel2 = 4;
  public static final int ForwardChannel3 = 5;
  public static final int ReverseChannel3 = 6;
  public static final int ForwardChannel4 = 0;
  public static final int ReverseChannel4 = 7;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
