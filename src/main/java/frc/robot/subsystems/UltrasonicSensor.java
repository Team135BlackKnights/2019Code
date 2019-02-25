package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.Robot.Sensors;
import edu.wpi.first.wpilibj.*;

public class UltrasonicSensor extends Subsystem {

	public static Ultrasonic 
	leftSonar = new Ultrasonic(Sensors.LEFT_TRIG, Sensors.LEFT_ECHO),
	rightSonar = new Ultrasonic(Sensors.RIGHT_TRIG, Sensors.RIGHT_ECHO),
	intakeSonar = new Ultrasonic(Sensors.INTAKE_TRIG, Sensors.INTAKE_ECHO);
	public static Ultrasonic [] sonars = {leftSonar, rightSonar, intakeSonar};
	private static UltrasonicSensor instance;

	public UltrasonicSensor() {
		leftSonar.setAutomaticMode(true);
		rightSonar.setAutomaticMode(true);
		intakeSonar.setAutomaticMode(true);
	}

	public double getSonarIn(int sonarID)
	{
		return sonars[sonarID].getRangeInches();
	}
	public boolean isBallinIntake() {
		return (getSonarIn(Sensors.INTAKE_SONAR) < 3 && getSonarIn(Sensors.INTAKE_SONAR) != 0);
	}

	public void periodic() 
	{
		SmartDashboard.putNumber("Left Sonar Inches: ", getSonarIn(Sensors.LEFT_SONAR));
		SmartDashboard.putNumber("Right Sonar Inches: ", getSonarIn(Sensors.RIGHT_SONAR));
		SmartDashboard.putNumber("Intake Sonar Inches: ", getSonarIn(Sensors.INTAKE_SONAR));
		SmartDashboard.putBoolean("Is Ball in Intake:", isBallinIntake());
	}

	public static UltrasonicSensor getInstance() {if (instance == null) {instance = new UltrasonicSensor();}return instance;}

	@Override
	protected void initDefaultCommand() {}
}
