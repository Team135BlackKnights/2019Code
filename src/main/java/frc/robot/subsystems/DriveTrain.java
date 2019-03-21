package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax.IdleMode;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap.Robot.*;
import frc.robot.commands.MotorCommands.*;

public class DriveTrain extends Subsystem {
	public static DriveTrain instance;

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	public DriveTrain() {ConfigSparks();}
	public void ConfigSpark(CANSparkMax spark)
	{
		spark.restoreFactoryDefaults();
		spark.setIdleMode(IdleMode.kBrake);
		spark.setInverted(false);
	}
	public void ConfigSparks()
	{
		ConfigSpark(frontLeftMotor);
		ConfigSpark(frontRightMotor);
		ConfigSpark(rearLeftMotor);
		ConfigSpark(rearRightMotor);
	}
	
	public void cartesianDrive(double x, double y, double z) 
	{
		chassis.driveCartesian(x, y, z);
	}

	public void StopMotors() {cartesianDrive(0, 0, 0);}

	public void initDefaultCommand() {setDefaultCommand(new DriveWithJoysticks());}

	public static DriveTrain getInstance() {if (instance == null) {instance = new DriveTrain();}return instance;}
}