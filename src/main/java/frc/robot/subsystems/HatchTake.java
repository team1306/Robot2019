package frc.robot.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.util.RobotMap;
import frc.robot.commands.HatchCommand;
public class HatchTake extends Subsystem
{
private int count;
private DoubleSolenoid spanel1;
private DoubleSolenoid spanel2;
private DoubleSolenoid sgrab;


public HatchTake()
{
spanel1 = new DoubleSolenoid(RobotMap.ForwardChannel1, RobotMap.ReverseChannel1);
spanel2 = new DoubleSolenoid(RobotMap.ForwardChannel2, RobotMap.ReverseChannel2);
sgrab = new DoubleSolenoid(RobotMap.ForwardChannel3, RobotMap.ReverseChannel3);

}
public void extend()
{
 spanel1.set(Value.kForward);
 spanel2.set(Value.kForward);
}
public void reverse()
{
spanel1.set(Value.kReverse);
spanel2.set(Value.kReverse);

}
public void grab()
{
sgrab.set(Value.kForward);

}
public void release()
{
    sgrab.set(Value.kReverse);
}
public void toggle()
{
if(sgrab.get().equals(Value.kForward))
{
    release();
}
else{
    grab();
}

}
public void toggles()
{
    if(spanel1.get().equals(Value.kForward) && spanel2.get().equals(Value.kForward))
    {
        reverse();
    }
    else{
        extend();
    }  
}
@Override
protected void initDefaultCommand() {
    setDefaultCommand(new HatchCommand());
}

}