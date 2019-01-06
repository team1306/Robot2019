/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveCommand;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX leftLeader;
  private WPI_TalonSRX rightLeader;
  private VictorSPX leftFollower;
  private VictorSPX rightFollower;
  private DifferentialDrive tankDrive;
  public DriveTrain()
  {
    leftLeader = new WPI_TalonSRX(RobotMap.LeftDriveLeader);
    rightLeader = new WPI_TalonSRX(RobotMap.RightDriveLeader);
    tankDrive = new DifferentialDrive(leftLeader, rightLeader);
  }

  public void drive(double left,double right){
    tankDrive.tankDrive(left, right);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
}