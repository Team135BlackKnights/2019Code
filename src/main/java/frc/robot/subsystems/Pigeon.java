package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.*;
import frc.robot.commands.Sensors.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pigeon extends Subsystem {
	private static Pigeon instance;
	public double initAngle = 0.0;
	public AHRS navx;

	public PigeonIMU pigeon;

	public Pigeon() {
		pigeon = new PigeonIMU(0);
		initAngle = pigeon.getFusedHeading();
	}

	public void reset() {
		pigeon.setYaw(0);
		pigeon.setFusedHeading(0);
		initAngle = 0;
	}

	public double getFusedAngle() {
		double angle = ((pigeon.getFusedHeading() + initAngle) % 360.0);
		return angle < 0 ? 360 + angle : angle;
	}

	public void periodic() {
		SmartDashboard.putNumber("Robot Angle", Robot.pigeon.getFusedAngle());
		SmartDashboard.putData("Reset Navx", new ResetGyro());
		SmartDashboard.putNumber("Pigeon Angle", pigeon.getFusedHeading());
	}

	public static Pigeon getInstance() {
		if (instance == null) {
			instance = new Pigeon();
		}
		return instance;
	}

	@Override
	protected void initDefaultCommand() {
	}
}