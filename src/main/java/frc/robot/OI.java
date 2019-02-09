package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.DriverAssistance.*;
import frc.robot.commands.DriverAssistance.Parallel.*;
import frc.robot.RobotMap.KOI;

public class OI {
	private static Joystick leftJoystick = new Joystick(KOI.LEFT_JOYSTICK);
	private static Joystick rightJoystick = new Joystick(KOI.RIGHT_JOYSTICK);
	private static Joystick	manipJoystick = new Joystick(KOI.MANIP_JOYSTICK);

	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, KOI.TRIGGER_BUTTON);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, KOI.TRIGGER_BUTTON);
	private static JoystickButton swapButton = new JoystickButton(rightJoystick, KOI.THUMB_BUTTON);
	private static JoystickButton resetButton = new JoystickButton(rightJoystick, KOI.BUTTON_12);
	private static JoystickButton turnButton = new JoystickButton(rightJoystick, KOI.BUTTON_11);
	private static JoystickButton RunWheelsIn = new JoystickButton(manipJoystick, KOI.BUTTON_3);
	private static JoystickButton RunWheelsOut = new JoystickButton(manipJoystick, KOI.BUTTON_4);
	private static JoystickButton ReleaseHatch = new JoystickButton(manipJoystick, KOI.TRIGGER_BUTTON);
	private static JoystickButton RunEndGame = new JoystickButton(manipJoystick, KOI.THUMB_BUTTON);
	private static JoystickButton CompressorToggle = new JoystickButton(manipJoystick, KOI.BUTTON_7);

	public static OI instance;

	public OI()
	{
		rightTrigger.toggleWhenPressed(new DriveandSteer(KOI.TurnRight));
		leftTrigger.toggleWhenPressed(new DriveandSteer(KOI.TurnLeft));
		resetButton.toggleWhenActive(new ResetNavx());
		turnButton.toggleWhenPressed(new TurnToAngle(0, 4));
		RunWheelsIn.whileHeld(new RunIntakeWheels(-1) );
		RunWheelsOut.whileHeld(new RunIntakeWheels(1) );
		ReleaseHatch.whenActive(new ReleaseHatch(true));
		RunEndGame.whileHeld(new RunEndGame());
		CompressorToggle.toggleWhenPressed(new ToggleCompressor());
	}

	private double DeadbandJoystickValue(double joystickValue) 
	{
		return (Math.abs(joystickValue) < KOI.DRIVE_TRAIN_JOYSTICK_DEADBAND ? 0.0 : joystickValue);
	}

	public double GetJoystickYValue(int joystickNumber) 
	{
		switch(joystickNumber)
		{
			case KOI.LEFT_JOYSTICK:
				return DeadbandJoystickValue(-leftJoystick.getY());
			case KOI.RIGHT_JOYSTICK:
				return DeadbandJoystickValue(-rightJoystick.getY());
			case KOI.MANIP_JOYSTICK:
				return DeadbandJoystickValue(-manipJoystick.getY());
			default: return 0.0;
		}
	}

	public double GetJoystickXValue(int joystickNumber) 
	{
		switch(joystickNumber)
		{
			case KOI.LEFT_JOYSTICK:
				return DeadbandJoystickValue(-leftJoystick.getX());
			case KOI.RIGHT_JOYSTICK:
				return DeadbandJoystickValue(-rightJoystick.getX());
			case KOI.MANIP_JOYSTICK:
				return DeadbandJoystickValue(-manipJoystick.getX());
			default: return 0.0;
		}
	}

	public double GetJoystickZValue(int joystickNumber) 
	{
		switch(joystickNumber)
		{
			case KOI.LEFT_JOYSTICK:
				return DeadbandJoystickValue(-leftJoystick.getZ());
			case KOI.RIGHT_JOYSTICK:
				return DeadbandJoystickValue(-rightJoystick.getZ());
			case KOI.MANIP_JOYSTICK:
				return DeadbandJoystickValue(-manipJoystick.getZ());
				default: return 0.0;
		}
		
	}
	
	public static boolean isSwapPressed()
	{
		return swapButton.get();
	}

	public static OI getInstance() {if (instance == null) {instance = new OI();}return instance;}

}
