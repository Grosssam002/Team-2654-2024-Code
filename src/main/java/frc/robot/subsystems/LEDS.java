package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDS extends SubsystemBase{
AddressableLED m_led = new AddressableLED(0);
AddressableLEDBuffer m_ledBuffer = new AddressableLEDBuffer(162);
   public void leds() {
        
        m_led.setLength(162);
        //m_led.setBitTiming(1, 0, 0, 0);

    // Reuse buffer
    // Default to a length of 60, start empty output
    // Length is expensive to set, so only set it once, then just update data
    
    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
        // Sets the specified LED to the RGB values for red
        m_ledBuffer.setRGB(i, 255, 0, 0);
     }
     
    //m_led.setLength(162);

    // Set the data
    m_led.setData(m_ledBuffer);
    m_led.start();
    m_led.setData(m_ledBuffer);
    //m_led.close(); 
    
}
 public void closeleds()
 {
    m_led.close(); 
}
}
