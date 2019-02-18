
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Intake intake;
	public static EndGame endgame;
	public static Limelight limelight;
	public static NavX navx;
	public static Pigeon pigeon;
	public static UltrasonicSensor ultra;


	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();
	static SendableChooser<Boolean> isCompBot = new SendableChooser<>();
	@Override
	public void robotInit() {
		isCompBot.addDefault("IsCompBot",true);
		isCompBot.addObject("IsCompBot", true);
		isCompBot.addObject("IsPracticeBot", false);
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.initializeLimelight();
		navx = NavX.initializeNavX();
		pigeon = Pigeon.getInstance();
		ultra = UltrasonicSensor.getInstance();
		oi = OI.getInstance();
		
	}
	

	@Override
	public void disabledInit() {
		Robot.limelight.SetLEDMode(Limelight.LED_OFF);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Robot.limelight.SetLEDMode(Limelight.LED_OFF);

	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		oi.periodic();
	}

	@Override
	public void testPeriodic() {
	}

	public static boolean isCompBot()
	{
		return isCompBot.getSelected();
	}
}

