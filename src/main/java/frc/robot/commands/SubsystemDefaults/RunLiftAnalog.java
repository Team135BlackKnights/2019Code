package frc.robot.commands.SubsystemDefaults;

import frc.robot.*;
import frc.robot.RobotMap.KOI;
import edu.wpi.first.wpilibj.command.Command;

public class RunLiftAnalog extends Command {
	public RunLiftAnalog() {
		requires(Robot.lift);
	}

	protected void execute() {
		Robot.lift.RunLift(Robot.oi.GetJoystickYValue(KOI.MANIP_JOYSTICK) * 5 * (Robot.oi.returnManipSlider() + 1) / 2);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
