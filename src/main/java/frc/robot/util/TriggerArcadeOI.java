package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class TriggerArcadeOI extends OI {

    private Button hatchGrab = null;
    private Button extendHatch = null;
    private Button visionEnable = null;
    private Button changeDirection = null;
    private Button cancelAll = null;
    private Button armUp = null;
    private Button armDown = null;
    private Button wheelOut = null;
    private Button wheelIn = null;

    @Override
    void initializeButtons() {
        hatchGrab = new JoystickButton(primaryJoystick, X);
        extendHatch = new JoystickButton(primaryJoystick, A);
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
            return -1;
        } else if (wheelOut.get()) {
            return 1;
        }
        return 0;
    }

    @Override
    public double getArmOutput() {
        return secondaryJoystick.getRawAxis(LJOYSTICKY);

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
        try {
            return visionEnable.get();
        } catch (NullPointerException e) {
            initializeButtons();
            return visionEnabled();
        }
    }

    @Override
    public boolean getExtension() {
        return extendHatch.get();
    }

    @Override
    public Button getCancelButton() {
        if(cancelAll!=null){
        return cancelAll;
        }else{
            System.out.println("!!! Cancelall null");
            return new JoystickButton(primaryJoystick, START);
        }
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

    @Override
    public Boolean enableCargoLimit() {
        return !secondaryJoystick.getRawButton(X);
    }

}