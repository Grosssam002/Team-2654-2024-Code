// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AprilTagCmd;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.Autos;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ExampleMotorSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.LEDS;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.MotorCommand;
import frc.robot.commands.ShooterCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.Pigeon;
import frc.robot.commands.LimeLightCmd;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.LEDScmd;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.commands.ManuelArmCmd;
import frc.robot.commands.ManuelArmCommand2;

public class RobotContainer {
  // Subsystem defined here
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleMotorSubsystem m_ExampleMotor = new ExampleMotorSubsystem();
  private final ShooterSubsystem m_ShooterSubsystem = new ShooterSubsystem();
  private final ArmSubsystem m_ArmSubsystem = new ArmSubsystem();
  private final LimelightSub m_LimelightSub = new LimelightSub();
  private final DrivetrainSubsystem drivetrain;
  private final Pigeon m_Pigeon = new Pigeon();
  private final LEDS m_Leds = new LEDS();
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
    m_ExampleMotor.setDefaultCommand(new MotorCommand(m_ExampleMotor, 0,false));
    m_ShooterSubsystem.setDefaultCommand(new ShooterCommand(m_ShooterSubsystem, 0));
    //m_ArmSubsystem.setDefaultCommand(new ArmCommand(m_ArmSubsystem,m_XboxController::getLeftTriggerAxis,0,0.125,false));
    m_LimelightSub.setDefaultCommand(new LimeLightCmd(m_LimelightSub));
    //m_ArmSubsystem.setDefaultCommand(new ArmCommand(m_ArmSubsystem, null, -60, 0.2, fieldCentric));
    m_ArmSubsystem.setDefaultCommand(new ManuelArmCommand2(m_ArmSubsystem, 0.2));
    m_Leds.setDefaultCommand(new LEDScmd(m_Leds));
    //Drive Commands
    driveJoystick = new Joystick(0);
    //steerJoystick = new Joystick(1);
    
    drivetrain = new DrivetrainSubsystem();

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

    c_driverController.button(1).whileTrue(new MotorCommand(m_ExampleMotor,0.35,true ));//1 Note Movement sensor
    c_driverController.button(2).whileTrue(new MotorCommand(m_ExampleMotor, 1,false));//2 Note movemnet no sensor
    c_driverController.button(3).whileTrue(new ShooterCommand(m_ShooterSubsystem,-0.75));//3 Launch into Speaker
    c_driverController.button(4).whileTrue(new ShooterCommand(m_ShooterSubsystem,-0.075));//4 Launch into Amp
    c_driverController.button(5).whileTrue(new MotorCommand(m_ExampleMotor,-0.5,false ));//5 Move Note Backwards
     c_driverController.button(6).whileTrue(new MotorCommand(m_ExampleMotor,-0.3,false ));
    //Xbox Controller
    m_driverController.button(2).whileTrue(new ManuelArmCmd(m_ArmSubsystem, 0.35));
    m_driverController.button(1).whileTrue(new ManuelArmCmd(m_ArmSubsystem, -0.25));
    //m_driverController.button(3).whileTrue(new ArmCommand(m_ArmSubsystem, null,7, 0.3,false));//3 Arm to Amp Launch Position
    //m_driverController.button(1).whileTrue(new ArmCommand(m_ArmSubsystem,null,-70.8, 0.2, false));//1 Arm to Floor
    //m_driverController.button(2).whileTrue(new ArmCommand(m_ArmSubsystem, null,-47, 0.3,false));//2 Arm to Speaker Launch Position
    
    m_driverController.button(4).whileTrue(new AprilTagCmd(m_ArmSubsystem, 0, 0.3, fieldCentric, m_LimelightSub));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
  
  }
 

