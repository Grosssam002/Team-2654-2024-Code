package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;
import java.util.function.DoubleSupplier;

public class ManuelShooterCmd extends Command {
    private final ShooterSubsystem m_ShooterSubsystem;
    private DoubleSupplier m_speed1;
    public ManuelShooterCmd(
        ShooterSubsystem m_ShooterSubsystem,
        DoubleSupplier m_speed1


    )
    {
        this.m_ShooterSubsystem = m_ShooterSubsystem;
        this.m_speed1 = m_speed1;
        addRequirements(m_ShooterSubsystem);
    }
    @Override
    public void execute() {
        m_ShooterSubsystem.run(m_speed1.getAsDouble());

    }
}

