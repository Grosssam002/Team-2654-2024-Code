// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AprilTagCmd;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.HookMotorSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDS;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.NoteDirectorySolenoidCmd;
import frc.robot.commands.ShooterCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.NoteDirectorySolenoid;
import frc.robot.subsystems.Pigeon;
import frc.robot.commands.LimeLightCmd;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.HookMotorSubsystem;

import java.util.function.BooleanSupplier;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.HookMotorCmd;
import frc.robot.commands.LEDScmd;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.ManuelArmCmd;
import frc.robot.commands.ManuelArmCommand2;
import frc.robot.commands.ManuelShooterCmd;

public class RobotContainer {
  // Subsystem defined here
  private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem m_IntakeMotor = new IntakeSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();
  private final LimelightSub m_LimelightSub = new LimelightSub();
  private final Pigeon m_Pigeon = new Pigeon();
  private final LEDS m_Leds = new LEDS();
  private final HookMotorSubsystem m_HookMotorSubsystem = new HookMotorSubsystem();
  private final NoteDirectorySolenoid m_Solenoid = new NoteDirectorySolenoid();
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();
      private final Joystick driveJoystick;
      //private final Joystick steerJoystick;
      private boolean fieldCentric;
//Controller Call
  private final CommandJoystick m_driverController =
      new CommandJoystick(OperatorConstants.kDriverControllerPort);
  private final CommandJoystick c_driverController = 
      new CommandJoystick(OperatorConstants.mDriverControllerPort);
    private final XboxController m_XboxController = 
      new XboxController(0);


  
  //Default Commands
  public RobotContainer() {
    m_IntakeMotor.setDefaultCommand(new IntakeCommand(m_IntakeMotor, 0,false));
    m_ShooterSubsystem.setDefaultCommand(new ShooterCommand(m_ShooterSubsystem, 0,m_IntakeMotor));
    //m_ArmSubsystem.setDefaultCommand(new ArmCommand(m_ArmSubsystem,m_XboxController::getLeftTriggerAxis,0,0.125,false));
    m_LimelightSub.setDefaultCommand(new LimeLightCmd(m_LimelightSub));
    //m_ArmSubsystem.setDefaultCommand(new ArmCommand(m_ArmSubsystem, null, -60, 0.2, fieldCentric));
    m_ArmSubsystem.setDefaultCommand(new ManuelArmCommand2(m_ArmSubsystem, 0.2));
    m_Leds.setDefaultCommand(new LEDScmd(m_Leds));
    m_Solenoid.setDefaultCommand(new NoteDirectorySolenoidCmd(m_Solenoid, true));
    m_HookMotorSubsystem.setDefaultCommand(new HookMotorCmd(m_HookMotorSubsystem, 0));
    //Drive Commands
    driveJoystick = new Joystick(0);
    //steerJoystick = new Joystick(1);

    CameraServer.startAutomaticCapture();

    drivetrain.setDefaultCommand(
      new DriveCommand(
          // Forward velocity supplier.
          m_XboxController::getLeftY,
          //driveJoystick::getY,
          // Sideways velocity supplier.
          m_XboxController::getLeftX,
          //driveJoystick::getX,
          // Rotation velocity supplier.
          m_XboxController::getRightX,
          //driveJoystick::getZ,
          () -> fieldCentric,
          drivetrain,
          m_Pigeon
          
      )
    );
    configureBindings();
    configureAutochooser();
  }
    private void configureBindings() {
      //JoystickButton zeroGyroButton = new JoystickButton(driveJoystick, 0);
      //zeroGyroButton.onTrue(new InstantCommand(() -> drivetrain.zeroGyro(), drivetrain));
  
      JoystickButton driveModeToggleButton = new JoystickButton(driveJoystick, 5);
      ParallelCommandGroup driveModeToggleCommandGroup = new ParallelCommandGroup(
        new InstantCommand( () -> fieldCentric = !fieldCentric), 
        new InstantCommand( () -> {if(fieldCentric){System.out.println("FIELD CENTRIC");} else {System.out.println("ROBOT CENTRIC");}})
      );
      driveModeToggleButton.onTrue(driveModeToggleCommandGroup);
    
    //ButtonPad

    c_driverController.button(1).whileTrue(new IntakeCommand(m_IntakeMotor,0.35,true ));//1 Note Movement sensor
    c_driverController.button(2).whileTrue(new IntakeCommand(m_IntakeMotor,0.5,false ));//2 Note movemnet no sensor
    c_driverController.button(3).whileTrue(new ShooterCommand(m_ShooterSubsystem, 0.75, m_IntakeMotor));//3 Launch into Speaker
    c_driverController.button(4).whileTrue(new ShooterCommand(m_ShooterSubsystem,0.075, m_IntakeMotor));//4 Launch into Amp
    c_driverController.button(5).whileTrue(new IntakeCommand(m_IntakeMotor,-0.5,false ));//5 Move Note Backwards
    c_driverController.button(6).whileTrue(new IntakeCommand(m_IntakeMotor,-0.3,false ));
    c_driverController.povLeft().whileTrue(new NoteDirectorySolenoidCmd(m_Solenoid, false));
    c_driverController.povUp().whileTrue(new HookMotorCmd(m_HookMotorSubsystem, 0.30)); 
    c_driverController.povDown().whileTrue(new HookMotorCmd(m_HookMotorSubsystem, -0.30));
    {
    
    };
    //Xbox Controller
    m_driverController.button(2).whileTrue(new ManuelArmCmd(m_ArmSubsystem, -0.45));
    m_driverController.button(1).whileTrue(new ManuelArmCmd(m_ArmSubsystem, 0.35));
    m_driverController.button(4).whileTrue(new AprilTagCmd(m_ArmSubsystem, 0, 0.3, fieldCentric, m_LimelightSub));
    //m_driverController.button(3).whileTrue(new NoteDirectorySolenoidCmd(m_Solenoid, false));
    //m_driverController.button(4).whileTrue(new NoteDirectoryCmd(m_DirectoryMotorSub, -0.1));
  }

private void configureAutochooser() {
  Command DefaultAuto = Autos.exampleAuto(m_exampleSubsystem);
  Command autoCommand1 = Autos.auto1(drivetrain, m_Pigeon, () -> fieldCentric);
  Command autoCommand2 = Autos.auto2(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command RedRightAuto = Autos.RedRightAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command RedCenterAuto = Autos.RedCenterAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command RedLeftAuto = Autos.RedLeftAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command BlueRightAuto = Autos.BlueRightAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command BlueCenterAuto = Autos.BlueCenterAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);
  Command BlueLeftAuto = Autos.BlueLeftAuto(drivetrain, m_Pigeon, () -> fieldCentric, m_ShooterSubsystem, m_IntakeMotor, m_ArmSubsystem, m_LimelightSub);

  autoChooser.setDefaultOption("Default Auto Mode", DefaultAuto);
  autoChooser.addOption("Auto Mode 1", autoCommand1);
  autoChooser.addOption("Auto Mode 2", autoCommand2);
  autoChooser.addOption("RedR Auto Mode", RedRightAuto);
  autoChooser.addOption("RedC Auto Mode", RedCenterAuto);
  autoChooser.addOption("RedL Auto Mode", RedLeftAuto);
  autoChooser.addOption("BlueR Auto Mode", BlueRightAuto);
  autoChooser.addOption("BlueC Auto Mode", BlueCenterAuto);
  autoChooser.addOption("BlueL Auto Mode", BlueLeftAuto);

  SmartDashboard.putData("Auto Mode", autoChooser);
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //return Autos.exampleAuto(m_exampleSubsystem);
    return autoChooser.getSelected();
  }
  
  }