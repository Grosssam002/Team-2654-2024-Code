package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.Command;

public class HookMotorSubsystem extends Command{
    private CANSparkMax motor1 = new CANSparkMax(28,MotorType.kBrushless);
    private CANSparkMax motor2 = new CANSparkMax(29,MotorType.kBrushless);
public void run(double speed){
motor1.set(speed);
motor2.set(speed);
    
}
}