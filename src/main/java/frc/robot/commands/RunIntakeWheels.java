package frc.robot.commands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakeWheels extends Command {
	private double power;

	public RunIntakeWheels(double power) {
		this.power = power;
	}

	protected void execute() {
		Robot.intake.RunIntake(this.power);
	}

	@Override
	protected boolean isFinished() {
		return Robot.ultra.isBallinIntake();
	}

	protected void end() {
		Robot.intake.RunIntake(0);
	}

	protected void interrupted() {
		end();
	}
}
