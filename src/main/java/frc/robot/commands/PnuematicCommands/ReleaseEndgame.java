package frc.robot.commands.PnuematicCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseEndgame extends Command {
	// This command when ran will set the endgame pistons to the state that they are currently not at
	private static boolean solenoidPosition = true;

	public ReleaseEndgame(boolean pistonsInOut) {
	}

	protected void execute() {
		solenoidPosition = !solenoidPosition;
		Robot.endgame.movePiston(solenoidPosition);
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}
