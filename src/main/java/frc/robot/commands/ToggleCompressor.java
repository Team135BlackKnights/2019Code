package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleCompressor extends Command
{
    private boolean _isOn;
	public ToggleCompressor(boolean isOn)
	{
        requires(Robot.intake);
        this._isOn = isOn;
	}
	protected void execute()
	{
        if(this._isOn == true)
        {
            Robot.intake.setCompressorOn();
        }
        else {
            Robot.intake.setCompressorOff();
        }
	}
	@Override
	protected boolean isFinished() 
	{
		return false;
	}	
	protected void end()
	{
	}
	protected void interrupted()
	{
		end();
	}
}
