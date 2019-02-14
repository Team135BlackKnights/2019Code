package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakeWheels extends Command
{
	private double power;
	private boolean isBallinIntake;


	public RunIntakeWheels(double power)
	{
			this.power = power;
		//	isBallinIntake = Robot.ultra.isBallinIntake();

	}
	
	protected void execute()
	{
		Robot.intake.RunIntake(this.power);
	}
	@Override
	protected boolean isFinished() 
	{
		return isBallinIntake;
	}	
	protected void end()
	{
		Robot.intake.RunIntake(0);
	}
	protected void interrupted()
	{
		end();
	}
}
