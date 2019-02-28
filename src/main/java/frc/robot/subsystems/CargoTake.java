package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.util.RobotMap;

public class CargoTake extends Subsystem {

   private static final int encoderValueGround = 2297;
   private static final int encoderValuePlace = 1089;

   private static final double COUNTERGRAVITY=0.05;

   public static final int GROUND = -1;
   public static final int PLACE = 1;

   private WPI_TalonSRX armMotor;
   private WPI_VictorSPX armMotorFollower;
   private Spark wheelMotor;
   private double pos = 0;//The goal encoder value

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
      if(Math.abs(value)>COUNTERGRAVITY){
      armMotor.set(value);
      }else{
         brakeArm();
      }
   }
   public void setPosition(double value){
      double weight=(value+1)/2;//what weight should be given to the place position
      pos=encoderValueGround*weight+(1-weight)*encoderValueGround;
   }

   public void brakeArm() {
      armMotor.stopMotor();
   }
   /**
    * Sets the arm to moving toward pos. Its really just P.
    */
   public void runPID() {
      double deltaE=getEncoderValue()-pos;//distance between goal and current encoder values
      double rangePercent= deltaE/(encoderValueGround-encoderValuePlace);
      double output=0;
      if(Math.abs(deltaE)>0.5){
         output=deltaE/Math.abs(deltaE);//either 1 or -1
      }else{
         output=-64*rangePercent*rangePercent*rangePercent*Math.abs(rangePercent)*(Math.abs(rangePercent)-0.625)+COUNTERGRAVITY;
      }
      output=Math.max(Math.min(output,1),-1);
   }

   public int getEncoderValue() {
      //For the left hand leader, the arm moves opposite to the rotation of the motor shaft.
      return -armMotor.getSensorCollection().getQuadraturePosition();
   }

   @Override
   protected void initDefaultCommand() {
      setDefaultCommand(Robot.cargoCommand());
   }

}