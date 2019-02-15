package frc.robot.commands.DriverAssistance.Parallel;

import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LineUp extends Command {

	private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];

	private boolean targetExists = false;
	private final double TURNING_MOTOR_POWER = .1;
	private final double X_THRESHOLD_TO_STOP_TURNING = 10;
	boolean TurnLeft;

	private int _pipeline;

	public LineUp(boolean Turnleft, int pipeline) {
		requires(Robot.limelight);
		requires(Robot.driveTrain);
		this._pipeline = pipeline;
	}

	protected void initialize() {
		Robot.limelight.SetCameraPipeline(_pipeline);
		Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
		if (_pipeline == 2) {
			Robot.limelight.SetLEDMode(Limelight.LED_OFF);

		} else {
			Robot.limelight.SetLEDMode(Limelight.LED_ON);
		}
		targetExists = false;
	}

	protected void execute() {
		targetExists = Robot.limelight.isTargetsExist();
		SmartDashboard.putBoolean("doestargetexist", targetExists);
		limelightData = Robot.limelight.GetLimelightData();

		if (targetExists == false) {
			if (TurnLeft) {
				Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Left);
			} else {
				Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Right);
			}

			if (targetExists && limelightData[Limelight.HORIZONTAL_OFFSET] > 0.0) // If Target is to the Right, turn to
																					// the Right
			{
				Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Right);
			} else if (targetExists && limelightData[Limelight.HORIZONTAL_OFFSET] < 0.0) // If Target is to the Left,
																							// turn to the Left
			{
				Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Left);
			} else {
				Robot.driveTrain.StopMotors();
			}
		}
	}

	protected boolean isFinished() {
		return (limelightData[Limelight.HORIZONTAL_OFFSET] <= X_THRESHOLD_TO_STOP_TURNING
				&& limelightData[Limelight.HORIZONTAL_OFFSET] >= -X_THRESHOLD_TO_STOP_TURNING
				&& limelightData[Limelight.HORIZONTAL_OFFSET] != 0);
	}

	protected void end() {
		Robot.driveTrain.StopMotors();
	}

	protected void interrupted() {
		this.end();
	}
}
