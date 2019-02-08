package frc.robot;


public class RobotMap {

	public interface OI
	{
	public static final int LEFT_JOYSTICK = 0;
	public static final int RIGHT_JOYSTICK = 1;
	public static final int MANIP_JOYSTICK = 2;

	public  static final int TRIGGER_BUTTON = 1;
	public static final int THUMB_BUTTON =2;
	public static final int BUTTON_3 = 3;
	public static final int BUTTON_4 = 4;
	public static final int BUTTON_5 = 5;
	public static final int BUTTON_6 = 6;
	public static final int BUTTON_7 = 7;
	public static final int BUTTON_8 = 8;
	public static final int BUTTON_9 = 9;
	public static final int BUTTON_10 = 10;
	public static final int BUTTON_11 = 11;
	public static final int BUTTON_12 = 12;

	}

public interface Robot
{
	public interface KDrivetrain 
	{
	public static final int FRONT_LEFT_SPARK_ID = 0;
	public static final int FRONT_RIGHT_SPARK_ID = 1;
	public static final int REAR_LEFT_SPARK_ID = 2;
	public static final int REAR_RIGHT_SPARK_ID = 3;

	public static final int CONTROL_FRAME_PERIOD = 1000;
	}

	public interface KLift 
	{
		public static final int LIFT_LEFT_TALON = 1;
		public static final int LIFT_RIGHT_TALON = 2;
		public static final int LIFT_LEFT_VICTOR = 1;
		public static final int LIFT_RIGHT_VICTOR = 3;
	}

	public interface KIntake
	{
		public static final int INTAKE_ELBOW_TALON = 0;
		public static final int LEFT_INTAKE_VICTOR = 2;
		public static final int RIGHT_INTAKE_VICTOR = 0;
		public static final int VEX_BUTTON_ID = 0;

		public static final boolean leftInverted = false;
		public static final boolean rightInverted = false; 
	}
	public interface EndGame
	{
		public static int END_GAME_SPARK = 4;
	}

	public interface Pneumatics
	{
		public static final int INTAKE_SOLENOID = 0;
		public static final int COMPRESSOR_ID	= 7;
	}
	public interface Timeouts
	{
		public static final double INTAKE_TIMEOUT = .5;
		public static final double LIFT_TIMEOUT = 1;
	}
}}