package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.RobotMap.Robot.KDrivetrain;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.util.PIDIn;
import frc.robot.util.PIDOut;

public class DriveTrain extends Subsystem  
{	
	public static DriveTrain instance;	

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	PIDOut turner = new PIDOut();
	PIDIn navx = new PIDIn( () -> Robot.navx.getFusedAngle(), PIDSourceType.kDisplacement);
	
	PIDController turnController = new PIDController(0.01f, 0, 0, navx, turner);

	public DriveTrain()
	{
		turnController.setInputRange(0.0, 360.0f);
		turnController.setOutputRange(-0.3, 0.3);
		turnController.setAbsoluteTolerance(5);
		turnController.setContinuous(true);
	}

	public void cartesianDrive(double x, double y, double z)
	{
		chassis.driveCartesian(-x, y, z);
	}
	public void cartesianDrive(double x, double y, double z, double gyro)
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
    		this.cartesianDrive(0, 0, driveTrainMotorPower, 0);
    	}
    	else if (directionToTurn == DirectionToTurn.Right)
    	{
    		this.cartesianDrive(0, 0, -driveTrainMotorPower, 0);
    	}
    	return;
	}
	
    public void TurnDriveTrain(double driveTrainMotorPower)
    {
    	this.TurnDriveTrain(driveTrainMotorPower, DirectionToTurn.Right);
	}
	
	public void DriveStraightWVision(double motorPower, double xValue, double pValue) 
	{
		cartesianDrive(0, motorPower, xValue * pValue * .75, 0);
		return;
	}

	public void turnToAnglePID(double x, double y, double z, double angleSetpoint)
	{
		turnController.setSetpoint(angleSetpoint);
		turnController.enable();
		double rotationRate = turnController.get();
		SmartDashboard.putNumber("Drivetrain PID Rate", rotationRate);
		cartesianDrive(x, y, z + rotationRate);
	}
	
	public void StopMotors()
	{
		cartesianDrive(0, 0, 0);
	}

	public static DriveTrain getInstance() {if (instance == null) {instance = new DriveTrain();}return instance;}
	public void initDefaultCommand() {setDefaultCommand(new DriveWithJoysticks());}
}