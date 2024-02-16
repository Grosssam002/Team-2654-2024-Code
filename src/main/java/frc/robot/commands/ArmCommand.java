package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import java.util.function.DoubleSupplier;

public class ArmCommand extends Command {
    private final ArmSubsystem m_ArmSubsystem;
    private final double pos;
    private final double speed;
    private final boolean reset;
    private final DoubleSupplier m_PositionSupplier;

    public ArmCommand(
        ArmSubsystem c_Armsub,
        DoubleSupplier c_PostionSupplier,
        double pos,
        double speed,
        boolean reset
    )
     {
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(m_ExampleSensor);
        this.m_ArmSubsystem = c_Armsub;
        this.pos = pos;
        this.speed = speed;
        this.reset = reset;
        this.m_PositionSupplier = c_PostionSupplier;
        addRequirements(m_ArmSubsystem);
     }


@Override
public void initialize(){
  if(reset == true){
  m_ArmSubsystem.setzero();
  }
  
}
@Override
  public void execute() {
    SmartDashboard.putNumber("truepos", m_ArmSubsystem.getPos());
    
    if(m_PositionSupplier == null){m_ArmSubsystem.drive_to_pos(pos, speed);}
    else{m_ArmSubsystem.drive_to_pos(m_PositionSupplier.getAsDouble() *-78+10, speed);}
}

}
