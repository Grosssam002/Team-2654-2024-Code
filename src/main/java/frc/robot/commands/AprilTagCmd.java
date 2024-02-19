package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.LimelightSub;

public class AprilTagCmd extends Command {
    //declare variables in this. format and through subsystem here.
    private final ArmSubsystem m_ArmSubsystem;
    private final double pos;
    private final double speed;
    private final boolean reset;
    private final LimelightSub m_LimelightSub;

    public AprilTagCmd(ArmSubsystem c_Armsub,
    double pos,
    double speed,
    boolean reset,
    LimelightSub c_LimelightSub) {

    this.m_ArmSubsystem = c_Armsub;
    this.pos = pos;
    this.speed = speed;
    this.reset = reset;   
    this.m_LimelightSub = c_LimelightSub;
    addRequirements(m_ArmSubsystem,m_LimelightSub);
    }
  
   // set zero when robot starts 
@Override
public void initialize(){
    if(reset == true){
        m_ArmSubsystem.setzero();
        }
 } 

//april tag progress, goes to arm angle when it sees tags on a button command
@Override
  public void execute() {
    SmartDashboard.putNumber("truepos", m_ArmSubsystem.getPos());
    m_ArmSubsystem.drive_to_pos(pos, speed);
   
    double[] SpeakerAprilTag = m_LimelightSub.limelight();
    //double x = SpeakerAprilTag[0];
    double y = SpeakerAprilTag[1];
    double Area = SpeakerAprilTag[2];
    double Tid = SpeakerAprilTag[3];
    //Working auto aim, the negative number is the angle, the more negative the umber the lower it is to the ground, works to about 6-8 feet from front of speaker
    //about 75% accuracy. The Y variable is the distance from the tag do not change those.
    if(Tid == 8){
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
    else{ 
        //double current_pos = m_ArmSubsystem.getPos();
        //if (current_pos < 5 && speed>0){
        //    m_ArmSubsystem.p2(speed);
        //    
        //} else if(current_pos > -46 && speed<0){
        //m_ArmSubsystem.p2(speed);
        //}
        //else {m_ArmSubsystem.drive_to_pos(SmartDashboard.getNumber("Manuel Motor",-60)*1.4976, speed);}
        m_ArmSubsystem.drive_to_pos(SmartDashboard.getNumber("Manuel Motor",-60)*1.4976, speed);
  }
  }
}