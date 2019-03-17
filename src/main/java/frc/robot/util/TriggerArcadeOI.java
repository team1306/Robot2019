package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class TriggerArcadeOI extends OI {

    Button hatchGrab = null;
    Button extendHatch = null;
    Button visionEnable = null;
    Button changeDirection = null;
    Button cancelAll = null;
    Button armUp = null;
    Button armDown = null;
    Button wheelOut = null;
    Button wheelIn = null;

    @Override
    void initializeButtons() {
        hatchGrab = new JoystickButton(primaryJoystick, X);
        extendHatch = new JoystickButton(primaryJoystick, RTRIGGERBUTTON);
        visionEnable = new JoystickButton(primaryJoystick, BACK);
        changeDirection = new JoystickButton(primaryJoystick, LTRIGGERBUTTON);
        cancelAll = new JoystickButton(primaryJoystick, START);
        // Cargo
        armUp = new JoystickButton(secondaryJoystick, LTRIGGERBUTTON);
        armDown = new JoystickButton(secondaryJoystick, RTRIGGERBUTTON);
        wheelOut = new JoystickButton(secondaryJoystick, Y);
        wheelIn = new JoystickButton(secondaryJoystick, B);
    }

    @Override
    public double getWheelRotation() {
        if (wheelIn.get()) {
            return 1;
        } else if (wheelOut.get()) {
            return -1;
        }
        return 0;
    }

    @Override
    public double getArmOutput() {
        if (armUp.get()) {
            return 0.5;
        } else if (armDown.get()) {
            return -0.5;
        } else {
            return 0;
        }
    }

    @Override
    public double getDriveVelocity() {
        return primaryJoystick.getRawAxis(RTRIGGER) - primaryJoystick.getRawAxis(LTRIGGER);
    }

    @Override
    public double getDriveAngle() {
        return primaryJoystick.getRawAxis(LJOYSTICKX);
    }

    @Override
    public boolean visionEnabled() {
        return visionEnable.get();
    }

    @Override
    public boolean getExtension() {
        return primaryJoystick.getRawButton(RTRIGGERBUTTON);
    }

    @Override
    public Button getCancelButton() {
        return cancelAll;
    }

    @Override
    public Button getGrabToggleButton() {
        return hatchGrab;
    }

    @Override
    public Button getReverseDirectionButton() {
        return changeDirection;
    }

    @Override
    public Button getVisionStartButton() {
        return visionEnable;
    }

}