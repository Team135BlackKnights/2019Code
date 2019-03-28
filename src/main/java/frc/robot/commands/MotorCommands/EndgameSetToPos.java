package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EndgameSetToPos extends Command {
  boolean isDone;
  public double desiredPos;
  public double releasePos;

  public EndgameSetToPos() {
    requires(Robot.endgame);

  }

  @Override
  protected void initialize() {
  }

  protected void execute() {
  
  }

  @Override
  protected boolean isFinished() {
    return isDone;
  }

}
