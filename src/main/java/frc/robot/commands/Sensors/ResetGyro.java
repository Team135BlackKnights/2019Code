package frc.robot.commands.Sensors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetGyro extends InstantCommand {

  public ResetGyro() {
    requires(Robot.pigeon);
    requires(Robot.navx);
  }

  @Override
  protected void initialize() {
    Robot.pigeon.reset();
    Robot.navx.reset();
  }
}
