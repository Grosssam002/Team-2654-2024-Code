package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LimelightSub;
import frc.robot.subsystems.Limelight2Sub;

public class LimeLight2Cmd extends Command{
    private final Limelight2Sub m_Limelight2Sub;
    public LimeLight2Cmd(Limelight2Sub c_Limelight2Sub) {
        this.m_Limelight2Sub = c_Limelight2Sub;
    addRequirements(m_Limelight2Sub);
}
@Override
  public void execute() {
    m_Limelight2Sub.limelight();
}
}