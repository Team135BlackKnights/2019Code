package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.DriverAssistance.*;
import frc.robot.commands.DriverAssistance.Parallel.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static boolean TurnLeft = true;
	public static boolean TurnRight = false;

	private static Joystick leftJoystick = new Joystick(RobotMap.OI.LEFT_JOYSTICK);
	private static Joystick rightJoystick = new Joystick(RobotMap.OI.RIGHT_JOYSTICK);
	private static Joystick	manipJoystick = new Joystick(RobotMap.OI.MANIP_JOYSTICK);

	public static JoystickButton leftTrigger = new JoystickButton(leftJoystick, RobotMap.OI.TRIGGER_BUTTON);
	public static JoystickButton rightTrigger = new JoystickButton(rightJoystick, RobotMap.OI.TRIGGER_BUTTON);
	private static JoystickButton swapButton = new JoystickButton(rightJoystick, RobotMap.OI.THUMB_BUTTON);
	private static JoystickButton resetButton = new JoystickButton(rightJoystick, RobotMap.OI.BUTTON_12);
	private static JoystickButton turnButton = new JoystickButton(rightJoystick, RobotMap.OI.BUTTON_11);
	private static JoystickButton RunWheelsIn = new JoystickButton(manipJoystick, RobotMap.OI.BUTTON_3);
	private static JoystickButton RunWheelsOut = new JoystickButton(manipJoystick, RobotMap.OI.BUTTON_4);
	private static JoystickButton ReleaseHatch = new JoystickButton(manipJoystick, RobotMap.OI.TRIGGER_BUTTON);
	private static JoystickButton RunEndGame = new JoystickButton(manipJoystick, RobotMap.OI.THUMB_BUTTON);
	private static JoystickButton CompressorON = new JoystickButton(manipJoystick, RobotMap.OI.BUTTON_7);
	private static JoystickButton compressorOFF = new JoystickButton(manipJoystick, RobotMap.OI.BUTTON_9);
	// DeadbandJoystickValue()
	private double DRIVE_TRAIN_JOYSTICK_DEADBAND = .1;
	private double returnJoystickValue;

	// GetJoystickYValue()
	private double joystickYValue;
	private double joystickXValue;
	private double joystickZValue;

	public static OI instance;

	public static OI initializeOperatorInterface() 
	{
		if (instance == null) 
		{
			instance = new OI();
		}
		return instance;
	}

	private double DeadbandJoystickValue(double joystickValue) 
	{
		if (Math.abs(joystickValue) >= DRIVE_TRAIN_JOYSTICK_DEADBAND) 
		{
			returnJoystickValue = joystickValue;
		} else {
			returnJoystickValue = 0.0;
		}
		return returnJoystickValue;
	}

	public double GetJoystickYValue(int joystickNumber) 
	{
		if (joystickNumber == RobotMap.OI.LEFT_JOYSTICK) 
		{
			joystickYValue = this.DeadbandJoystickValue(-leftJoystick.getY());
		} else if (joystickNumber == RobotMap.OI.RIGHT_JOYSTICK) 
		{
			joystickYValue = this.DeadbandJoystickValue(-rightJoystick.getY());
		}
		else if (joystickNumber == RobotMap.OI.MANIP_JOYSTICK)
		{
			joystickYValue = this.DeadbandJoystickValue(-manipJoystick.getY());
		}

		return joystickYValue;
	}

	public double GetJoystickXValue(int joystickNumber) 
	{
		if (joystickNumber == RobotMap.OI.LEFT_JOYSTICK) {
			joystickXValue = this.DeadbandJoystickValue(-leftJoystick.getX());
		} else if (joystickNumber == RobotMap.OI.RIGHT_JOYSTICK) 
		{
			joystickXValue = this.DeadbandJoystickValue(-rightJoystick.getX());
		}
		else if (joystickNumber == RobotMap.OI.MANIP_JOYSTICK)
		{
			joystickXValue = this.DeadbandJoystickValue(-manipJoystick.getX());
		}

		return joystickXValue;
	}

	public double GetJoystickZValue(int joystickNumber) 
	{
		if (joystickNumber == RobotMap.OI.LEFT_JOYSTICK) {
			joystickZValue = this.DeadbandJoystickValue(-leftJoystick.getZ());
		} else if (joystickNumber == RobotMap.OI.RIGHT_JOYSTICK) 
		{
			joystickZValue = this.DeadbandJoystickValue(-rightJoystick.getZ());
		} else if (joystickNumber == RobotMap.OI.MANIP_JOYSTICK)
		{
			joystickZValue = this.DeadbandJoystickValue(-manipJoystick.getZ());
		}

		return joystickZValue;
	}
	
	public static boolean isSwapPressed()
	{
		return swapButton.get();
	}
	
	public static void initializeButtonsWithCommands()
	{
		rightTrigger.toggleWhenPressed(new DriveandSteer(TurnRight));
		leftTrigger.toggleWhenPressed(new DriveandSteer(TurnLeft));
		resetButton.toggleWhenActive(new ResetNavX());
		turnButton.toggleWhenPressed(new TurnToAngle(0, 4));
		RunWheelsIn.whileHeld(new RunIntakeWheels(-1));
		RunWheelsOut.whileHeld(new RunIntakeWheels(1));
		ReleaseHatch.whenActive(new ReleaseHatch(true));
		RunEndGame.whileHeld(new RunEndGame());
		CompressorON.toggleWhenPressed(new ToggleCompressor(true));
		compressorOFF.toggleWhenPressed(new ToggleCompressor(false));
		return;
	}
}
