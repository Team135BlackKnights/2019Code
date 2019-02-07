package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class RunEndGame extends Command
{
	public RunEndGame()
	{
        requires(Robot.endgame);
	}
	protected void execute()
	{
		Robot.endgame.RunEndGame(.75);
	}
	@Override
	protected boolean isFinished() 
	{
		return false;
	}	
	protected void end()
	{
		Robot.endgame.RunEndGame(0);
	}
	protected void interrupted()
	{
		end();
	}
}
