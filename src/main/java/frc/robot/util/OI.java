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
import frc.robot.commands.ReverseDrive;
import frc.robot.commands.VisionDrive;

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
  public Button armUp = null;
  public Button armDown = null;
  public Button wheelOut = null;
  public Button wheelIn = null;
  public Button reverseDrive = null;
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
    // Cargo
    armUp = new JoystickButton(secondaryJoystick, LTRIGGERBUTTON);
    armDown = new JoystickButton(secondaryJoystick, RTRIGGERBUTTON);
    wheelOut = new JoystickButton(secondaryJoystick, Y);
    wheelIn = new JoystickButton(secondaryJoystick, B);
    // Vision
    visionDrive = new JoystickButton(primaryJoystick, 2);
    visionDrive.whenPressed(new VisionDrive());
    // Drive
    reverseDrive=new JoystickButton(primaryJoystick,LTRIGGERBUTTON);
    reverseDrive.whenPressed(new ReverseDrive());
  }

  // Accessors
  // Cargo
  public double getArmPosition() {
    if (armUp.get()) {
      return Robot.cargoTake.PLACE;
    } else if (armDown.get()) {
      return (Robot.cargoTake.GROUND);
    } else {
      return 1;
    }
  }

  public double getWheelRotation() {
    if (wheelIn.get()) {
      return 1;
    } else if (wheelOut.get()) {
      return -1;
    }
    return 0;
  }

  public double getArmOutput() {
    if (armUp.get()) {
      return 0.5;
    } else if (armDown.get()) {
      return -0.5;
    } else {
      return 0;
    }
  }

  // Drive
  public double getDriveVelocity() {
    return -primaryJoystick.getRawAxis(LJOYSTICKY);
  }

  public double getDriveAngle() {
    return primaryJoystick.getRawAxis(LJOYSTICKX);
  }
  
  public boolean visionEnabled(){
    return primaryJoystick.getRawAxis(LTRIGGER)>0.5;
  }
  // Hatch

  public boolean getExtension() {
    System.out.println(primaryJoystick.getRawAxis(RTRIGGER));
    return primaryJoystick.getRawAxis(RTRIGGER) < 0.1;
  }


}