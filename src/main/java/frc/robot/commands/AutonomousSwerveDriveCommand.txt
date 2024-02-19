package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import Pigeon


public class AutonomousSwerveDriveCommand extends CommandBase {
    private final DrivetrainSubsystem swerveDriveSubsystem;
    private final Pose2d targetPose;
    private final double speed;

    public AutonomousSwerveDriveCommand(DrivetrainSubsystem swerveDriveSubsystem, Pose2d targetPose, double speed) {
        this.swerveDriveSubsystem = swerveDriveSubsystem;
        this.targetPose = targetPose;
        this.speed = speed;
        addRequirements(swerveDriveSubsystem);
    }

    @Override
    public void initialize() {
        // Your initialization code here
    }

    @Override
    public void execute() {
        Pose2d currentPose = swerveDriveSubsystem.getPosition();
        
        // Example: Calculate distance and angle to targetPose
        double distanceToTarget = currentPose.getTranslation().getDistance(targetPose.getTranslation());
        double angleToTarget = currentPose.getTranslation().getAngle() - targetPose.getTranslation().getAngle();

        // Example: Drive towards the targetPose with specified speed, distance, and angle
        swerveDriveSubsystem.driveToPose(targetPose, speed, distanceToTarget, angleToTarget);
    }

    @Override
    public void end(boolean interrupted) {
        // Your end code here
    }

    @Override
    public boolean isFinished() {
        // Example: Finish when the robot is close enough to the targetPose
        return swerveDriveSubsystem.isAtPose(targetPose);
    }
}