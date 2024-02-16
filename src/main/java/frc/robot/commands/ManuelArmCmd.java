package frc.robot.commands;

import com.revrobotics.SparkMaxLimitSwitch.Direction;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ManuelArmCmd extends Command{
    private final ArmSubsystem m_ArmSubsystem;
    private final double speed;

    public ManuelArmCmd(
    ArmSubsystem c_ArmSubsystem,
    double speed
    
    ){
        this.speed = speed;
        this.m_ArmSubsystem = c_ArmSubsystem;
        addRequirements(c_ArmSubsystem);
    }
@Override
  public void execute() {
    double current_pos = m_ArmSubsystem.getPos();
    if (current_pos < 5 && speed>0){
        m_ArmSubsystem.p2(speed);
        
    } else if(current_pos > -46 && speed<0){
    m_ArmSubsystem.p2(speed);
    }
    else{m_ArmSubsystem.p2(0);}
}
}

