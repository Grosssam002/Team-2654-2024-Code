package frc.robot.commands;

import frc.robot.commands.AprilTagCmd;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSub;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Drivetrain;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.Limelight2Sub;

public class AprilTagAutoCmd extends Command {
    private final Limelight2Sub m_Limelight2Sub;
    private final DrivetrainSubsystem m_DrivetrainSubsystem;
    private final ArmSubsystem m_ArmSubsystem;

    public AprilTagAutoCmd(Limelight2Sub c_Limelight2Sub, DrivetrainSubsystem c_DrivetrainSubsystem, ArmSubsystem c_ArmSubsystem){
        this.m_DrivetrainSubsystem = c_DrivetrainSubsystem;
        this.m_Limelight2Sub = c_Limelight2Sub;
        this.m_ArmSubsystem = c_ArmSubsystem;
    }
    @Override
    public void execute() {
        double[] AprilTagData = m_Limelight2Sub.limelight();
        double x = AprilTagData[0];
        double y = AprilTagData[1];
        double Tid = AprilTagData[3];
        if(Tid == 4 || Tid == 7) {
            if (x < -0.1) {
                m_DrivetrainSubsystem.drive(0, 0, 0.1, isFinished());
            }
            else if (x > 0.1) {
                m_DrivetrainSubsystem.drive(0, 0, -0.1, isFinished());

            }
            else {
                m_DrivetrainSubsystem.drive(0, 0, 0, isFinished());
                SmartDashboard.putNumber("Manuel Motor",m_ArmSubsystem.getPos());
                if(y >= 20){m_ArmSubsystem.drive_to_pos(-65,0.3);}
                else if((y<20) && (y>=19)){m_ArmSubsystem.drive_to_pos(-64, 0.3);}
                else if((y<19) && (y>=18)){m_ArmSubsystem.drive_to_pos(-63, 0.3);}
                else if((y<18) && (y>=17)){m_ArmSubsystem.drive_to_pos(-62, 0.3);}
                else if((y<17) && (y>=16)){m_ArmSubsystem.drive_to_pos(-61, 0.3);}
                else if((y<16) && (y>=15)){m_ArmSubsystem.drive_to_pos(-60, 0.3);}
                else if((y<15) && (y>=14)){m_ArmSubsystem.drive_to_pos(-59, 0.3);}
                else if((y<14) && (y>=13)){m_ArmSubsystem.drive_to_pos(-58, 0.3);}
                else if((y<13) && (y>=12)){m_ArmSubsystem.drive_to_pos(-57, 0.3);}
                else if((y<12) && (y>=11)){m_ArmSubsystem.drive_to_pos(-56.2, 0.3);}
                else if((y<11) && (y>=10)){m_ArmSubsystem.drive_to_pos(-55.5, 0.3);}
                else if((y<10) && (y>=9)){m_ArmSubsystem.drive_to_pos(-55.3, 0.3);}
                else if((y<9) && (y>=8)){m_ArmSubsystem.drive_to_pos(-54.9, 0.3);}
                else if((y<8) && (y>=7)){m_ArmSubsystem.drive_to_pos(-54, 0.3);}
                else if((y<7) && (y>=6)){m_ArmSubsystem.drive_to_pos(-53.3, 0.3);}
                else if((y<6) && (y>=5)){m_ArmSubsystem.drive_to_pos(-53.3, 0.3);}
                else if((y<5) && (y>=4)){m_ArmSubsystem.drive_to_pos(-53.1, 0.3);}
                else if((y<4) && (y>=3)){m_ArmSubsystem.drive_to_pos(-52.6, 0.3);}
                else if((y<3) && (y>=2)){m_ArmSubsystem.drive_to_pos(-51.8, 0.3);}
                else if((y<2) && (y>=1)){m_ArmSubsystem.drive_to_pos(-50.7, 0.3);}
                else if((y<1) && (y>=0)){m_ArmSubsystem.drive_to_pos(-49, 0.3);}
                else {m_ArmSubsystem.drive_to_pos(-47.8, 0.3);}
            }
        }
    }

    @Override
    public void initialize() {
        
    }
}
