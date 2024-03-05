package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class ArmPosCmd extends Command {
    private ArmSubsystem m_ArmSubsystem;
    private double speed;
    private double pos;

    public ArmPosCmd(
            ArmSubsystem c_ArmSubsystem,
            double speed,
            double pos
          ) {
        this.m_ArmSubsystem = c_ArmSubsystem;
        this.speed = speed;
        this.pos = pos;
        addRequirements(m_ArmSubsystem);
        
    }
    @Override
    public void execute() {
        m_ArmSubsystem.drive_to_pos(pos*1.4976, speed);
    }
    @Override
    public boolean isFinished() {
        m_ArmSubsystem.getPos();
        if(m_ArmSubsystem.getPos() - pos <= 0.5) {
            return true;
        }
        else {
            return false;
        }
    }
}
