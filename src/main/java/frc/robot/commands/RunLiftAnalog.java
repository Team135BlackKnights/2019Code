package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunLiftAnalog extends Command
{
	public RunLiftAnalog()
	{
		requires(Robot.lift);
	}
	protected void execute()
	{
		SmartDashboard.putBoolean("Is Lift Running", true);
		Robot.lift.RunLift(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK));
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
