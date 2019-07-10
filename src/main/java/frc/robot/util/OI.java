/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ResetAll;
import frc.robot.commands.ReverseDrive;
import frc.robot.commands.ToggleHatchGrab;
import frc.robot.commands.VisionDrive;
import frc.robot.util.gamepadController;
import frc.robot.util.xboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public abstract class OI {

  // Controller Map:

  // now implemented in helper classes
  // protected static final int RTRIGGER = 3;
  // protected static final int LTRIGGER = 2;
  // protected static final int LTRIGGERBUTTON = 5;
  // protected static final int RTRIGGERBUTTON = 6;
  // protected static final int RBUMPER = RTRIGGERBUTTON;
  // protected static final int LBUMPER = LTRIGGERBUTTON;
  // protected static final int LJOYSTICKY = 1;
  // protected static final int LJOYSTICKX = 0;
  // protected static final int RJOYSTICKY = 5;
  // protected static final int RJOYSTICKX = 4;
  // protected static final int START = 8;
  // protected static final int BACK = 7;
  // protected static final int X = 3;
  // protected static final int Y = 4;
  // protected static final int A = 1;
  // protected static final int B = 2;

  // protected static final int BUTTON1 = 1;
  // protected static final int BUTTON2 = 2;
  // protected static final int BUTTON3 = 3;
  // protected static final int BUTTON4 = 4;
  // protected static final int BUTTON5 = 5;
  // protected static final int BUTTON6 = 6;
  // protected static final int BUTTON7 = 7;
  // protected static final int BUTTON8 = 8;
  // protected static final int JOYX = 10;
  // protected static final int JOYY = 11;

  public xboxController primaryJoystick = null;
  public gamepadController secondaryJoystick = null;
  // public Joystick secondaryJoystick = null;

  public OI() {
    // Initialize Joysticks
    primaryJoystick = new xboxController(0);
    secondaryJoystick = new gamepadController(1);
    initializeButtons();
    // Command Buttons
    // Cancel
    Command cancel = new ResetAll();
    Button cancelButton = getCancelButton();
    try {
      cancelButton.whenPressed(cancel);
      cancelButton.whenReleased(new Command() {

        @Override
        protected void execute() {
          cancel.cancel();
        }

        @Override
        protected boolean isFinished() {
          return true;
        }

      });
    } catch (NullPointerException e) {
      e.printStackTrace();
      System.out.println("OI null");
    }
    // Hatch take
    getGrabToggleButton().whenPressed(new ToggleHatchGrab());
    // Vision
    getVisionStartButton().whenPressed(new VisionDrive());
    // Drive
    getReverseDirectionButton().whenPressed(new ReverseDrive());
  }

  abstract void initializeButtons();

  /**
   * @return Percent output, from 1 to -1, of the cargo intake wheel spark.
   * 
   *         POSITIVE: In NEGATIVE: Out
   */
  public abstract double getWheelRotation();

  /**
   * @return Percent output, from 1 to -1, of the cargo arm motor.
   * 
   *         POSITIVE: Up NEGATIVE: Down
   * 
   *         TODO verify up/down
   */
  public abstract double getArmOutput();

  /**
   * @return Percent output, from 1 to -1, of the drive train.
   * 
   *         POSITIVE: Toward the cargo take side. NEGATIVE: Toward the hatch
   *         side.
   * 
   *         Values are sometimes switched to be reverse between OI and
   *         drivetrain.
   */
  public abstract double getDriveVelocity();

  /**
   * @return value, 1 to -1, to be interpereted by the drive train.
   * 
   *         POSITIVE: Right NEGATIVE: Left
   */
  public abstract double getDriveAngle();

  /**
   * @return if robot should be currently executing vision.
   */
  public abstract boolean visionEnabled();
  // Hatch

  /**
   * @return true if the hatch take should be extended, else false.
   */
  public abstract boolean getExtension();

  // Get buttons for executing commands

  /**
   * @return Joystick Button to be bound with calling cancel on press
   */
  public abstract Button getCancelButton();

  /**
   * @return Joystick Button to be bound with toggling grab on press
   */
  public abstract Button getGrabToggleButton();

  /**
   * @return Button to be bound to toggling drive direction
   */
  public abstract Button getReverseDirectionButton();

  /**
   * @return button to be bound with starting vision
   */
  public abstract Button getVisionStartButton();

  public abstract Boolean enableCargoLimit();
}