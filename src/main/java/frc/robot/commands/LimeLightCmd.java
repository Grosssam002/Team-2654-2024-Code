package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightSub;

public class LimeLightCmd extends Command{
    private final LimelightSub m_LimelightSub;
    public LimeLightCmd(LimelightSub c_LimelightSub) {
        this.m_LimelightSub = c_LimelightSub;
    addRequirements(m_LimelightSub);
}
@Override
  public void execute() {
    m_LimelightSub.limelight(); 
}
}