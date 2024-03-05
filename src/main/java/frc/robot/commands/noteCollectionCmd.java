package frc.robot.commands;

import javax.crypto.ExemptionMechanismException;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ArmSubsystem;

public class noteCollectionCmd extends Command {
    
    private final LimelightSub m_LimelightSub;
    private final ShooterSubsystem m_ShooterSubsystem;
    private final ArmSubsystem m_ArmSubsystem;
    private final IntakeSubsystem m_IntakeMotor;
    private final DrivetrainSubsystem m_DrivetrainSubsystem;
    private double m_drivespeed;
    private double m_intakespeed;
    private double m_armspeed;
    
    public noteCollectionCmd(LimelightSub c_LimelightSub, 
    ShooterSubsystem c_ShooterSubsystem, 
    ArmSubsystem c_ArmSubsystem, 
    IntakeSubsystem c_IntakeSubsystem, 
    DrivetrainSubsystem c_DrivetrainSubsystem,
    double c_drivespeed,
    double c_intakespeed,
    double c_armspeed) {
        this.m_LimelightSub = c_LimelightSub;
        this.m_ShooterSubsystem = c_ShooterSubsystem;
        this.m_ArmSubsystem = c_ArmSubsystem;
        this.m_IntakeMotor = c_IntakeSubsystem;
        this.m_DrivetrainSubsystem = c_DrivetrainSubsystem;
        this.m_drivespeed = c_drivespeed;
        this.m_intakespeed = c_intakespeed;
        this.m_armspeed = c_armspeed;
    }
    @Override
    public void initialize() {
        m_LimelightSub.switchPipeline(1);
    }
    @Override
    public void execute() {
    m_ArmSubsystem.drive_to_pos(0.0, 0.25);
    double[] SpeakerAprilTag = m_LimelightSub.limelight();
    double x = SpeakerAprilTag[0];
    double y = SpeakerAprilTag[1];
        if(x <= -1) {
            m_DrivetrainSubsystem.drive(0, 0, 0.1, false);
        }
        else if(x >= 1) {
            m_DrivetrainSubsystem.drive(0, 0, -0.1, false);
        }
        else {
            m_IntakeMotor.setmotor1(0.35);
            m_DrivetrainSubsystem.drive(0, 0.1, 0, false);
        }
    }
    @Override
    public boolean isFinished() {
        if(SmartDashboard.getBoolean("a1", false)) {
            return false;
        }
        else {
            m_DrivetrainSubsystem.drive(0, 0, 0, false);
            m_IntakeMotor.setmotor1(0.0);
            return true;
        }
    }
}
