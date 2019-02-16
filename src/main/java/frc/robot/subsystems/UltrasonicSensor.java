package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.Robot.Sensors;
import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {

	public static Ultrasonic leftSonar, rightSonar, intakeSonar;

	private static UltrasonicSensor instance;

	public UltrasonicSensor() {
		leftSonar = new Ultrasonic(Sensors.LEFT_TRIG, Sensors.LEFT_ECHO);
		rightSonar = new Ultrasonic(Sensors.RIGHT_TRIG, Sensors.RIGHT_ECHO);
		intakeSonar = new Ultrasonic(Sensors.INTAKE_TRIG, Sensors.INTAKE_ECHO);

		leftSonar.setAutomaticMode(true);
		rightSonar.setAutomaticMode(true);
		intakeSonar.setAutomaticMode(true);

	}

	public double getLeftSonarIn() {
		return leftSonar.getRangeInches();
	}

	public double getLeftSonarFt() {
		return leftSonar.getRangeInches() / 12;
	}

	public double getRightSonarIn() {
		return rightSonar.getRangeInches();
	}

	public double getRightSonarFt() {
		return rightSonar.getRangeInches() / 12;
	}

	public double getIntakeSonarIn() {
		return intakeSonar.getRangeInches();
	}

	public double getIntakeSonarFt() {
		return intakeSonar.getRangeInches() / 12;
	}

	public boolean isBallinIntake() {
		return (getIntakeSonarIn() < 3 && getIntakeSonarIn() != 0);
	}

	public void periodic() {
		SmartDashboard.putNumber("Left Sonar Inches: ", getLeftSonarIn());
		SmartDashboard.putNumber("Left Sonar Feet: ", getLeftSonarFt());
		SmartDashboard.putNumber("Right Sonar Inches: ", getRightSonarIn());
		SmartDashboard.putNumber("Right Sonar Feet: ", getRightSonarFt());
		SmartDashboard.putNumber("Intake Sonar Inches: ", getIntakeSonarIn());
		SmartDashboard.putNumber("Intake Sonar Feet: ", getIntakeSonarFt());
		SmartDashboard.putBoolean("Is Ball in Intake:", isBallinIntake());
	}

	public static UltrasonicSensor getInstance() {
		if (instance == null) {
			instance = new UltrasonicSensor();
		}
		return instance;
	}

	@Override
	protected void initDefaultCommand() {
	}
}
