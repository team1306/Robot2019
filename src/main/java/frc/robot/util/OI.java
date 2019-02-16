/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // Controller Map:
  private static final int RTRIGGER = 3;// R Trigger: Forward Drive
  private static final int LTRIGGER = 2;// L Trigger: Backward Drive
  private static final int LTRIGGERBUTTON = 4;// L Trigger Button: Nothing
  private static final int RTRIGGERBUTTON = 5;// R Trigger Button: Nothing
  private static final int LJOYSTICKY = 1;
  private static final int LJOYSTICKX = 0;// L Joystick: Directional Steering
  private static final int RJOYSTICKY = 5;
  private static final int RJOYSTICKX = 4;// R Joystick: Cargo wheel output
  private static final int START = 7;// Start: Cancel All
  private static final int X = 2;// X: Toggle Hatch Grab
  private static final int Y = 3;// Y: Rocket Place
  private static final int A = 0;// A: Cargo Up
  private static final int B = 1;// B: Cargo Dow

  public Joystick primaryJoystick = null;
  public Joystick secondaryJoystick = null;

  // Command Buttons
  public Button cancelAll = null;
  public Button visionDrive = null;
  public Button toggleGrab = null;
  public Button placeRocket = null;
  public Button climb = null;
  public static final int driveSpeedAxis = 1;

  public OI() {
    // Initialize Joysticks
    primaryJoystick = new Joystick(0);
    secondaryJoystick = new Joystick(1);
    // Command Buttons
    // Cancel
    cancelAll = new JoystickButton(primaryJoystick, START);
    cancelAll.whenPressed(Robot.reset());
    // Hatch take
    toggleGrab = new JoystickButton(primaryJoystick, X);
    toggleGrab.whenPressed(Robot.toggleHatchGrab());
    placeRocket = new JoystickButton(primaryJoystick, Y);
    // Vision
    visionDrive = new JoystickButton(primaryJoystick, 1);
    visionDrive.whenPressed(Robot.visionDrive());
    /*
     * Values are also being dynamicaly grabbed in the execute loop of varius
     * functions. These values include:
     * 
     * - Primary Left Joystick value for driving
     * 
     * - Secondary Left joystick value for cargo intake/outake wheel value
     * 
     * - Secondary Left and Right trigger values for arm speed values.
     */
  }
}