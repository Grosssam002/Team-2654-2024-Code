// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.IntakeCommand;
import frc.robot.subsystems.Limelight2Sub;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }
  public static Command auto1(DrivetrainSubsystem drive,  BooleanSupplier bSupplier) {
    return Commands.sequence(
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, -3, 3, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 4, -4, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 3, 3, 0, 0.5, 0.1, 0.1, 0.1, spins))
      new AutonomousSwerveDriveCommand(bSupplier, drive, 15, 0, 0, 0.0, 0.05, 0.01, 5, false),
      new AutonomousSwerveDriveCommand(bSupplier, drive, -1, -1, 0, 0.1, 0.1, 0.01, 5, true)
      //new AutonomousSwerveDriveCommand(bSupplier, drive, -.5, 5, 0, 0.5, 0.1, 0.1, 0.1, spins),
      //new AutonomousSwerveDriveCommand(bSupplier, drive, -.5, -.5, 0, 0.5, 0.1, 0.1, 0.1, spins),
      //new AutonomousSwerveDriveCommand(bSupplier, drive, .5, -.5, 0, 0.5, 0.1, 0.1, 0.1, spins)

    
      );
    //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1, 90, 1, 0.1, 0.1, 1, spins));
  }
  //Auto sequence which runs a series of commands
  public static Command auto2(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
    return Commands.sequence(
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 2, 2, 180, 0.1, 0.1, 0.1, 0.1, false)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, -1, -1, 270, 0.5, 0.1, 0.1, 0.1, false)),
      new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0, 0)),
      new ShooterCommand(shooter, 0.1, intake),
      new RepeatCommand(new AprilTagAutoCmd(limelight, drive, arm)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0))
      );
  }
  public static Command BackUpAuto(DrivetrainSubsystem drive,
  BooleanSupplier bSupplier,
  ShooterSubsystem shooter, IntakeSubsystem intake,
  ArmSubsystem arm,
  Limelight2Sub limelight) {
  DoubleSupplier returnX = () -> 0.0;
  DoubleSupplier returnY = () -> 0.05;
  DoubleSupplier returnR = () -> 0.0;
  DoubleSupplier stop = () -> 0.0;
  return Commands.sequence(
    new DriveCommand(returnX, returnY, returnR, bSupplier, drive));
  }
  public static Command RedRightAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
    return Commands.sequence(
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 1.5, -90, 0.5, 0.5, 0.1, 0.1, false)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1.5, -90, 0.5, 0.5, 0.1, 0.1, false)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 1.5, 0, 0.5, 0.5, 0.1, 0.1, false)),
      new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1.5, -90, 0.5, 0.5, 0.1, 0.1,false)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, 0, intake))
    );
  }
  public static Command RedCenterAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
        return Commands.sequence(
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 180, 0.5, 0.5, 0.1, 0.1, false)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 0, 0.5, 0.5, 0.1, 0.1, false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
    }
  public static Command RedLeftAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 135, 0.5, 0.5, 0.1, 0.1, false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 0, 0.5, 0.5, 0.1, 0.1, false)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 135, 0.5, 0.5, 0.1, 0.1,false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
      }
  public static Command BlueRightAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, -1.5, 90, -0.5, -0.5, -0.1, -0.1,false)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, -1.5, 90, -0.5, -0.5, -0.1, -0.1, false)),
          new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, -1.5, 0, -0.5, -0.5, -0.1, -0.1, false)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, -1.5, 90, -0.5, -0.5, -0.1, -0.1, false)),
          new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
    );
  }
  public static Command BlueCenterAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
        return Commands.sequence(
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, -180, -0.5, -0.5, -0.1, -0.1, false)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 0, -0.5, -0.5, -0.1, -0.1, false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
    }
  public static Command BlueLeftAuto(DrivetrainSubsystem drive,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      Limelight2Sub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, -135, -0.5, -0.5, -0.1, -0.1, false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 0, -0.5, -0.5, -0.1, -0.1,false)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, -135, -0.5, -0.5, -0.1, -0.1,false)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
      }
  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
