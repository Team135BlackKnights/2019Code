package frc.robot.commands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class PullEndGameString extends Command {
	private static boolean solenoidPosition = true;

	public PullEndGameString(boolean pistonsInOut) {
		//solenoidPosition = pistonsInOut;
		setTimeout(RobotMap.Robot.Timeouts.INTAKE_TIMEOUT);
	}

	protected void execute() {
		solenoidPosition = !solenoidPosition;
		Robot.endgame.movePistoon(solenoidPosition);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		//Robot.intake.MoveSolenoid(false);
	}

	protected void interrupted() {
		end();
	}
}
