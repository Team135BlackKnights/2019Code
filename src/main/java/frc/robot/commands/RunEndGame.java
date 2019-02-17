package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class RunEndGame extends Command {
	private double _power;

	public RunEndGame(double power) {
		requires(Robot.endgame);
		_power = power;
	}

	protected void execute() {
		Robot.endgame.RunEndGame(_power);
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
