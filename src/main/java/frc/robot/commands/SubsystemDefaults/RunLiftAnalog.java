package frc.robot.commands.SubsystemDefaults;

import frc.robot.*;
import frc.robot.RobotMap.KOI;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class RunLiftAnalog extends InstantCommand {
	public RunLiftAnalog() {
		requires(Robot.lift);
	}

	protected void execute() {
		Robot.lift.RunLift(Robot.oi.GetJoystickYValue(KOI.MANIP_JOYSTICK) * 5);
		;
	}
}
