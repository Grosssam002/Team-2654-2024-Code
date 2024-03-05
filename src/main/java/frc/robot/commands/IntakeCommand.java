package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LEDS;

public class IntakeCommand extends Command {
    private final IntakeSubsystem m_IntakeMotor;
    private double speed1 = 0;
    private boolean sensorenabled = false;
//public ExampleCommand(ExampleSubsystem subsystem) {
    //m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
    public IntakeCommand(
        IntakeSubsystem m_IntakeMotor,
        double speed1,
        boolean sensorenabled
    )
     {
        this.speed1 = speed1;
        this.sensorenabled = sensorenabled;
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(m_ExampleSensor);
        this.m_IntakeMotor = m_IntakeMotor;
        //this.speed = speed;
        addRequirements(m_IntakeMotor);
    }
    
    

@Override
public void execute() {
    m_IntakeMotor.getDiscSensor();
    if (sensorenabled == true) {
        if (m_IntakeMotor.getDiscSensor() == false) {
            m_IntakeMotor.setmotor1(0.0);
           
        }
        else{
            m_IntakeMotor.setmotor1(speed1);
        }
    
    }
    else {
    m_IntakeMotor.setmotor1(speed1);
    }
    SmartDashboard.putBoolean("motor", true);
    }
}


