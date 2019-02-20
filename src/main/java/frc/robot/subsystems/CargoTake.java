package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.util.RobotMap;

public class CargoTake extends Subsystem {

   private static final int encoderValueGround = -2297;
   private static final int encoderValuePlace = -1089;

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
      armMotor.getSensorCollection().setQuadraturePosition(0,0);
      wheelMotor = new Spark(RobotMap.CargoWheelMotor);
   }

   public void spinWheel(double value) {
      wheelMotor.set(value);
   }

   public void setArmOutput(double value) {
      if(Math.abs(value)>0.1){
      armMotor.set(value);
      }else{
         brakeArm();
      }
   }
   public void setPosition(double value){
      pos=value;
   }

   public void brakeArm() {
      armMotor.stopMotor();
   }
   /**
    * Sets the arm to moving toward pos
    */
   public void updatePos() {
      //Find both the direction and velocity the arm should be traveling at
      double weight=(pos+1.0)/2;
      double goalPos=weight*encoderValuePlace+(1-weight)*encoderValueGround;
      double vel=(goalPos-getEncoderValue())/(encoderValuePlace-encoderValueGround);
      vel=Math.max(-1.0,Math.min(1.0,vel));
      setArmOutput(-vel);
   }

   public double getPercent() {
      return (getEncoderValue() - encoderValuePlace) / (encoderValueGround - encoderValuePlace + 0.0);
   }

   public int getEncoderValue() {
      return -armMotor.getSensorCollection().getQuadraturePosition();
   }

   @Override
   protected void initDefaultCommand() {
      setDefaultCommand(Robot.cargoCommand());
   }

}