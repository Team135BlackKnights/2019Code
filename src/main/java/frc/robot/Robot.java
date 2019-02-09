
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.NavX;

public class Robot extends TimedRobot 
{
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Intake intake; 
	public static EndGame endgame;
	public static Limelight limelight;
	public static NavX navx;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	@Override
	public void robotInit() 
	{
		oi = OI.getInstance();
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.initializeLimelight();
		navx = NavX.initializeNavX();
		Robot.limelight.SetLEDMode(Limelight.LED_OFF);
	}
	

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {}

	@Override
	public void autonomousPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() 
	{
		if (m_autonomousCommand != null) 
		{
			m_autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {}
}
