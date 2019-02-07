package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntakeElbow extends Command
{
	public MoveIntakeElbow()
	{
		requires(Robot.intake);
        setTimeout(RobotMap.Robot.Timeouts.INTAKE_TIMEOUT);
	}
	protected void execute()
	{
		Robot.intake.RunElbow(.25 * Robot.oi.GetJoystickZValue(RobotMap.OI.MANIP_JOYSTICK));
	}
	@Override
	protected boolean isFinished() 
	{
		return isTimedOut();
	}	
	protected void end()
	{
		Robot.intake.RunElbow(0);
	}
	protected void interrupted()
	{
		end();
	}
}
