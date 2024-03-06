package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HookMotorSubsystem;

public class HookMotorCmd extends Command{
    private final HookMotorSubsystem m_HookMotorSubsystem;
    private double m_speed1;
    public HookMotorCmd(
        HookMotorSubsystem c_HookMotorSubsystem,
        double c_speed1


    )
    {
        this.m_HookMotorSubsystem = c_HookMotorSubsystem;
        this.m_speed1 = c_speed1;
        addRequirements(m_HookMotorSubsystem);
    }
   
    
    @Override
    public void execute() {
    //m_HookMotorSubsystem.run(m_speed1);
    m_HookMotorSubsystem.getPos();
    double hook_pos1 = SmartDashboard.getNumber("Hook1 Degrees",0);
    if (hook_pos1 <=0 && hook_pos1 >= -482){
        m_HookMotorSubsystem.run1(m_speed1);
    }
    else if (hook_pos1 >=0 && hook_pos1 >= -482){
        if (m_speed1 < 0){
        m_HookMotorSubsystem.run1(m_speed1);
        }
        else{m_HookMotorSubsystem.run1(0);
        }
    }
    else if (hook_pos1 <=0 && hook_pos1 <= -482){
        if (m_speed1 > 0){
        m_HookMotorSubsystem.run1(m_speed1);
        }
      else{m_HookMotorSubsystem.run1(0);
        }
    }
    else{m_HookMotorSubsystem.run1(0);}
     double hook_pos2 = SmartDashboard.getNumber("Hook2 Degrees",0);
    if (hook_pos2 <=0 && hook_pos2 >= -482){
        m_HookMotorSubsystem.run2(m_speed1);
    }
    else if (hook_pos2 >=0 && hook_pos2 >= -482){
        if (m_speed1 < 0){
        m_HookMotorSubsystem.run2(m_speed1);
        }
        else{m_HookMotorSubsystem.run2(0);
        }
       }
       else if (hook_pos2 <=0 && hook_pos2 <= -482){
           if (m_speed1 > 0){
           m_HookMotorSubsystem.run2(m_speed1);
           }
         else{m_HookMotorSubsystem.run2(0);
           }
       }
       else{m_HookMotorSubsystem.run2(0);}


        
}
}