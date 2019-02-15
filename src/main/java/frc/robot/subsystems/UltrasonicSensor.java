package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.Robot.Sensors;
import frc.robot.commands.Sensors.ChangeUltrasonic;
import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {

	public static Ultrasonic sonar;

    private static UltrasonicSensor instance;
    public UltrasonicSensor()
    {
		sonar = new Ultrasonic(Sensors.INTAKE_TRIG, Sensors.INTAKE_ECHO);
		sonar.setAutomaticMode(true);
	}
	
	public void changeSensor(int trig, int echo)
	{
		sonar = new Ultrasonic(trig, echo);
	}
    
	public double getSonarIn() {
		return sonar.getRangeInches();
	}
	public double getSonarFt()
	{
		return sonar.getRangeInches()/12;
	}	

	public boolean isBallinIntake()
	{
		return (getSonarIn() < 3);
	}
    
    public void periodic()
    {
		SmartDashboard.putNumber("Right Sonar Inches: ", getSonarIn());
		SmartDashboard.putNumber("Right Sonar Feet ", getSonarFt());
		SmartDashboard.putData("Set Sonar Intake ", new ChangeUltrasonic(true));
		SmartDashboard.putData("Set Sonar Drivetrain ", new ChangeUltrasonic(false));
	}
	
	public static UltrasonicSensor getInstance(){if (instance == null){instance = new UltrasonicSensor();}return instance;}
	@Override
	protected void initDefaultCommand() {}
}
