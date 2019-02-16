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
  private static final int LTRIGGERBUTTON = 5;// L Trigger Button: Nothing
  private static final int RTRIGGERBUTTON = 6;// R Trigger Button: Nothing
  public static final int LJOYSTICKY = 1;
  public static final int LJOYSTICKX = 0;// L Joystick: Directional Steering
  public static final int RJOYSTICKY = 5;
  public static final int RJOYSTICKX = 4;// R Joystick: Cargo wheel output
  private static final int START = 7;// Start: Cancel All
  private static final int X = 3;// X: Toggle Hatch Grab
  private static final int Y = 4;// Y: Rocket Place
  private static final int A = 1;// A: Cargo Up
  private static final int B = 2;// B: Cargo Dow

  public Joystick primaryJoystick = null;
  public Joystick secondaryJoystick = null;

  // Command Buttons
  public Button cancelAll = null;
  public Button visionDrive = null;
  public Button toggleGrab = null;
  public Button placeRocket = null;
  public Button climb = null;
  public Button raiseArm = null;
  public Button lowerArm = null;
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
    placeRocket.whenPressed(Robot.placeRocketHatch());
    //Cargo
    raiseArm = new JoystickButton(primaryJoystick,A);
    lowerArm = new JoystickButton(primaryJoystick, B);
    // Vision
    visionDrive = new JoystickButton(primaryJoystick, 1);
    //visionDrive.whenPressed(Robot.visionDrive());

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