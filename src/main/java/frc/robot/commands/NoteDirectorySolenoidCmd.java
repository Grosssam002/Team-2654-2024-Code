package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.NoteDirectorySolenoid;


public class NoteDirectorySolenoidCmd extends Command {
    private final NoteDirectorySolenoid m_Solenoid;
    private Boolean out = false;
    public NoteDirectorySolenoidCmd(
       NoteDirectorySolenoid m_Solenoid,
       boolean out
       
    ){

        this.m_Solenoid = m_Solenoid;
        this.out = out;
        addRequirements(m_Solenoid);
}

@Override
public void execute() {
    m_Solenoid.setSolenoid(out);
    SmartDashboard.putBoolean("solenoid", out);
    }
}