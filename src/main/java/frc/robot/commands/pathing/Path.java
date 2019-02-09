package frc.robot.commands.pathing;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
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
    // Instance code and methods
    Trajectory.Config trajConfig = null;
    Trajectory trajectory = null;
    Trajectory left = null;
    Trajectory right = null;

    Encoder rightEncoder = null;
    Encoder leftEncoder = null;
    EncoderFollower leftFollower = null;
    EncoderFollower rightFollower = null;

    static final double wheelbaseWidth = 22 * 2.54 / 100;// Wheelbase width in meters
    static final double updateTime = Robot.kDefaultPeriod;

    public Path() {
        requires(Robot.driveTrain);
        trajConfig = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH,
                updateTime, 1.7, 2.0, 60.0);
        trajectory = Pathfinder.generate(getWaypoints(), trajConfig);
    }

    @Override
    public void initialize() {
        TankModifier modifier = new TankModifier(trajectory).modify(wheelbaseWidth);
        // get individual side trajectories
        left = modifier.getLeftTrajectory();
        right = modifier.getRightTrajectory();
        // Set following paths
        leftFollower = new EncoderFollower(left);
        rightFollower = new EncoderFollower(right);
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
        double heading_difference = Pathfinder.boundHalfDegrees(desired_heading -
        heading);
        double turn = 0.8 * (-1.0/80.0) * heading_difference;
        Robot.driveTrain.drive(left_speed + turn,right_speed - turn);
    }

    @Override
    protected boolean isFinished() {
        return leftFollower.isFinished() || rightFollower.isFinished();
    }

    /**
     * @return an array of points to follow
     */
    abstract Waypoint[] getWaypoints();
}
