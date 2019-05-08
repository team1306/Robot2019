package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.util.RobotMap;

public class CargoTake extends Subsystem {

   private static final int encoderUppermost = 720;
   private static final int encoderLowermost = 2406;// TODO
   /*
    * Practice Bot: Start: 0 Place:700 Ground:2156 Breaks gears: 2542
    */

   public static final int GROUND = -1;
   public static final int PLACE = 1;

   private WPI_TalonSRX armMotor;
   private WPI_VictorSPX armMotorFollower;
   private Spark wheelMotor;
   private double pos = 0;

   public CargoTake() {
      armMotor = new WPI_TalonSRX(RobotMap.CargoArmMotor);
      armMotor.setNeutralMode(NeutralMode.Brake);
      armMotorFollower = new WPI_VictorSPX(RobotMap.CargoArmFollowerMotor);
      armMotorFollower.setNeutralMode(NeutralMode.Brake);
      armMotorFollower.setInverted(true);
      armMotorFollower.follow(armMotor);
      armMotor.getSensorCollection().setQuadraturePosition(0, 0);
      wheelMotor = new Spark(RobotMap.CargoWheelMotor);
   }

   public void spinWheel(double value) {
      wheelMotor.set(value);
   }

   public void setArmOutput(double value) {
      int encoderValue = getEncoderValue();
      if (encoderValue < encoderUppermost + 10) {
         if(Robot.oi.enableCargoLimit()||encoderValue<10){
            value = Math.max(0, value);// Cannot move up more
         }else{
            value = Math.max(-0.3, value);// move up more, but slowly
         }
      } else if (encoderValue > encoderLowermost - 10) {
         value = Math.min(0, value);// Cannot move down more
      }
      if (Math.abs(value) > 0.1) {
         armMotor.set(value);
      } else {
         brakeArm();
      }
   }

   public void setPosition(double value) {
      pos = value;
   }

   public void brakeArm() {
      armMotor.stopMotor();
   }

   public int getEncoderValue() {
      return armMotor.getSensorCollection().getQuadraturePosition();
   }

   @Override
   protected void initDefaultCommand() {
      setDefaultCommand(Robot.cargoCommand());
   }

}