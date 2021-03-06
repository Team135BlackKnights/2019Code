package frc.robot;

public class RobotMap {
	// This is where we store all the values that we will want to need to keep constant and use for the robot
	public interface KOI {
		public static boolean 
			TurnLeft = true, 
			TurnRight = false;

		public static final int 
			LEFT_JOYSTICK = 0, 
			RIGHT_JOYSTICK = 1, 
			MANIP_JOYSTICK = 2,

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
			JOYSTICK_DEADBAND = .2;
	}

	public interface Robot {
		public interface KDrivetrain {
			public static final int 
				FRONT_LEFT_SPARK_ID = 7, 
				FRONT_RIGHT_SPARK_ID = 1, 
				REAR_LEFT_SPARK_ID = 2,
				REAR_RIGHT_SPARK_ID = 3;
			public static final double OVERHEAT_CONSTANT = 160;
		}

		public interface KLift {
			public static final int 
				LIFT_LEFT_TALON = 1, 
				LIFT_RIGHT_TALON = 2, 
				LIFT_LEFT_SPARK = 5, 
				LIFT_RIGHT_SPARK = 6,

				LIFT_TOLERANCE = 1, // ticks
				LIFT_PIDTOLERANCE = 8,

				LIFT_SETPOINT_0 = 1, 
				LIFT_SETPOINT_1 = 9, 
				LIFT_SETPOINT_2 = 71,
				LIFT_SETPOINT_3 = 124;
			public static final double 
				STATICP = 0.1,
				ERRORP = 0.1;
			public static final int[] 
				LIFT_SETPOINTS = { LIFT_SETPOINT_0, LIFT_SETPOINT_1, LIFT_SETPOINT_2, LIFT_SETPOINT_3 };

			public static final int 
				ENCODER_A = 8, ENCODER_B = 7;
		}

		public interface KIntake {
			public static final int 
				INTAKE_ELBOW_TALON = 0, 
				INTAKE_VICTOR = 2;

			public static final boolean 
				leftInverted = false, 
				rightInverted = false;
		}

		public interface Endgame {
			public static int 
				END_GAME_SPARK = 4;
		}

		public interface Pneumatics {
			public static final int 
				INTAKE_SOLENOID = 2, 
				COMPRESSOR_ID = 7, 
				ENDGAME_PISTON = 0, 
				ENDGAME_PISTOON =1;
		}

		public interface Sensors {
			public static final int 
				LEFT_TRIG = 1, 
				LEFT_ECHO = 0, 
				RIGHT_TRIG = 3, 
				RIGHT_ECHO = 2, 
				INTAKE_TRIG = 5,
				INTAKE_ECHO = 4,

				INTAKE_BUTTON_ID = 9, 
				ENDGAME_SWITCH_ID = 6,

				LEFT_SONAR = 0,
				RIGHT_SONAR = 1,
				INTAKE_SONAR = 2;
		}
	}
}