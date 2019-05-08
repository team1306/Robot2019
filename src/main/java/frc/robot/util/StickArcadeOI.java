/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class StickArcadeOI extends OI {

  // Command Buttons
  private Button cancelAll = null;
  private Button visionDrive = null;
  private Button toggleGrab = null;
  private Button armUp = null;
  private Button armDown = null;
  private Button wheelOut = null;
  private Button wheelIn = null;
  private Button reverseDrive = null;
  public static final int driveSpeedAxis = 1;

  @Override
  public void initializeButtons() {
    // Command Buttons
    // Cancel
    cancelAll = new JoystickButton(primaryJoystick, START);
    // Hatch take
    toggleGrab = new JoystickButton(primaryJoystick, X);
    // Cargo
    armUp = new JoystickButton(secondaryJoystick, LTRIGGERBUTTON);
    armDown = new JoystickButton(secondaryJoystick, RTRIGGERBUTTON);
    wheelOut = new JoystickButton(secondaryJoystick, Y);
    wheelIn = new JoystickButton(secondaryJoystick, B);
    // Vision
    visionDrive = new JoystickButton(primaryJoystick, 2);
    // Drive
    reverseDrive = new JoystickButton(primaryJoystick, LTRIGGERBUTTON);
  }

  // Accessors
  // Cargo

  @Override
  public double getWheelRotation() {
    if (wheelIn.get()) {
      return -1;
    } else if (wheelOut.get()) {
      return 1;
    }
    return 0;
  }

  @Override
  public double getArmOutput() {
    try {
      return secondaryJoystick.getRawAxis(LJOYSTICKY);
    } catch (NullPointerException e) {
      initializeButtons();
      return getArmOutput();
    }
  }

  // Drive
  @Override
  public double getDriveVelocity() {
    return -primaryJoystick.getRawAxis(LJOYSTICKY);
  }

  @Override
  public double getDriveAngle() {
    return primaryJoystick.getRawAxis(LJOYSTICKX);
  }

  @Override
  public boolean visionEnabled() {
    return primaryJoystick.getRawAxis(LTRIGGER) > 0.5;
  }

  // Hatch
  @Override
  public boolean getExtension() {
    System.out.println(primaryJoystick.getRawAxis(RTRIGGER));
    return primaryJoystick.getRawAxis(RTRIGGER) < 0.1;
  }

  @Override
  public Button getCancelButton() {
    return cancelAll;
  }

  @Override
  public Button getGrabToggleButton() {
    return toggleGrab;
  }

  @Override
  public Button getReverseDirectionButton() {
    return reverseDrive;
  }

  @Override
  public Button getVisionStartButton() {
    return visionDrive;
  }

  @Override
  public Boolean enableCargoLimit() {
    return !secondaryJoystick.getRawButton(X);
  }

}