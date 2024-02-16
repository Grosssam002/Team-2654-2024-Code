package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.Pigeon2;

public class Pigeon extends SubsystemBase{
    Pigeon2 m_Pigeon = new Pigeon2(9, "rio");

public double getangle(){
    return m_Pigeon.getAngle();
    

}

}

