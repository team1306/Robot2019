package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
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
        grabSolenoid.set(Value.kReverse);
    }
    private boolean forward=false;
    private boolean grab=false;
    /**
     * Extends the grabbing mechanism. Does not toggle grab.
     */
    public void extend() {
        extendSolenoid.set(Value.kForward);
        forward=true;
        Timer.delay(0.1);
        extendSolenoid.set(Value.kOff);
    }

    /**
     * Retracts the grabbing mechanism. Does not toggle grab.
     */
    public void retract() {
        extendSolenoid.set(Value.kReverse);
        forward=false;
        Timer.delay(0.1);
        extendSolenoid.set(Value.kOff);
    }

    /**
     * @return true if the hatch is out
     */
    public boolean getExtension(){
        return forward;
    }
    /**
     * Widens the claws on the grabbing mechanism
     */
    public void grab() {
        grabSolenoid.set(Value.kForward);
        grab=true;
        Timer.delay(0.03);
        grabSolenoid.set(Value.kOff);
    }

    /**
     * Narrow the grabbing mechanism to drop a hatch
     */
    public void release() {
        grabSolenoid.set(Value.kReverse);
        grab=false;
        Timer.delay(0.03);
        grabSolenoid.set(Value.kOff);
    }

    /**
     * 
     */
    public void toggleGrab() {
        if (grab) {
            release();
        } else {
            grab();
        }
    }

    public boolean getGrabbing(){
        return grab;
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
        this.setDefaultCommand(Robot.hatchCommand());
    }

}