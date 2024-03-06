package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LEDS;

public class LEDScmd extends Command {
    private final LEDS m_Leds;
    public LEDScmd(
        LEDS m_Leds
    )
    {
        this.m_Leds = m_Leds;
        addRequirements(m_Leds);
    }
    @Override
    public void initialize() {
        m_Leds.init_leds();
}
    @Override
    public void execute(){
        m_Leds.leds();
    }
    @Override
    public void end(boolean interrupted){
       // m_Leds.closeleds();
    }
}
