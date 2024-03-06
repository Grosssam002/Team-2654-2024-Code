package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NoteDirectorySolenoid extends SubsystemBase{
    private DigitalOutput Solenoid = new DigitalOutput(6);
    public void setSolenoid(Boolean out) {
    Solenoid.set(out);
}
}
