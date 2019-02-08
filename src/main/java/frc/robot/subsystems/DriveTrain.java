package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap.Robot.KDrivetrain;
import frc.robot.commands.DriveWithJoysticks;

public class DriveTrain extends Subsystem  
{	
	public static DriveTrain instance;	

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	public void CartesianDrive(double x, double y, double z)
	{
		chassis.driveCartesian(x, y, z);
	}
	public void CartesianDrive(double x, double y, double z, double gyro)
	{
		chassis.driveCartesian(x, y, z, gyro);
	}
		
	public static enum DirectionToTurn {
		Left, Right
	}

    public void TurnDriveTrain(double driveTrainMotorPower, DirectionToTurn directionToTurn)
    {	
    	if (directionToTurn == DirectionToTurn.Left)
    	{
    		this.CartesianDrive(0, 0, driveTrainMotorPower, 0);
    	}
    	else if (directionToTurn == DirectionToTurn.Right)
    	{
    		this.CartesianDrive(0, 0, -driveTrainMotorPower, 0);
    	}
    	return;
	}
	
    public void TurnDriveTrain(double driveTrainMotorPower)
    {
    	this.TurnDriveTrain(driveTrainMotorPower, DirectionToTurn.Right);
	}
	
	public void DriveStraightWVision(double motorPower, double xValue, double pValue) 
	{
		CartesianDrive(0, motorPower, xValue * pValue * .75, 0);
		return;
	}
	
	public void StopMotors()
	{
		CartesianDrive(0, 0, 0);
	}

	public static DriveTrain getInstance() {if (instance == null) {instance = new DriveTrain();}return instance;}
	public void initDefaultCommand() {setDefaultCommand(new DriveWithJoysticks());}
}