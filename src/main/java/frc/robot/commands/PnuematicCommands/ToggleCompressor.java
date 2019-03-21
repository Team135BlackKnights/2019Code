package frc.robot.commands.PnuematicCommands;

import frc.robot.*;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ToggleCompressor extends InstantCommand {
	private static boolean isCompressorOn = true;

	public ToggleCompressor() {
		requires(Robot.intake);
	}

	protected void execute() {
		isCompressorOn = !isCompressorOn;
		if (isCompressorOn) {
			Robot.intake.setCompressorOn();
		} else {
			Robot.intake.setCompressorOff();
		}
	}
}