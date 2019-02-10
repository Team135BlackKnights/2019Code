package frc.robot;


public class RobotMap {

	public interface KOI
	{
		public static boolean 
			TurnLeft = true,
			TurnRight = false;

		public static final int 
			LEFT_JOYSTICK = 0,
			RIGHT_JOYSTICK = 1,
			MANIP_JOYSTICK = 2;

		public  static final int 
			TRIGGER_BUTTON = 1,
			THUMB_BUTTON = 2,
			BUTTON_3 = 3,
			BUTTON_4 = 4,
			BUTTON_5 = 5,
			BUTTON_6 = 6,
			BUTTON_7 = 7,
			BUTTON_8 = 8,
			BUTTON_9 = 9,
			BUTTON_10 = 10,
			BUTTON_11 = 11,
			BUTTON_12 = 12;

		public static final double 
			DRIVE_TRAIN_JOYSTICK_DEADBAND = .1;
	}

public interface Robot
{
	public interface KDrivetrain 
	{
		public static final int 
			FRONT_LEFT_SPARK_ID = 0,
			FRONT_RIGHT_SPARK_ID = 1,
			REAR_LEFT_SPARK_ID = 2,
			REAR_RIGHT_SPARK_ID = 3;

		public static final int 
			CONTROL_FRAME_PERIOD = 1000;
	}

	public interface KLift 
	{
		public static final int 
			LIFT_LEFT_TALON = 1,
			LIFT_RIGHT_TALON = 2,
			LIFT_LEFT_VICTOR = 1,
			LIFT_RIGHT_VICTOR = 3;

		public static final int 
			LIFT_TOLERANCE = 50; // ticks
		public static final int 
			LIFT_SETPOINT_0 = 0,
			LIFT_SETPOINT_1 = 161,
			LIFT_SETPOINT_2 = 249,
			LIFT_SETPOINT_3 = 443,
			LIFT_SETPOINT_4 = 501;
		public static final int[] 
			LIFT_SETPOINTS = {LIFT_SETPOINT_0, LIFT_SETPOINT_1, LIFT_SETPOINT_2, LIFT_SETPOINT_3, LIFT_SETPOINT_4};
	}

	public interface KIntake
	{
		public static final int 
			INTAKE_ELBOW_TALON = 0,
			INTAKE_VICTOR = 0,
			VEX_BUTTON_ID = 2;

		public static final boolean 
			leftInverted = false,
			rightInverted = false; 
	}
	public interface EndGame
	{
		public static int 
			END_GAME_SPARK = 4;
	}
	public interface Pneumatics
	{
		public static final int 
			INTAKE_SOLENOID = 0,
			COMPRESSOR_ID	= 7,
			ENDGAME_PISTON =1;
	}
	public interface Timeouts
	{
		public static final double 
			INTAKE_TIMEOUT = .5,
			LIFT_TIMEOUT = 1;
	}
}}