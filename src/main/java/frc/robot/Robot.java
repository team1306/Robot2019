/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.AutonomousCommand;
import frc.robot.commands.CargoCommand;
import frc.robot.commands.HatchCommand;
import frc.robot.commands.ResetAll;
import frc.robot.commands.ToggleHatchGrab;
import frc.robot.commands.VisionDrive;
import frc.robot.subsystems.CargoTake;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.HatchTake;
import frc.robot.util.OI;
import frc.robot.util.StickArcadeOI;
import frc.robot.util.TandemExtensionOI;
import frc.robot.util.TriggerArcadeOI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // Driver/OI pairings
  private static final int STICKARCADE = 0;
  private static final int TRIGGERARCADE = 1;
  private static final int TANDEMEXTENSION = 2;
  // Drivers
  private static final int WALKER = TANDEMEXTENSION;
  private static final int EGAN = STICKARCADE;
  private static final int ETHAN = TRIGGERARCADE;
  /*
   * .._____................................_....._____........._.................
   * ./.____|..............................|.|...|..__.\.......(_)................
   * |.|....._..._.._.__.._.__..___.._.__..|.|_..|.|..|.|._.__.._.__...__.___.._.
   * |.|....|.|.|.||.'__||.'__|/._.\|.'_.\.|.__|.|.|..|.||.'__||.|\.\././/._.\|.'|
   * |.|____|.|_|.||.|...|.|..|..__/|.|.|.||.|_..|.|__|.||.|...|.|.\.V./|..__/|.|.
   * .\_____|\__,_||_|...|_|...\___||_|.|_|.\__|.|_____/.|_|...|_|..\_/..\___||_|
   * .\/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/ \/
   */
  private static int currentDriver = WALKER;
  // Commands
  public static Command autonomous = null;

  public static Command reset() {
    return new ResetAll();
  }

  public static Command visionDrive() {
    return new VisionDrive();
  }

  public static Command toggleHatchGrab() {
    return new ToggleHatchGrab();
  }

  public static Command hatchCommand() {
    return new HatchCommand();
  }

  public static Command cargoCommand() {
    return new CargoCommand();
  }
  // public static Command climbCommand()
  // {
  // return new ClimbCommand();

  // }
  // Subsystems
  public static AHRS gyro = null;
  public static DriveTrain driveTrain = new DriveTrain();
  public static HatchTake hatchTake = new HatchTake();
  public static CargoTake cargoTake = new CargoTake();
  // public static Climb climb = new Climb();

  // Array of all subsystems. Please add all new subsystems to this array
  public final static Subsystem[] allSubsystems = { driveTrain, hatchTake, cargoTake };

  public static OI oi = null;
  public static OI getOI(){
    switch (currentDriver) {
      case TRIGGERARCADE:
        return new TriggerArcadeOI();
      case STICKARCADE:
        return new StickArcadeOI();
      case TANDEMEXTENSION:
        return new TandemExtensionOI();
      default:
        return new TriggerArcadeOI();
      }
  }

  public Robot(){
    super();
    if(oi==null){
      oi=getOI();
    }
  }
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
  
    gyro = new AHRS(Port.kMXP);
    NetworkTableInstance.getDefault().startServer();
    UsbCamera camera=CameraServer.getInstance().startAutomaticCapture();
    camera.setBrightness(50);
    camera.setVideoMode(new VideoMode(PixelFormat.kMJPEG,  180, 120, 20));
    //camera.setResolution(320, 240);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * 
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
    hatchTake.release();
    hatchTake.retract();
  }

  @Override
  public void disabledPeriodic() {
    // System.out.printf("Pitch:%f, Yaw:%f, Roll:%f\n", gyro.getPitch(),
    // gyro.getYaw(), gyro.getRoll());
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *  
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomous = new AutonomousCommand();
    hatchTake.grab();
    DriveTrain.reverse=true;//drive toward hatch side
    // autonomous.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    if (Math.abs(oi.primaryJoystick.getRawAxis(0)) > 0.75) {
      autonomous.cancel();
    }
  }

  @Override
  public void teleopInit() {
    if (!(autonomous == null)) {
      autonomous.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    if (hatchTake.getGrabbing()) {
      oi.primaryJoystick.setRumble(GenericHID.RumbleType.kRightRumble, 1);
    } else {
      oi.primaryJoystick.setRumble(GenericHID.RumbleType.kRightRumble, 0);
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    Scheduler.getInstance().run();
  }
}
