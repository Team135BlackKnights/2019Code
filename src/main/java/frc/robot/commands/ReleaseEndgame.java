package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseEndgame extends Command
{
    private boolean _TF;
	public ReleaseEndgame(boolean TF)
	{
        this._TF = TF;
        setTimeout(RobotMap.Robot.Timeouts.INTAKE_TIMEOUT);
	}
	protected void execute()
	{
		Robot.endgame.movePiston(_TF);
	}
	@Override
	protected boolean isFinished() 
	{
		return isTimedOut();
	}	
	protected void end()
	{
		Robot.endgame.movePiston(true);
	}
	protected void interrupted()
	{
		end();
	}
}
