package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MotorCommands.*;
import frc.robot.commands.PnuematicCommands.*;

import frc.robot.commands.Sensors.*;
import frc.robot.RobotMap.KOI;

public class OI {
	public static Joystick 
		leftJoystick = new Joystick(KOI.LEFT_JOYSTICK),
		rightJoystick = new Joystick(KOI.RIGHT_JOYSTICK),
		manipJoystick = new Joystick(KOI.MANIP_JOYSTICK);

		public static double povValue;
		public static boolean PovDirection;
		public static final int 
		UP_POV =0, RIGHT_POV =1, LEFT_POV = 2, DOWN_POV = 3;
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
		RunEndgameUP_POV = new JoystickButton(rightJoystick, KOI.BUTTON_4),
		RunEndgameDown = new JoystickButton(rightJoystick, KOI.BUTTON_6),

		RunWheelsIn = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),
		RunWheelsOut = new JoystickButton(manipJoystick, KOI.TRIGGER_BUTTON),

		RunElbowUP_POV = new JoystickButton(manipJoystick, KOI.BUTTON_6),
		RunElbowDown = new JoystickButton(manipJoystick, KOI.BUTTON_4),

		ReleaseHatch = new JoystickButton(manipJoystick, KOI.BUTTON_3),
		ReleaseEndGame = new JoystickButton(rightJoystick, KOI.BUTTON_3),
		IntakeEndGame = new JoystickButton(rightJoystick, KOI.BUTTON_5),

		RunEndGame = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON),
		CompressorToggle = new JoystickButton(manipJoystick, KOI.BUTTON_7),

		LifttoPos0 = new JoystickButton(manipJoystick, KOI.BUTTON_9),
		LifttoPos1 = new JoystickButton(manipJoystick, KOI.BUTTON_10),
		LifttoPos2 = new JoystickButton(manipJoystick, KOI.BUTTON_11),
		LifttoPos3 = new JoystickButton(manipJoystick, KOI.BUTTON_12),
		resetEncoder = new JoystickButton(manipJoystick, KOI.BUTTON_6);

	public static OI instance;

	public OI() {
		//resetButton.toggleWhenActive(new ResetGyro());

		ReleaseHatch.whenActive(new ReleaseHatch(true));
		ReleaseEndGame.whenActive(new ReleaseEndgame(false));
		IntakeEndGame.whenActive(new ReleaseEndgame(true));

		RunWheelsIn.whileHeld(new RunIntakeWheels(-1));
		RunWheelsOut.whileHeld(new RunIntakeWheels(1));

		RunElbowDown.whileHeld(new MoveIntakeElbow(1));
		RunElbowUP_POV.whileHeld(new MoveIntakeElbow(-1));

		RunEndgameUP_POV.whileHeld(new RunEndGame(1));
		RunEndgameDown.whileHeld(new RunEndGame(-1));

		CompressorToggle.toggleWhenPressed(new ToggleCompressor());
		resetEncoder.whenPressed(new resetEncoderLift());
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
	public void isJoystickPluggedin(int joystickNumber)
	{
		//boolean yes = joysticks[joystickNumber].getPort();
		//if (yes)
	}

	public static boolean fullSpeedTurn()
	{
		return fullSpeedTurn.get();
	}

	public static int GetAnglePov(int joystickNumber)
	{
		return joysticks[joystickNumber].getPOV();
	}
	public static boolean PovDirection(int joystickNumber,int povDirection)
	{
		povValue = GetAnglePov(joystickNumber);

		switch(povDirection) {
			case (UP_POV):
				if (povValue >= 315.0 || (povValue <= 45.0 && povValue != -1)) {
					PovDirection = true;
				}
				else {
					PovDirection = false;
				}
				break;
			case (RIGHT_POV):
				if (povValue >= 45.0 && povValue <= 135.0) {
					PovDirection = true;
				}
				else {
					PovDirection = false;
				}
				break;
			case (DOWN_POV):
				if (povValue >= 135.0 && povValue <= 225.0) {
					PovDirection = true;
				}
				else {
					PovDirection = false;
				}
				break;
			case (LEFT_POV):
				if (povValue >= 225.0 && povValue <= 315.0) {
					PovDirection = true;
				}
				else {
					PovDirection = false;
				}
				break;
			}
			return PovDirection;
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
	public void periodic() {
		SmartDashboard.putNumber("RIGHT POV", GetAnglePov(1));
	}

	public static OI getInstance() {if (instance == null) {instance = new OI();}return instance;}
}
