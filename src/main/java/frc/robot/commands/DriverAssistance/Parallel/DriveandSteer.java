package frc.robot.commands.DriverAssistance.Parallel;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.*;

public class DriveandSteer extends CommandGroup 
{
	public DriveandSteer(boolean TurnLeft) 
	{
		
    	requires(Robot.driveTrain);
		requires(Robot.limelight);
		Robot.limelight.SetLEDMode(Limelight.LED_ON);
        	addSequential(new LineUp(TurnLeft));
			addParallel(new DriveToward());
    	}
    }

