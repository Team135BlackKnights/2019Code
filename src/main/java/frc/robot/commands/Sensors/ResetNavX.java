package frc.robot.commands.Sensors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ResetNavX extends InstantCommand {

  public ResetNavX() {
    requires(Robot.navx);
  }

  @Override
  protected void initialize() {
    Robot.navx.reset();
  }
}
