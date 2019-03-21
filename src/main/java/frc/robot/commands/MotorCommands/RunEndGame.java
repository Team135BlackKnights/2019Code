package frc.robot.commands.MotorCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class RunEndGame extends Command {
	private double _power;
	public static boolean beginingMatch = true,
	disableEndgame = false;
	public RunEndGame(double power) {
		requires(Robot.endgame);
		_power = power;
	}

	protected void execute() {
		if ( (!disableEndgame && _power > 0) || _power < 0)
		{
			Robot.endgame.RunEndGame(_power);
		}
		if (beginingMatch)
		{
			beginingMatch = Robot.endgame.isSwitchPressed();
		}
		else
		{
			disableEndgame = Robot.endgame.isSwitchPressed();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.endgame.RunEndGame(0);
	}

	protected void interrupted() {
		end();
	}
}
