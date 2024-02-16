package frc.robot.subsystems;



import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;




public class ExampleMotorSubsystem extends SubsystemBase {
private CANSparkMax motor1 = new CANSparkMax(24,MotorType.kBrushless);
public void setmotor1(Double speed) {
    motor1.set(speed);
}
    
    //private DigitalInput DiscSensor = new DigitalInput(5);
    //public void getDiscSensor() {
    //    //public void DiscSensor() {
    //    //smartDashboard().putBoolean("DiscSensor", DiscSensor.get());
    //SmartDashboard.putBoolean("a1",DiscSensor.get());
    //}

    public Command exampleMethodCommand() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exampleMethodCommand'");
    }
    public boolean exampleCondition() {
        // Query some boolean state, such as a digital sensor.
        return false;
    }
    

    
    private DigitalInput DiscSensor = new DigitalInput(5);
    public boolean getDiscSensor() {
       
        //public void DiscSensor() {
        //smartDashboard().putBoolean("DiscSensor", DiscSensor.get());
    SmartDashboard.putBoolean("a1",DiscSensor.get());
    return DiscSensor.get();
    }

}
