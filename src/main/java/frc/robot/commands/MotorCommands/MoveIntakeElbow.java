package frc.robot.commands.MotorCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class MoveIntakeElbow extends Command 
{
	// This will, when ran, set the power of the intake elbow to move at a desired speed
	private double _power;

	public MoveIntakeElbow(double power) 
	{
		requires(Robot.intake);
		_power = power;
	}

	protected void execute() {
		Robot.intake.RunElbow(_power);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.RunElbow(0);
	}

	protected void interrupted() {
		end();
	}
}
