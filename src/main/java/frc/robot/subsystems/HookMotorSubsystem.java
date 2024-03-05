package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HookMotorSubsystem extends SubsystemBase{
    private CANSparkMax motor1 = new CANSparkMax(28,MotorType.kBrushless);
    private CANSparkMax motor2 = new CANSparkMax(29,MotorType.kBrushless);
    private RelativeEncoder c_up = motor1.getEncoder();
    private RelativeEncoder m_up = motor2.getEncoder();
public void run(double speed){
motor1.set(speed);
motor2.set(speed);
}
public void setzero() { 
        m_up.setPosition(0);
        c_up.setPosition(0);
    }
    public void getPos() {
      SmartDashboard.putNumber("Hook1 Degrees", c_up.getPosition());
      SmartDashboard.putNumber("Hook2 Degrees", m_up.getPosition());
      
    
}
public void run1(double speed) {
    motor1.set(speed);
}
public void run2(double speed) {
    motor2.set(speed);
}
}