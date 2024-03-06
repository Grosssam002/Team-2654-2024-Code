package frc.robot.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DriverStation;

public class LEDS extends SubsystemBase{
AddressableLED m_led = new AddressableLED(0);
AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(165);
   public void init_leds(){
    m_led.setLength(165);
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
    // Sets the specified LED to the RGB values for red
    m_ledBuffer.setRGB(i, 255, 0, 0);
     }
    m_led.setData(m_ledBuffer);
    m_led.start();
   }
   public void leds() {
        
    
        //m_led.setBitTiming(1, 0, 0, 0);

    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    Optional<Alliance> ally = DriverStation.getAlliance();
    if (SmartDashboard.getBoolean("a1", false)) {
        if (ally.isPresent()){
            if(ally.get() == Alliance.Red){
                for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                // Sets the specified LED to the RGB values for red
                m_ledBuffer.setRGB(i, 255, 0, 0);
                }
            }
            else if (ally.get() == Alliance.Blue) {
                for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                // Sets the specified LED to the RGB values for red
                m_ledBuffer.setRGB(i, 0, 0, 255);
                }
            }
            else {
                for (var i = 0; i < m_ledBuffer.getLength(); i++) {
                // Sets the specified LED to the RGB values for red
                m_ledBuffer.setRGB(i, 255, 0, 255);
                }

            }    
        }
    }
    else{ 
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        // Sets the specified LED to the RGB values for red
        m_ledBuffer.setRGB(i, 191, 255, 0);
        }
    }
     
    //m_led.setLength(162);

    // Set the data
    m_led.setData(m_ledBuffer);
    
    //m_led.setData(m_ledBuffer);
    //m_led.close(); 
    
}
// public void closeleds()
// {
//    m_led.close(); 
//}
   }
