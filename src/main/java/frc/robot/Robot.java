
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Intake intake;
	public static EndGame endgame;
	public static Limelight limelight;
	

	public static boolean isComp;

	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();
	static SendableChooser<Boolean> isCompBot = new SendableChooser<>();
	@Override
	public void robotInit() {
		isCompBot.setDefaultOption("IsCompBot",true); 
		isCompBot.addOption("IsPracticeBot", false);
		SmartDashboard.putData(isCompBot);
		isComp = isCompBot.getSelected();
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.getInstance();
		
		oi = OI.getInstance();

		Robot.limelight.initLimelight(limelight.LED_OFF, limelight.VISION_PIPELINE);
	}
	

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {}

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
	}

	@Override
	public void testPeriodic() {}
}