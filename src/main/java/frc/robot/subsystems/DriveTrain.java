package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.RobotMap.Robot.*;
import frc.robot.commands.MotorCommands.*;

public class DriveTrain extends Subsystem {
	public static DriveTrain instance;
	public boolean isCompBot = Robot.isComp;

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	public static int Forward, Backward, Left, Right;
	
	public DriveTrain() {
		
		ConfigSpark(frontLeftMotor);
		ConfigSpark(frontRightMotor);
		ConfigSpark(rearLeftMotor);
		ConfigSpark(rearRightMotor);
	}

	public void cartesianDrive(double x, double y, double z) {
		chassis.driveCartesian(-x, y, z);
	}

	public void cartesianDrive(double x, double y, double z, double gyro) {
		chassis.driveCartesian(x, y, z, gyro);
	}

	public void ConfigSpark(CANSparkMax spark)
	{
	spark.setIdleMode(IdleMode.kBrake);
	spark.setInverted(false);
	}
	public void DriveDirection(int direction)
	{	
		if (direction == Forward)
		{
			cartesianDrive(0,.3, 0);
		}
		else if (direction == Right)
		{
			cartesianDrive(.3, 0, 0);
		}
		else if (direction == Backward)
		{
			cartesianDrive(0,-.3,0);
		}
		else if (direction == Left)
		{
			cartesianDrive(-.3, 0, 0);
		}
	}

	

	public void getNumbers()
	{
		double frontLeftTemp = frontLeftMotor.getMotorTemperature()*9/5 +32;
		double frontRightTemp = frontRightMotor.getMotorTemperature()*9/5 +32;
		double rearLeftTemp = rearLeftMotor.getMotorTemperature()*9/5 +32;
		double rearRightTemp = rearRightMotor.getMotorTemperature()*9/5 +32;

		SmartDashboard.putNumber("Front Left Motor Temperature ", frontLeftTemp);
		SmartDashboard.putNumber("Front Right Motor Temperature ", frontRightTemp);
		SmartDashboard.putNumber("Rear Left Motor Temperature ", rearLeftTemp);
		SmartDashboard.putNumber("Rear Right Motor Temperature ", rearRightTemp);

	}

	public void periodic()
	{
		getNumbers();
	}

	public void StopMotors() {
		cartesianDrive(0, 0, 0);
	}

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}

}