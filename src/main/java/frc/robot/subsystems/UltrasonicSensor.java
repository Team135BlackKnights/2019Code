package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.*;

/**
 *
 */
public class UltrasonicSensor extends Subsystem {



    public Ultrasonic rightSonar = new Ultrasonic(RobotMap.Robot.Sensors.RIGHT_SONAR_TRIG_PORT, RobotMap.Robot.Sensors.RIGHT_SONAR_ECHO_PORT);
	public Ultrasonic leftSonar = new Ultrasonic(RobotMap.Robot.Sensors.LEFT_SONAR_TRIG_PORT, RobotMap.Robot.Sensors.LEFT_SONAR_ECHO_PORT);
	public Ultrasonic intakeSonar = new Ultrasonic(RobotMap.Robot.Sensors.INTAKE_SONAR_TRIG_PORT, RobotMap.Robot.Sensors.INTAKE_SONAR_ECHO_PORT);

    private static UltrasonicSensor instance;
    
    public static UltrasonicSensor getInstance()
    {
    	if (instance == null)
    	{
    		instance = new UltrasonicSensor();
    	}
    	return instance;
    }
    
    public UltrasonicSensor()
    {
    	rightSonar.setAutomaticMode(true);
		leftSonar.setAutomaticMode(true);
		intakeSonar.setAutomaticMode(true);
    	
    }
    
	public double getRightSonarINCHES() {
		double RightSonarInches = rightSonar.getRangeInches();
		SmartDashboard.putNumber("Right Sonar Inches: ", RightSonarInches);
		return RightSonarInches;
	}
	public double getRightSonarFEET()
	{
		double RightSonarFET = rightSonar.getRangeInches()/12;
		SmartDashboard.putNumber("Right Sonar Feet ", RightSonarFET);
		return RightSonarFET;
	}	

	public double getLeftSonarINCHES() {
		double LeftSonarDistance = leftSonar.getRangeInches();
		SmartDashboard.putNumber("Left Sonar Distance: ", LeftSonarDistance);
		return LeftSonarDistance;
	}
	public double getLeftSonarFEET()
	{
		double LeftSonarFET = leftSonar.getRangeInches()/12;
		SmartDashboard.putNumber("Left Sonar Feet", LeftSonarFET);
		return LeftSonarFET;
	}
	
	public double getIntakeSonarValue() {
		double IntakeSonarInches = intakeSonar.getRangeInches();
		SmartDashboard.putNumber("Intake Sonar Inches: ", IntakeSonarInches);
		return IntakeSonarInches;
	}

	public boolean isBallinIntake()
	{
		return (getIntakeSonarValue() < 3);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void periodic()
    {
		SmartDashboard.putBoolean("Ball in Intake", isBallinIntake());
		getRightSonarINCHES();
		getLeftSonarINCHES();
		getLeftSonarFEET();
		getRightSonarFEET();
    
    }

}
