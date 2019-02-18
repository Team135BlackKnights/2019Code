package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.commands.Sensors.*;
import frc.robot.commands.DriverAssistance.*;
import frc.robot.commands.DriverAssistance.Parallel.*;
import frc.robot.subsystems.Limelight;
import frc.robot.RobotMap.KOI;

public class OI {
	private static Joystick leftJoystick = new Joystick(KOI.LEFT_JOYSTICK);
	private static Joystick rightJoystick = new Joystick(KOI.RIGHT_JOYSTICK);
	public static Joystick manipJoystick = new Joystick(KOI.MANIP_JOYSTICK);

	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, KOI.TRIGGER_BUTTON),
			turnTo0 = new JoystickButton(leftJoystick, KOI.BUTTON_3),
			turnToRocketClose = new JoystickButton(leftJoystick, KOI.BUTTON_4),
			turnToRocketFar = new JoystickButton(leftJoystick, KOI.BUTTON_5),
			rightTrigger = new JoystickButton(rightJoystick, KOI.TRIGGER_BUTTON),

			fieldOrientated = new JoystickButton(rightJoystick, KOI.THUMB_BUTTON),
			resetButton = new JoystickButton(rightJoystick, KOI.BUTTON_12),
			turnButton = new JoystickButton(rightJoystick, KOI.BUTTON_11),
			RunEndgameUp = new JoystickButton(rightJoystick, KOI.BUTTON_4),
			RunEndgameDown = new JoystickButton(rightJoystick, KOI.BUTTON_6),

			RunWheelsIn = new JoystickButton(manipJoystick, KOI.TRIGGER_BUTTON),
			RunWheelsOut = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),

			RunElbowUp = new JoystickButton(manipJoystick, KOI.BUTTON_6),
			RunElbowDown = new JoystickButton(manipJoystick, KOI.BUTTON_4),

			ReleaseHatch = new JoystickButton(manipJoystick, KOI.BUTTON_3),

			ReleaseEndGame = new JoystickButton(rightJoystick, KOI.BUTTON_5),

			PullString = new JoystickButton(rightJoystick, KOI.BUTTON_3),

			RunEndGame = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),
			CompressorToggle = new JoystickButton(manipJoystick, KOI.BUTTON_7),

			LifttoPos0 = new JoystickButton(manipJoystick, KOI.BUTTON_9),
			LifttoPos1 = new JoystickButton(manipJoystick, KOI.BUTTON_10),
			LifttoPos2 = new JoystickButton(manipJoystick, KOI.BUTTON_11),
			LifttoPos3 = new JoystickButton(manipJoystick, KOI.BUTTON_12);

	public static OI instance;

	public OI() {
		rightTrigger.toggleWhenPressed(new DriveandSteer(KOI.TurnRight, Limelight.HATCH_PIPELINE));
		leftTrigger.toggleWhenPressed(new DriveandSteer(KOI.TurnLeft, Limelight.BALL_PIPELINE));
		resetButton.toggleWhenActive(new ResetNavX());
		turnButton.toggleWhenPressed(new TurnToAngle(0, 4));

		ReleaseHatch.whenActive(new ReleaseHatch(true));

		ReleaseEndGame.whenPressed(new ReleaseEndgame(true));

		PullString.whenPressed(new PullEndGameString(false));

		RunWheelsIn.whileHeld(new RunIntakeWheels(-.75));
		RunWheelsOut.whileHeld(new RunIntakeWheels(1));

		RunElbowDown.whileHeld(new MoveIntakeElbow(-1));
		RunElbowUp.whileHeld(new MoveIntakeElbow(1));

		RunEndgameUp.whileHeld(new RunEndGame(1));
		RunEndgameDown.whileHeld(new RunEndGame(-1));

		CompressorToggle.toggleWhenPressed(new ToggleCompressor());

	}

	private double DeadbandJoystickValue(double joystickValue) {
		return (Math.abs(joystickValue) < KOI.DRIVE_TRAIN_JOYSTICK_DEADBAND ? 0.0 : joystickValue);
	}

	public double GetJoystickYValue(int joystickNumber) {
		switch (joystickNumber) {
		case KOI.LEFT_JOYSTICK:
			return DeadbandJoystickValue(-leftJoystick.getY());
		case KOI.RIGHT_JOYSTICK:
			return DeadbandJoystickValue(-rightJoystick.getY());
		case KOI.MANIP_JOYSTICK:
			return DeadbandJoystickValue(-manipJoystick.getY());
		default:
			return 0.0;
		}
	}

	public double GetJoystickXValue(int joystickNumber) {
		switch (joystickNumber) {
		case KOI.LEFT_JOYSTICK:
			return DeadbandJoystickValue(-leftJoystick.getX());
		case KOI.RIGHT_JOYSTICK:
			return DeadbandJoystickValue(-rightJoystick.getX());
		case KOI.MANIP_JOYSTICK:
			return DeadbandJoystickValue(-manipJoystick.getX());
		default:
			return 0.0;
		}
	}

	public double GetJoystickZValue(int joystickNumber) {
		switch (joystickNumber) {
		case KOI.LEFT_JOYSTICK:
			return DeadbandJoystickValue(-leftJoystick.getZ());
		case KOI.RIGHT_JOYSTICK:
			return DeadbandJoystickValue(-rightJoystick.getZ());
		case KOI.MANIP_JOYSTICK:
			return DeadbandJoystickValue(-manipJoystick.getZ());
		default:
			return 0.0;
		}

	}

	public static boolean fieldOrientated() {
		return fieldOrientated.get();
	}

	public static int liftButtons() {
		if (LifttoPos0.get()) {
			return 0;
		}
		if (LifttoPos1.get()) {
			return 1;
		}
		if (LifttoPos2.get()) {
			return 2;
		}
		if (LifttoPos3.get()) {
			return 3;
		}
		return -1;
	}

	public double returnManipSlider() {
		return (-((Math.abs(manipJoystick.getRawAxis(3)) < .15) ? 0 : manipJoystick.getRawAxis(3)) + 1) / 2;
	}

	public double returnLeftSlider() {
		return (-((Math.abs(leftJoystick.getRawAxis(3)) < .15) ? 0 : leftJoystick.getRawAxis(3)) + 1) / 2;
	}

	public double returnRightSlider() {
		return (-((Math.abs(rightJoystick.getRawAxis(3)) < .15) ? 0 : rightJoystick.getRawAxis(3)) + 1) / 2;
	}

	public void periodic() {
		SmartDashboard.putNumber("Manip Joystick Throttle ", returnManipSlider());
		SmartDashboard.putNumber("Right Joystick Throttle ", returnRightSlider());
		SmartDashboard.putNumber("Left Joystick Throttle ", returnLeftSlider());
	}

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}

}
