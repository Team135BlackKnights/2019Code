package frc.robot.commands.Sensors;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class resetEncoderEndgame extends InstantCommand {
  // this command when ran will reset the encoder on the endgame manipulator
  public resetEncoderEndgame() {
    requires(Robot.endgame);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.endgame.resetEncoder();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
