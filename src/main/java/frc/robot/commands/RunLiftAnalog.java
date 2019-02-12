package frc.robot.commands;

import frc.robot.*;
import frc.robot.RobotMap.KOI;
import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RunLiftAnalog extends InstantCommand
{
	public RunLiftAnalog()
	{
		requires(Robot.lift);
	}
	protected void execute()
	{
		Robot.lift.RunLift(Robot.oi.GetJoystickYValue(KOI.MANIP_JOYSTICK) *5);;	
	}
}
