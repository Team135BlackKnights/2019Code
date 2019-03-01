package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.CANSparkMax.IdleMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.RobotMap.Robot.*;
import frc.robot.commands.MotorCommands.*;
import frc.robot.util.PIDIn;
import frc.robot.util.PIDOut;

public class DriveTrain extends Subsystem {
	public static DriveTrain instance;
	public boolean isCompBot = Robot.isComp;

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
	
	PIDIn gyro;
	PIDOut turner = new PIDOut();
	PIDController turnController;
	
	public DriveTrain() {
		if (isCompBot)
		{
			gyro = new PIDIn(() -> Robot.navx.getFusedAngle(), PIDSourceType.kDisplacement);
		}
		else {
			gyro = new PIDIn(() -> Robot.pigeon.getFusedAngle(), PIDSourceType.kDisplacement);
		}
		turnController = new PIDController(0.04f, .01f, 0.01f, gyro, turner);
		turnController.setInputRange(0.0, 360.0f);
		turnController.setOutputRange(-.5, .5);
		turnController.setAbsoluteTolerance(10);
		turnController.setContinuous(true);
		
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

	public double turnToAnglePID(double angleSetpoint) {
		turnController.setSetpoint(angleSetpoint);
		turnController.enable();
		double rotationRate = turnController.get();
		SmartDashboard.putNumber("Drivetrain PID Rate", rotationRate);
		return rotationRate;
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