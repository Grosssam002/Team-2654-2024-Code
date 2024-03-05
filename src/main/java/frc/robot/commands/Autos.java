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
import frc.robot.subsystems.Pigeon;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.IntakeCommand;

import java.util.function.BooleanSupplier;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }
  public static Command auto1(DrivetrainSubsystem drive, Pigeon spins, BooleanSupplier bSupplier) {
    return Commands.sequence(
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, -3, 3, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 4, -4, 0, 0.5, 0.1, 0.1, 0.1, spins)),
      //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 3, 3, 0, 0.5, 0.1, 0.1, 0.1, spins))
      new AutonomousSwerveDriveCommand(bSupplier, drive, .5, .5, 0, 0.5, 0.1, 0.1, 0.1, spins),
      new AutonomousSwerveDriveCommand(bSupplier, drive, -.5, 5, 0, 0.5, 0.1, 0.1, 0.1, spins),
      new AutonomousSwerveDriveCommand(bSupplier, drive, -.5, -.5, 0, 0.5, 0.1, 0.1, 0.1, spins),
      new AutonomousSwerveDriveCommand(bSupplier, drive, .5, -.5, 0, 0.5, 0.1, 0.1, 0.1, spins)
    
      );
    //new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1, 90, 1, 0.1, 0.1, 1, spins));
  }
  //Auto sequence which runs a series of commands
  public static Command auto2(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
    return Commands.sequence(
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 2, 2, 180, 0.5, 0.1, 0.1, 0.1, spins)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, -1, -1, 270, 0.5, 0.1, 0.1, 0.1, spins)),
      new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0, 0)),
      new ShooterCommand(shooter, 0.1, intake),
      new RepeatCommand(new AprilTagAutoCmd(limelight, drive, arm)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0))
      );
  }
  public static Command RedRightAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
    return Commands.sequence(
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 1.5, -90, 0.5, 0.5, 0.1, 0.1, spins)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1.5, -90, 0.5, 0.5, 0.1, 0.1, spins)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 1.5, 0, 0.5, 0.5, 0.1, 0.1, spins)),
      new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
      new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 1.5, -90, 0.5, 0.5, 0.1, 0.1, spins)),
      new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
      new WaitCommand(1.0),
      new RepeatCommand(new ShooterCommand(shooter, 0, intake))
    );
  }
  public static Command RedCenterAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
        return Commands.sequence(
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 180, 0.5, 0.5, 0.1, 0.1, spins)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 0, 0.5, 0.5, 0.1, 0.1, spins)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
    }
  public static Command RedLeftAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 135, 0.5, 0.5, 0.1, 0.1, spins)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 0, 0.5, 0.5, 0.1, 0.1, spins)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 135, 0.5, 0.5, 0.1, 0.1, spins)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
      }
  public static Command BlueRightAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, -1.5, 90, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, -1.5, 90, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, -1.5, 0, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, -1.5, 90, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new ArmPosCmd(arm, 0.35, 5.0)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.075, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
    );
  }
  public static Command BlueCenterAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
        return Commands.sequence(
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, -180, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 0, 0, 0, -0.5, -0.5, -0.1, -0.1, spins)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake))
        );
    }
  public static Command BlueLeftAuto(DrivetrainSubsystem drive,
      Pigeon spins,
      BooleanSupplier bSupplier,
      ShooterSubsystem shooter, IntakeSubsystem intake,
      ArmSubsystem arm,
      LimelightSub limelight) {
        return Commands.sequence(
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, -135, -0.5, -0.5, -0.1, -0.1, spins)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, -0.75, intake)),
          new WaitCommand(1.0),
          new RepeatCommand(new ShooterCommand(shooter, 0, intake)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, 0, -0.5, -0.5, -0.1, -0.1, spins)),
          new RepeatCommand(new noteCollectionCmd(limelight, shooter, arm, intake, drive, 0, 0.1, 0.1)),
          new RepeatCommand(new AutonomousSwerveDriveCommand(bSupplier, drive, 1, 0, -135, -0.5, -0.5, -0.1, -0.1, spins)),
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
