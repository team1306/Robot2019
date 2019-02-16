package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.util.RobotMap;

public class CargoTake extends Subsystem {

   private WPI_TalonSRX armMotor;
   private Spark wheelMotor;
   private DigitalInput limiter;

   public CargoTake() {
      armMotor = new WPI_TalonSRX(RobotMap.CargoArmMotor);
      wheelMotor = new Spark(RobotMap.CargoWheelMotor);
   }

   public void spinWheel(double value) {
      wheelMotor.set(value);
   }

   public void moveArm(double value){
      armMotor.set(value);
   }

   @Override
   protected void initDefaultCommand() {
      setDefaultCommand(Robot.cargoCommand());
   }

}