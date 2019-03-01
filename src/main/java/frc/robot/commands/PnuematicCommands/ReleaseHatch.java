package frc.robot.commands.PnuematicCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.Command;

public class ReleaseHatch extends Command {
	private static boolean solenoidPosition = true;

	public ReleaseHatch(boolean pistonsInOut) {
	}

	protected void execute() {
		solenoidPosition = !solenoidPosition;
		Robot.intake.MoveSolenoid(solenoidPosition);
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
