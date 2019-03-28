package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.MotorCommands.*;
import frc.robot.commands.PnuematicCommands.*;

import frc.robot.RobotMap.KOI;

public class OI {
	public static Joystick 
		leftJoystick = new Joystick(KOI.LEFT_JOYSTICK),
		rightJoystick = new Joystick(KOI.RIGHT_JOYSTICK),
		manipJoystick = new Joystick(KOI.MANIP_JOYSTICK);

	public static Joystick[] joysticks = {leftJoystick, rightJoystick, manipJoystick};

	public static JoystickButton 
		leftTrigger = new JoystickButton(leftJoystick, KOI.TRIGGER_BUTTON),
		turnTo0 = new JoystickButton(leftJoystick, KOI.BUTTON_3),
		turnToRocketClose = new JoystickButton(leftJoystick, KOI.BUTTON_4),
		turnToRocketFar = new JoystickButton(leftJoystick, KOI.BUTTON_5),
		rightTrigger = new JoystickButton(rightJoystick, KOI.TRIGGER_BUTTON),

		fieldOrientated = new JoystickButton(rightJoystick, KOI.THUMB_BUTTON),
		fullSpeedTurn = new JoystickButton(leftJoystick, KOI.TRIGGER_BUTTON),
		resetButton = new JoystickButton(rightJoystick, KOI.BUTTON_12),
		turnButton = new JoystickButton(rightJoystick, KOI.BUTTON_11),
		RunEndgameUp = new JoystickButton(rightJoystick, KOI.BUTTON_4),
		RunEndgameDown = new JoystickButton(rightJoystick, KOI.BUTTON_6),

		RunWheelsOut = new JoystickButton(manipJoystick, KOI.TRIGGER_BUTTON),
		RunWheelsIn = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),

		RunElbowUp = new JoystickButton(manipJoystick, KOI.BUTTON_6),
		RunElbowDown = new JoystickButton(manipJoystick, KOI.BUTTON_4),

		ReleaseHatch = new JoystickButton(manipJoystick, KOI.BUTTON_3),
		EndGameButton = new JoystickButton(rightJoystick, KOI.THUMB_BUTTON),
		IntakeEndGame = new JoystickButton(rightJoystick, KOI.BUTTON_3),

		RunEndGame = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),
		CompressorToggle = new JoystickButton(manipJoystick, KOI.BUTTON_7),

		LifttoPos0 = new JoystickButton(manipJoystick, KOI.BUTTON_9),
		LifttoPos1 = new JoystickButton(manipJoystick, KOI.BUTTON_10),
		LifttoPos2 = new JoystickButton(manipJoystick, KOI.BUTTON_11),
		LifttoPos3 = new JoystickButton(manipJoystick, KOI.BUTTON_12),
		resetEncoder = new JoystickButton(manipJoystick, KOI.BUTTON_8);

	public static OI instance;

	public OI() {
		ReleaseHatch.whenActive(new ReleaseHatch(true));
		IntakeEndGame.whenActive(new ReleaseEndgame(true));
		//EndGameButton.whenActive(new EndgameSetToPos());
		RunWheelsIn.whileHeld(new RunIntakeWheels(1));
		RunWheelsOut.whileHeld(new RunIntakeWheels(-1));

		RunElbowDown.whileHeld(new MoveIntakeElbow(1));
		RunElbowUp.whileHeld(new MoveIntakeElbow(-1));

		CompressorToggle.toggleWhenPressed(new ToggleCompressor());
	}

	private double DeadbandJoystickValue(double joystickValue) {
		return (Math.abs(joystickValue) < KOI.JOYSTICK_DEADBAND ? 0.0 : joystickValue);
	}

	public double GetJoystickYValue(int joystickNumber) {
		return DeadbandJoystickValue( -joysticks[joystickNumber].getY() );
	}

	public double GetJoystickXValue(int joystickNumber) {
		return DeadbandJoystickValue( -joysticks[joystickNumber].getX() );
	}

	public double GetJoystickZValue(int joystickNumber) {
		return DeadbandJoystickValue( -joysticks[joystickNumber].getZ() );
	}

	public static boolean fieldOrientated() {
		return fieldOrientated.get();
	}
	public boolean isUpEndPressed()
	{
		return RunEndgameUp.get();
	}
	public boolean isDownEndPressed()
	{
		return RunEndgameDown.get();
	}
	public static boolean turnFast()
	{
		return fullSpeedTurn.get();
	}
	public boolean isEndGamePressed()
	{
		return EndGameButton.get();
	}

	public static boolean SlowDown()
	{
		return leftTrigger.get() || rightTrigger.get();
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
		return (-((Math.abs(manipJoystick.getRawAxis(3)) < KOI.JOYSTICK_DEADBAND) ?
			0 : manipJoystick.getRawAxis(3)) + 1) / 2;
	}

	public double returnLeftSlider() {
		return (-((Math.abs(leftJoystick.getRawAxis(3)) < KOI.JOYSTICK_DEADBAND) ? 
			0 : leftJoystick.getRawAxis(3)) + 1) / 2;
	}

	public double returnRightSlider() {
		return (-((Math.abs(rightJoystick.getRawAxis(3)) < KOI.JOYSTICK_DEADBAND) ? 
			0 : rightJoystick.getRawAxis(3)) + 1) / 2;
	}

	public static OI getInstance() {if (instance == null) {instance = new OI();}return instance;}
}
