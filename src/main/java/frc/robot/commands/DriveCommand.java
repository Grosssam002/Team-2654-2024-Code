// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Pigeon;
import edu.wpi.first.math.MathUtil;
import java.lang.Math;


public class DriveCommand extends Command {
    protected final DrivetrainSubsystem drivetrain;

    private final DoubleSupplier xSupplier;
    private final DoubleSupplier ySupplier;
    private final DoubleSupplier rotationSupplier;
    private final BooleanSupplier fieldCentricSupplier;
    
    private final SlewRateLimiter xLimiter;
    private final SlewRateLimiter yLimiter;
    private final SlewRateLimiter rotationLimiter;
    private final Pigeon spins;

    


    /**
     * @param xSupplier supplier for forward velocity.
     * @param ySupplier supplier for sideways velocity.
     * @param rotationSupplier supplier for angular velocity.
     */
    public DriveCommand(
        DoubleSupplier xSupplier, 
        DoubleSupplier ySupplier, 
        DoubleSupplier rotationSupplier,
        BooleanSupplier fieldCentricSupplier,
        DrivetrainSubsystem drivetrain,
        Pigeon spins
    ) {
        this.drivetrain = drivetrain;
        this.spins = spins;

        this.xSupplier = xSupplier;
        this.ySupplier = ySupplier;
        this.rotationSupplier = rotationSupplier;
        this.fieldCentricSupplier = fieldCentricSupplier;


        xLimiter = new SlewRateLimiter(2);
        yLimiter = new SlewRateLimiter(2);
        rotationLimiter = new SlewRateLimiter(5);

        addRequirements(drivetrain, spins);
    }
    protected double getRotateX(double jx,double jy,double pa) {
        double ja;
        double nx = 0;
        double ny;
        ja = pa * (Math.PI/180);
        nx = jx * Math.cos(ja) - jy * Math.sin(ja);
        SmartDashboard.putNumber("NX", nx);
        return nx;
    }
    protected double getRotateY(double jx,double jy,double pa){
        double ja;
        double nx;
        double ny = 0;
        ja = pa * (Math.PI/180);
        ny = jx * Math.sin(ja) + jy * Math.cos(ja);
        SmartDashboard.putNumber("NY",ny);
        return ny;
    }

    protected double getX() {
        //return slewAxis(xLimiter, deadBand(-xSupplier.getAsDouble()));
        return slewAxis(xLimiter, deadBand(getRotateX(-xSupplier.getAsDouble(),-ySupplier.getAsDouble(),spins.getangle())));
    }

    protected double getY() {
       //return slewAxis(yLimiter, deadBand(-ySupplier.getAsDouble()));
        return slewAxis(yLimiter, deadBand(getRotateY(-xSupplier.getAsDouble(),-ySupplier.getAsDouble(),spins.getangle())));
    }

    protected double getRotation() {
        return slewAxis(rotationLimiter, deadBand(-rotationSupplier.getAsDouble()));
    }

    @Override
    public void execute() {
        drivetrain.drive(getX(), -getY(), -getRotation(), fieldCentricSupplier.getAsBoolean());
        
        SmartDashboard.putNumber("nav rotation x", drivetrain.getPosition().getX());
        SmartDashboard.putNumber("nav rotation y", drivetrain.getPosition().getY());
        SmartDashboard.putNumber("nav rotation angle", spins.getangle());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }

    protected double slewAxis(SlewRateLimiter limiter, double value) {
        return limiter.calculate(Math.copySign(Math.pow(value, 2), value));
    }

    protected double deadBand(double value) {
        if (Math.abs(value) <= 0.075) {
            return 0.0;
        }
        // Limit the value to always be in the range of [-1.0, 1.0]
        return Math.copySign(Math.min(1.0, Math.abs(value)), value);
    }
}
