package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;

public class ReverseDrive extends Command {
    @Override
    public void initialize(){
        DriveTrain.reverse=!DriveTrain.reverse;
    }
    @Override
    public boolean isFinished(){
        return true;
    }
}