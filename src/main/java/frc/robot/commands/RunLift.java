package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunLift extends Command
{
	public RunLift()
	{
		requires(Robot.lift);
	}
	protected void execute()
	{
		SmartDashboard.putString("Command Running", "Run lift");
		Robot.lift.RunLift(Robot.oi.GetJoystickYValue(RobotMap.OI.MANIP_JOYSTICK));
	}
	@Override
	protected boolean isFinished() 
	{
		return false;
	}	
	protected void end()
	{
		Robot.lift.RunLift(0);
	}
	protected void interrupted()
	{
		end();
	}
}
