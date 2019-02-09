package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleCompressor extends InstantCommand
{
    private boolean isCompressorOn = true;
	public ToggleCompressor()
	{
        requires(Robot.intake);
	}
	protected void execute()
	{
		isCompressorOn = !isCompressorOn;
        if(isCompressorOn)
        {
            Robot.intake.setCompressorOn();
        }
		else 
		{
            Robot.intake.setCompressorOff();
        }
	}
}
