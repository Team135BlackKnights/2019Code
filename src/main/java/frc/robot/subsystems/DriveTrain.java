package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.RobotMap.Robot.*;
import frc.robot.commands.MotorCommands.*;

public class DriveTrain extends Subsystem
{
	public static DriveTrain instance;
	public boolean isCompBot = Robot.isComp;
	public int frontLeft, rearLeft, frontRight, rearRight;

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	public CANEncoder frontLeftEncoder, rearLeftEncoder, frontRightEncoder, rearRightEncoder;

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	public DriveTrain()
	{
		ConfigSpark(frontLeftMotor);
		ConfigSpark(frontRightMotor);
		ConfigSpark(rearLeftMotor);
		ConfigSpark(rearRightMotor);
	}
	public void ConfigSpark(CANSparkMax spark)
	{
		spark.setIdleMode(IdleMode.kBrake);
		spark.setInverted(false);
	}
	
	public void cartesianDrive(double x, double y, double z)
	{
		chassis.driveCartesian(x, y, z);
	}

	public void cartesianDrive(double x, double y, double z, double gyro)
	{
		chassis.driveCartesian(x, y, z, gyro);
	}

	public void periodic()
	{
		SmartDashboard.putNumber("Front Left Motor Temperature ", frontLeftMotor.getMotorTemperature()*9/5 +32);
		SmartDashboard.putNumber("Front Right Motor Temperature ", frontRightMotor.getMotorTemperature()*9/5 +32);
		SmartDashboard.putNumber("Rear Left Motor Temperature ", rearLeftMotor.getMotorTemperature()*9/5 +32);
		SmartDashboard.putNumber("Rear Right Motor Temperature ", rearRightMotor.getMotorTemperature()*9/5 +32);
	}

	public void StopMotors()
	{
		cartesianDrive(0, 0, 0);
	}

	public void initDefaultCommand() {setDefaultCommand(new DriveWithJoysticks());}

	public static DriveTrain getInstance() {if (instance == null) {instance = new DriveTrain();}return instance;}
}