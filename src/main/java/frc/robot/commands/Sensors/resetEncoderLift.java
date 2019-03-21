package frc.robot.commands.Sensors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class resetEncoderLift extends InstantCommand {
  public resetEncoderLift() {}
  @Override
  protected void initialize() {
  }
  @Override
  protected void execute() 
  {
    Robot.lift.resetEncoders();
  }

  @Override
  protected boolean isFinished() {return false;}

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
