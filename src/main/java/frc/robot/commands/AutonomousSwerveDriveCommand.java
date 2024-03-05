package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Pigeon;
import edu.wpi.first.math.MathUtil;
import java.lang.Math;



public class AutonomousSwerveDriveCommand extends Command {
    protected final DrivetrainSubsystem drivetrain;
    private final BooleanSupplier fieldCentricSupplier;
    private double destinationX;
    private double destinationY;
    private double destinationAngle;
    private double rotationSpeed;
    private double driveSpeed;
    private double distanceMargin;
    private double rotationMargin;
    
    private final SlewRateLimiter xLimiter;
    private final SlewRateLimiter yLimiter;
    private final SlewRateLimiter rotationLimiter;
    private final Pigeon spins;

    public AutonomousSwerveDriveCommand( 
        BooleanSupplier fieldCentricSupplier,
        DrivetrainSubsystem drivetrain,
        double destinationX,
        double destinationY,
        double destinationAngle,
        double rotationSpeed,
        double driveSpeed,
        double distanceMargin,
        double rotationMargin,
        Pigeon spins
        ) {
        this.drivetrain = drivetrain;
        this.spins = spins;
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.destinationAngle = destinationAngle;
        this.rotationSpeed = rotationSpeed;
        this.driveSpeed = driveSpeed;
        this.distanceMargin = distanceMargin;
        this.rotationMargin = rotationMargin;

        this.fieldCentricSupplier = fieldCentricSupplier;
        xLimiter = new SlewRateLimiter(2);
        yLimiter = new SlewRateLimiter(2);
        rotationLimiter = new SlewRateLimiter(5);
        
        addRequirements(drivetrain, spins);
    }

    @Override
    public void initialize() {
        // Your initialization code here
    }

    @Override
    public void execute() {
        /*double destinationX = 1;
        double destinationY = 1;
        double destinationAngle = 90;
        double rotationSpeed = 1;
        double driveSpeed = 0.1;
        double distanceMargin = 0.1;
        double rotationMargin = 1;*/

        double vectorX = destinationX - drivetrain.getPosition().getX();
        double vectorY = destinationY - drivetrain.getPosition().getX();

        double vectorMagnitude = Math.sqrt(Math.pow(vectorX, 2) + Math.pow(vectorY, 2));
        double normalizedVectorX = vectorX / vectorMagnitude;
        double normalizedVectorY = vectorY / vectorMagnitude;

        if((destinationAngle - spins.getangle() <= rotationMargin)){
            rotationSpeed = 0;
        }

        if (vectorMagnitude >= distanceMargin){
            drivetrain.drive(normalizedVectorX * driveSpeed, -normalizedVectorY * driveSpeed, -rotationSpeed, fieldCentricSupplier.getAsBoolean());
        }
        else {
            drivetrain.drive(0, 0, 0, fieldCentricSupplier.getAsBoolean());
        }


        SmartDashboard.putNumber("nav rotation x", drivetrain.getPosition().getX());
        SmartDashboard.putNumber("nav rotation y", drivetrain.getPosition().getY());
        SmartDashboard.putNumber("nav rotation angle", spins.getangle());
        
                
        if(vectorMagnitude <= distanceMargin) {
            end(true);
        }


    }

    @Override
    public boolean isFinished() {
        double vectorX = destinationX - drivetrain.getPosition().getX();
        double vectorY = destinationY - drivetrain.getPosition().getX();

        double vectorMagnitude = Math.sqrt(Math.pow(vectorX, 2) + Math.pow(vectorY, 2));

        if(vectorMagnitude <= distanceMargin) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

}


