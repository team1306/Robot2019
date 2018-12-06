/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.TestCommand;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class TestSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark spark;

  public TestSubsystem()
  {
    spark = new Spark(RobotMap.sparkChannel);
  }

  public void runSpark()
  {
    spark.set(0.1);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TestCommand());
  }
}
