package frc.robot.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.util.RobotMap;
import frc.robot.commands.HatchCommand;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
public class CargoTake extends Subsystem
{

private WPI_TalonSRX mainmotor;
private WPI_TalonSRX wheelmotor;
private Timer time;
private DigitalInput limiter;

public CargoTake()
{
mainmotor = new WPI_TalonSRX(RobotMap.CargoMotorMain);
wheelmotor = new WPI_TalonSRX(RobotMap.CargoMotorWheel);
time = new Timer();
// Switch for Limiter

}
public void intake()
{
mainmotor.set(.5);
wheelmotor.set(1);

}
public void raise()
{
   mainmotor.set(-.5);
   wheelmotor.set(-1);

}
public boolean limit()
{
   return limiter.get();
}

@Override
protected void initDefaultCommand() {
    setDefaultCommand(new HatchCommand());
}

}