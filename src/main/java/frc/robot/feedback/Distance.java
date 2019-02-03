package frc.robot.feedback;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * A class for managing encoder values and distances.
 * 
 * Static methods give quick functionality, while creating objects around certain 
 * sets of motors gives more options. <-- NOT YET IMPLEMENTED
 * @author Egan Johnson, Badgerbots 1306
 */
public class Distance {
    
    /**
     * 
     * @param motor - the Talon with connected quaderature
     * @return the raw rotation value of 
     */
    public static int rawValue(WPI_TalonSRX motor){
        return motor.getSensorCollection().getQuadraturePosition();
    }

    /**
     * Sets the position of the motor to zero (does not actualy move motor).
     * There is a possibility of failure, and this is not checked by the program.
     * To ensure sucess, call motor.getSensorCollection.setQuaderaturePosition yourself.
     * @param motor
     */
    public static void resetValue(WPI_TalonSRX motor){
        motor.getSensorCollection().setQuadraturePosition(0, 0);
    }
}