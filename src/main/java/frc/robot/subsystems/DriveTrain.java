/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DriveCommand;
import frc.robot.util.RobotMap;

/**
 * Drivetrain subsystem to drive the robot. Uses DifferentialDrive
 */

public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonSRX leftLeader;
  private WPI_TalonSRX rightLeader;

  private WPI_VictorSPX leftFollower;
  private WPI_VictorSPX rightFollower;

  public SensorCollection leftSensors;
  public SensorCollection rightSensors;

  private DifferentialDrive tankDrive;

  public static boolean reverse = false;

  public DriveTrain() {
    // Initialize motors
    leftLeader = new WPI_TalonSRX(RobotMap.LeftDriveLeader);
    rightLeader = new WPI_TalonSRX(RobotMap.RightDriveLeader);

    leftFollower = new WPI_VictorSPX(RobotMap.LeftDriveFollower);
    rightFollower = new WPI_VictorSPX(RobotMap.RightDriveFollower);

    leftFollower.follow(leftLeader);
    rightFollower.follow(rightLeader);

    // Initialize sensor values
    leftSensors = leftLeader.getSensorCollection();
    rightSensors = rightLeader.getSensorCollection();

    // Initialize DifferentialDrive object for use later
    tankDrive = new DifferentialDrive(leftLeader, rightLeader);
    tankDrive.setDeadband(0.05);
  }

  /**
   * Pushes up {@link DifferentialDrive}.tankDrive
   * 
   * DifferentialDrive documentation: Tank drive method for differential drive
   * platform. The calculated values will be squared to decrease sensitivity at
   * low speeds.
   *
   * @param left  The robot's left side speed along the X axis [-1.0..1.0].
   *              Forward is positive.
   * @param right The robot's right side speed along the X axis [-1.0..1.0].
   *              Forward is positive.
   */
  public void drive(double left, double right) {
    if (reverse) {
      left = -left;
      right = -right;
    }
    tankDrive.tankDrive(left, right);
  }

  /**
   * Pushes up edu.wpi.first.wpilibj.drive.DifferentialDrive.arcadeDrive
   * 
   * DifferentailDrive documentation: Arcade drive method for differential drive
   * platform. The calculated values will be squared to decrease sensitivity at
   * low speeds.
   *
   * @param speed    The robot's speed along the X axis [-1.0..1.0]. Forward is
   *                 positive.
   * @param rotation The robot's rotation rate around the Z axis [-1.0..1.0].
   *                 Clockwise is positive.
   */
  public void arcadeDrive(double speed, double rotation) {
    if (reverse) {
      speed = -speed;
    }
    tankDrive.arcadeDrive(speed, rotation);
  }

  @Override
  /**
   * Initializes the Drivetrain default (when not run) to driving.
   */
  public void initDefaultCommand() {
    setDefaultCommand(new DriveCommand());
  }
}
