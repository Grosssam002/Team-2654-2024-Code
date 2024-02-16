package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.LimelightSub;

public class AprilTagCmd extends Command {
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
  
    
@Override
public void initialize(){
    if(reset == true){
        m_ArmSubsystem.setzero();
        }
 } 


@Override
  public void execute() {
    SmartDashboard.putNumber("truepos", m_ArmSubsystem.getPos());
    m_ArmSubsystem.drive_to_pos(pos, speed);
   
    double[] SpeakerAprilTag = m_LimelightSub.limelight();
    //double x = SpeakerAprilTag[0];
    double y = SpeakerAprilTag[1];
    double Area = SpeakerAprilTag[2];
    double Tid = SpeakerAprilTag[3];
    if(Tid == 8){
        //if(x == 3.825845){m_ArmSubsystem.drive_to_pos(-65,0.3);}
        //else if(x < 10){m_ArmSubsystem.drive_to_pos(-65,0.3);}
        //else if(x > -10){m_ArmSubsystem.drive_to_pos(-65,0.3);}
        if(y == 19.698210){m_ArmSubsystem.drive_to_pos(-65,0.3);}
        else if (y > 20.06 && y < 18){m_ArmSubsystem.drive_to_pos(-65,0.3);}
        else{{double current_pos = m_ArmSubsystem.getPos();
    if (current_pos < 5 && speed>0){
        m_ArmSubsystem.p2(speed);
        
    } else if(current_pos > -46 && speed<0){
    m_ArmSubsystem.p2(speed);
    }
    else{m_ArmSubsystem.p2(0);}}
   
    }   
   

}
}
}