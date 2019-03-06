package frc.robot.commands.MotorCommands;

import frc.robot.*;
import edu.wpi.first.wpilibj.command.Command;

public class RunIntakePOV extends Command {
    public int _joystickNumber;
	public RunIntakePOV(int joystickNumber) {
        requires(Robot.intake);
        _joystickNumber = joystickNumber;

	}

	protected void execute() {
        if (OI.PovDirection(_joystickNumber, OI.UP_POV))
        {
            Robot.intake.RunElbow(-1);
        }
        else if (OI.PovDirection(_joystickNumber, OI.DOWN_POV))
        {
            Robot.intake.RunElbow(1);
        }
        else if (OI.PovDirection(_joystickNumber, OI.RIGHT_POV))
        {
            Robot.intake.RunIntake(1);
        }
        else if (OI.PovDirection(_joystickNumber, OI.LEFT_POV))
        {
            Robot.intake.RunIntake(-1);
        }
        else 
        {
            Robot.intake.RunElbow(0);
            Robot.intake.RunIntake(0);
        }


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
