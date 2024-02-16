package frc.robot.subsystems;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;


//ratio is 222

public class ArmSubsystem extends SubsystemBase {
    private CANSparkMax motor1 = new CANSparkMax(26,MotorType.kBrushless);
    private CANSparkMax motor2 = new CANSparkMax(27,MotorType.kBrushless);
    private RelativeEncoder c_Rotation = motor1.getEncoder();
    private RelativeEncoder m_Rotation = motor2.getEncoder();
    
    
    
    public void setzero() { 
        m_Rotation.setPosition(0);
        c_Rotation.setPosition(0);
    }
    public double getPos() {
      SmartDashboard.putNumber("motor1 Degrees", c_Rotation.getPosition());
      return  c_Rotation.getPosition();
    
    
    }
    public void mbrake(){
        motor1.setIdleMode(IdleMode.kBrake);
        motor2.setIdleMode(IdleMode.kBrake);
    }
    public void stop(){
        motor1.set(0);
        motor2.set(0);
    }
    public void p2(double n){
        motor1.set(n);
        motor2.set(-n);
    }
    public void drive_to_pos(double desired_pos,double speed){  //desired pos should be 1.833 times the desired angle
        desired_pos = desired_pos /1.4976;//1.92
        SmartDashboard.putNumber("desired_pos", desired_pos);
        double current = getPos();
        double difference =  desired_pos-current;   
        SmartDashboard.putNumber("difference", difference);
        double truespeed = speed*difference*.5;
        SmartDashboard.putNumber("truespeed", truespeed);
        if (truespeed > speed){
            truespeed = speed;
        }
        if (truespeed < -speed){
            truespeed = -speed;
        }
        if (difference<0){
            motor1.set(truespeed);
            motor2.set(-truespeed);
        }
        else if (difference>0){
            motor1.set(truespeed);
            motor2.set(-truespeed);
        }
        else{
            motor1.set(0);
            motor2.set(0);
        }



    }

}
