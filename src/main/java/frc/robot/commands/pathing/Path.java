package frc.robot.commands.pathing;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.PathfinderFRC;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

@FunctionalInterface
interface Encoder {
    int get();
}

/**
 * A class for initializing and executing paths.
 */
public abstract class Path extends Command {

    private static final double wheelbaseWidth = 0.5588;// = 22 * 2.54 / 100, Wheelbase width in meters
    private static final double updateTime = Robot.kDefaultPeriod;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    // Names for use in path creation 
    public static final String RIGHTCARGOTOLOADING = "RightLoadingToCargoGeneric";
    public static final String LEFTCARGOTOLOADING = "LeftLoadingToCargoGeneric";
    public static final String CENTERTOLEFTLOADING = "";
    // Instance code and methods
    Trajectory.Config trajConfig = null;
    Trajectory[] trajectories = null;
    Trajectory left = null;
    Trajectory right = null;

    Encoder rightEncoder = null;
    Encoder leftEncoder = null;
    EncoderFollower leftFollower = null;
    EncoderFollower rightFollower = null;

    private String pathName = null;

    public Path(String name) {
        requires(Robot.driveTrain);
        pathName = name;
        trajConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
                updateTime, 1.7, 2.0, 60.0);
        trajectories = getTrajectory();
    }

    @Override
    public void initialize() {
        // Set following paths
        leftFollower = new EncoderFollower(trajectories[LEFT]);
        rightFollower = new EncoderFollower(trajectories[RIGHT]);
        // get encoder lambdas
        rightEncoder = (() -> {
            return Robot.driveTrain.rightSensors.getQuadraturePosition();
        });
        leftEncoder = (() -> {
            return Robot.driveTrain.leftSensors.getQuadraturePosition();
        });

        Robot.gyro.reset();
    }

    // Taken from
    // https://wpilib.screenstepslive.com/s/currentCS/m/84338/l/1021631-integrating-path-following-into-a-robot-program
    @Override
    public void execute() {
        double left_speed = leftFollower.calculate(rightEncoder.get());
        double right_speed = rightFollower.calculate(leftEncoder.get());
        double heading = Robot.gyro.getYaw();
        double desired_heading = Pathfinder.r2d(leftFollower.getHeading());
        double heading_difference = Pathfinder.boundHalfDegrees(desired_heading - heading);
        double turn = 0.8 * (-1.0 / 80.0) * heading_difference;
        Robot.driveTrain.drive(left_speed + turn, right_speed - turn);
    }

    @Override
    protected boolean isFinished() {
        return leftFollower.isFinished() || rightFollower.isFinished();
    }

    /**
     * @return Trajectory[] {left, right}
     */
    private Trajectory[] getTrajectory() {
        Trajectory left = PathfinderFRC.getTrajectory(pathName + ".left.pf1.csv");
        Trajectory right = PathfinderFRC.getTrajectory(pathName + ".right.pf1.csv");
        return new Trajectory[] { left, right };
    }

}
