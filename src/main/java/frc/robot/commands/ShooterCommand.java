package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class ShooterCommand extends Command{
    private final ShooterSubsystem m_ShooterSubsystem;
    private IntakeSubsystem m_ExampleMotorSubsystem;
    private double m_speed1 = 0;
    public ShooterCommand(
        ShooterSubsystem c_ShooterSubsystem,
        double c_speed1,
        IntakeSubsystem c_ExampleMotorSubsystem


    )
    {
        this.m_ShooterSubsystem = c_ShooterSubsystem;
        this.m_ExampleMotorSubsystem = c_ExampleMotorSubsystem;
        this.m_speed1 = c_speed1;
        addRequirements(m_ShooterSubsystem);
    }
    
  
    @Override
    public void execute() {
        m_ShooterSubsystem.run(-m_speed1);
        
    }
}