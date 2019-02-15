
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
	public static UltrasonicSensor ultra;

	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();

	@Override
	public void robotInit() 
	{
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.initializeLimelight();
		navx = NavX.getInstance();
		ultra = UltrasonicSensor.getInstance();
		oi = OI.getInstance();
		chooser.setDefaultOption("Nate Ralston", "5960");
		chooser.addOption("Daddy Doshi", "2160");
		chooser.addOption("Bad Braylen", "1111");
		chooser.addOption("Robust Robotma", "1234");
		SmartDashboard.putData("Password Select", chooser);
	}
	

	@Override
	public void disabledInit() {
		Robot.limelight.SetLEDMode(Limelight.LED_OFF);
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
		if (autonomousCommand != null) 
		{
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() 
	{
		
		if (Integer.parseInt(chooser.getSelected()) == 6 / 3 + 5 * 6 * 0 - 12 + 9 + 6 * 6 * 6 * 10)
		{
			Scheduler.getInstance().run();
		}
	}

	@Override
	public void testPeriodic() {}
}
