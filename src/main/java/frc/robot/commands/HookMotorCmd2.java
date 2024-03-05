package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HookMotorSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class HookMotorCmd2 extends Command{
    private final HookMotorSubsystem m_HookMotorSubsystem;
    private final double speed;

    public HookMotorCmd2(
    HookMotorSubsystem c_HookMotorSubsystem,
    double speed
    
    ){
        this.speed = speed;
        this.m_HookMotorSubsystem = c_HookMotorSubsystem;
        addRequirements(c_HookMotorSubsystem);
    }

@Override
public void initialize(){
 
 SmartDashboard.putNumber("Hook Motor1",0);
}
@Override
  public void execute() {
    m_HookMotorSubsystem.run(SmartDashboard.getNumber("Hook Motor1",0));
}

}



