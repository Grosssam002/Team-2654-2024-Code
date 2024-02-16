package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;

public class arm_set_zero_com extends Command {
    private final ArmSubsystem m_ArmSub;

    public arm_set_zero_com(
        ArmSubsystem c_Armsub
        
    )
     {
        // Use addRequirements() here to declare subsystem dependencies.
        //addRequirements(m_ExampleSensor);
        this.m_ArmSub = c_Armsub;
        addRequirements(m_ArmSub);
     }


@Override
  public void execute() {
    m_ArmSub.setzero();
}
}