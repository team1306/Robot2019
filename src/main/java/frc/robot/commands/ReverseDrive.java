package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.util.Names;

public class ReverseDrive extends Command {
    @Override
    public void execute() {
        DriveTrain.reverse = !(DriveTrain.reverse);
        try {
          //  NetworkTableInstance.getDefault().getEntry(Names.driveDirectionName).setBoolean(DriveTrain.reverse);
        } catch (Exception e) {
            //under no conditions crash to robot for feedback
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}