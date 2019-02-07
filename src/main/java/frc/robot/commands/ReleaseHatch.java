package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseHatch extends Command
{
    private boolean _TF;
	public ReleaseHatch(boolean TF)
	{
        this._TF = TF;
        setTimeout(RobotMap.Robot.Timeouts.INTAKE_TIMEOUT);
	}
	protected void execute()
	{
		Robot.intake.MoveSolenoid(this._TF);
	}
	@Override
	protected boolean isFinished() 
	{
		return isTimedOut();
	}	
	protected void end()
	{
		Robot.intake.MoveSolenoid(false);;
	}
	protected void interrupted()
	{
		end();
	}
}
