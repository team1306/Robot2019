package frc.robot.util;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.ResetAll;

public class SafetyOI extends OI {
    JoystickButton wheelIn, wheelOut, toggleExtension;

    @Override
    void initializeButtons() {
        wheelIn = new JoystickButton(primaryJoystick, A);
        wheelOut = new JoystickButton(primaryJoystick, B);

        toggleExtension=new JoystickButton(primaryJoystick,X);
        toggleExtension.whenPressed(new Command(){

            @Override
            protected void execute() {
                extension=!extension;
            }

            @Override
            protected boolean isFinished() {
                return true;
            }

        });
        // Make every button on secondary cancel
        Command cancel = new ResetAll();
        for (int i = 1; i < 11; i++) {
            if (!(i == A)) {// A is handeled in abstract method
                JoystickButton button = new JoystickButton(secondaryJoystick, i);
                button.whenPressed(cancel);
                button.whenReleased(new Command() {

                    @Override
                    protected void execute() {
                        cancel.cancel();
                    }

                    @Override
                    protected boolean isFinished() {
                        return true;
                    }
                });
            }
        }
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
        return primaryJoystick.getRawAxis(RTRIGGER)-primaryJoystick.getRawAxis(LTRIGGER);
    }

    @Override
    public double getDriveVelocity() {
        return primaryJoystick.getRawAxis(LJOYSTICKY);
    }

    @Override
    public double getDriveAngle() {
        return primaryJoystick.getRawAxis(X);
    }

    @Override
    public boolean visionEnabled() {
        return false;
    }

    boolean extension=false;

    @Override
    public boolean getExtension() {
        return extension;
    }

    @Override
    public Button getCancelButton() {
        return new JoystickButton(secondaryJoystick, A);
    }

    @Override
    public Button getGrabToggleButton() {
        return new JoystickButton(primaryJoystick, Y);
    }

    @Override
    public Button getReverseDirectionButton() {
        return new JoystickButton(primaryJoystick, BACK);
    }

    @Override
    public Button getVisionStartButton() {
        return new JoystickButton(primaryJoystick, START);
    }

    @Override
    public Boolean enableCargoLimit() {
        return primaryJoystick.getRawButton(LBUMPER);
    }

}