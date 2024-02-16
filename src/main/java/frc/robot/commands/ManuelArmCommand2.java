package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ManuelArmCommand2 extends Command{
    private final ArmSubsystem m_ArmSubsystem;
    private final double speed;

    public ManuelArmCommand2(
    ArmSubsystem c_ArmSubsystem,
    double speed
    
    ){
        this.speed = speed;
        this.m_ArmSubsystem = c_ArmSubsystem;
        addRequirements(c_ArmSubsystem);
    }

@Override
public void initialize(){
 
 SmartDashboard.putNumber("Manuel Motor",m_ArmSubsystem.getPos());
}
@Override
  public void execute() {
    m_ArmSubsystem.drive_to_pos(SmartDashboard.getNumber("Manuel Motor",-60)*1.4976, speed);
}

}

