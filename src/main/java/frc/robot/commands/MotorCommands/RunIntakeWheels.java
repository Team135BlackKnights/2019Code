package frc.robot.commands.MotorCommands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakeWheels extends Command {
	// Here we set the intake wheels to a power delcared when the command is called for
	private double power;

	public RunIntakeWheels(double power) {
		this.power = power;
	}

	protected void execute() {
		Robot.intake.RunIntake(this.power);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.RunIntake(0);
	}

	protected void interrupted() {
		end();
	}
}
