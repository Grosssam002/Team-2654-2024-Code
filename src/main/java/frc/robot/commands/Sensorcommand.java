package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;



public class Sensorcommand extends Command {
    private final IntakeSubsystem m_ExampleSensor;
//public ExampleCommand(ExampleSubsystem subsystem) {
    //m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
    public Sensorcommand(
        IntakeSubsystem m_ExampleSensor
    )
     {
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(m_ExampleSensor);
        this.m_ExampleSensor = m_ExampleSensor;
        addRequirements(m_ExampleSensor);
    }

@Override
public void execute() {
    m_ExampleSensor.getDiscSensor();
    }
}

    

