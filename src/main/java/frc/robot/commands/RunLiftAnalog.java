package frc.robot.commands;

import frc.robot.*;
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
		Timer timer = new Timer();
		timer.start();
		Lift.setpoint += Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK) * 5000 * timer.get(); //50 ticks/second * seconds = speed
		SmartDashboard.putNumber("Joystick Setpoint Add", Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK) * 50 * timer.get());
	}
}
