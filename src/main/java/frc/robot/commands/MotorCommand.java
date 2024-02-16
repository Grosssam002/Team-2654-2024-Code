package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ExampleMotorSubsystem;
import frc.robot.subsystems.LEDS;

public class MotorCommand extends Command {
    private final ExampleMotorSubsystem m_ExampleMotor;
    private double speed1 = 0;
    private boolean sensorenabled = false;
//public ExampleCommand(ExampleSubsystem subsystem) {
    //m_subsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
    public MotorCommand(
        ExampleMotorSubsystem m_ExampleMotor,
        double speed1,
        boolean sensorenabled
    )
     {
        this.speed1 = speed1;
        this.sensorenabled = sensorenabled;
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(m_ExampleSensor);
        this.m_ExampleMotor = m_ExampleMotor;
        //this.speed = speed;
        addRequirements(m_ExampleMotor);
    }
    
    

@Override
public void execute() {
    m_ExampleMotor.getDiscSensor();
    if (sensorenabled == true) {
        if (m_ExampleMotor.getDiscSensor() == false) {
            m_ExampleMotor.setmotor1(0.0);
           
        }
        else{
            m_ExampleMotor.setmotor1(speed1);
        }
    
    }
    else {
    m_ExampleMotor.setmotor1(speed1);
    }
    SmartDashboard.putBoolean("motor", true);
    }
}


