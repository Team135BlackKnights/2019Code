package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakeWheels extends Command
{
	private double _power;
	Counter counter = new Counter(Robot.intake.vexButton);
	public RunIntakeWheels(double power)
	{
        this._power = power;
	}
	public boolean isSwitchSet() {
        return counter.get() > 0;
	}
	public void initializeCounter()
	{
		counter.reset();
	}
	protected void execute()
	{
		initializeCounter();
		Robot.intake.RunIntake(this._power);
	}
	@Override
	protected boolean isFinished() 
	{
		return isSwitchSet();
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
