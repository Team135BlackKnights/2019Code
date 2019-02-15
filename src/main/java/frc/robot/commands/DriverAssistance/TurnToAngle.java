package frc.robot.commands.DriverAssistance;

import frc.robot.Robot;
import frc.robot.util.PIDIn;
import frc.robot.util.PIDOut;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends TimedCommand {

	private double _initSignError = 0;

	private PIDController angleController;
	private PIDIn pidIn;
	private PIDOut pidOut;

	public TurnToAngle(double angle, double timeout) {
		super(timeout);
		requires(Robot.driveTrain);

		pidIn = new PIDIn(() -> Robot.navx.getFusedAngle(), PIDSourceType.kDisplacement);
		pidOut = new PIDOut();

		angleController = new PIDController(1 / 90.0, 0, 0, pidIn, pidOut);

		angleController.setInputRange(0, 360);
		angleController.setContinuous();
		angleController.setOutputRange(-1, 1);

		angleController.setSetpoint(angle);

		this._initSignError = Math.signum(angleController.getError());

	}

	// Called once when the command executes
	protected void initialize() {
		angleController.enable();

	}

	protected void execute() {
		Robot.driveTrain.cartesianDrive(0, 0, pidOut.output, 0);
		SmartDashboard.putString("NavXisturning", "Robot is Turning");
	}

	protected boolean isFinished() {
		return this._initSignError != Math.signum(angleController.getError())
				|| !DriverStation.getInstance().isAutonomous();
	}

	protected void interupted() {
		Robot.driveTrain.StopMotors();
	}

	protected void end() {
		Robot.driveTrain.StopMotors();
	}

}