package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.RobotMap;

public class HatchTake extends Subsystem {
    private DoubleSolenoid extendSolenoid;
    private DoubleSolenoid grabSolenoid;

    /*
     * Times for each action. Important for use in rocket placement, because we need
     * to fully execute each segment of the operation before continuing on to the
     * next.
     */
    public final double EXTENDTIME = 1.0;
    public final double GRABTIME = 0.1;
    public final double RELEASETIME = 0.1;
    public final double RETRACTIME = 1.0;

    public HatchTake() {
        extendSolenoid = new DoubleSolenoid(RobotMap.ExtendForward, RobotMap.ExtendReverse);
        grabSolenoid = new DoubleSolenoid(RobotMap.GrabChannel, RobotMap.ReleaseChannel);

    }

    /**
     * Extends the grabbing mechanism. Does not toggle grab.
     */
    public void extend() {
        extendSolenoid.set(Value.kForward);
    }

    /**
     * Retracts the grabbing mechanism. Does not toggle grab.
     */
    public void retract() {
        extendSolenoid.set(Value.kReverse);

    }

    /**
     * Widens the claws on the grabbing mechanism
     */
    public void grab() {
        grabSolenoid.set(Value.kForward);

    }

    /**
     * Narrow the grabbing mechanism to drop a hatch
     */
    public void release() {
        grabSolenoid.set(Value.kReverse);
    }

    /**
     * 
     */
    public void toggleGrab() {
        if (grabSolenoid.get().equals(Value.kForward)) {
            release();
        } else {
            grab();
        }

    }

    /**
     * Toggles the extension of the grabing mechanism. If extended, pulls back, and
     * if near, pushes out.
     */
    public void toggleExtension() {
        if (extendSolenoid.get().equals(Value.kForward)) {
            retract();
        } else {
            extend();
        }
    }

    @Override
    protected void initDefaultCommand() {
        // Don't want default, is called on button.
    }

}