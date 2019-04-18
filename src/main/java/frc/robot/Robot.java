
package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Intake intake;
	public static EndGame endgame;
	public static Limelight limelight;

	Command autonomousCommand;
	@Override
	public void robotInit() {
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.getInstance();
		oi = OI.getInstance();

		Robot.limelight.SetLEDMode(Robot.limelight.LED_OFF);
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