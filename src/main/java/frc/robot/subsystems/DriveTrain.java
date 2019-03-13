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

import edu.wpi.first.wpilibj.drive.*;


public class DriveTrain extends Subsystem {
	public static DriveTrain instance;
	public boolean isCompBot = Robot.isComp;
	public int frontLeft, rearLeft, frontRight, rearRight;

	public CANSparkMax frontLeftMotor = new CANSparkMax(KDrivetrain.FRONT_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearLeftMotor = new CANSparkMax(KDrivetrain.REAR_LEFT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax frontRightMotor = new CANSparkMax(KDrivetrain.FRONT_RIGHT_SPARK_ID, MotorType.kBrushless);
	public CANSparkMax rearRightMotor = new CANSparkMax(KDrivetrain.REAR_RIGHT_SPARK_ID, MotorType.kBrushless);

	public CANEncoder frontLeftEncoder, rearLeftEncoder, frontRightEncoder, rearRightEncoder;

	public CANPIDController frontLeftPID, rearLeftPID, frontRightPID, rearRightPID;

	public double frontLeftSetpoint, rearLeftSetpoint, frontRightSetpoint, rearRightSetpoint;

	public double
	FL_P, FL_I, FL_D, FR_P, FR_I, FR_D,
	RL_P, RL_I, RL_D, RR_P, RR_I, RR_D,
	kMax, kMin, maxRPM;
	public double countsPerRev = KDrivetrain.COUNTS_PER_REV;
	public int wheelDiameter = KDrivetrain.WHEEL_DIAMETER;
	public double distance; 
	MecanumDrive chassis = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	public static int Forward, Backward, Left, Right;
	
	public DriveTrain() {
		ConfigSparks();
		//InitPID();
	}
	public void ConfigSpark(CANSparkMax spark)
	{
		spark.setIdleMode(IdleMode.kBrake);
		spark.setInverted(false);
		spark.restoreFactoryDefaults();
	}
	public void ConfigSparks()
	{
		ConfigSpark(frontLeftMotor);
		ConfigSpark(frontRightMotor);
		ConfigSpark(rearLeftMotor);
		ConfigSpark(rearRightMotor);
	}
	public void InitPID()
	{
		frontLeftEncoder = frontLeftMotor.getEncoder();
		rearLeftEncoder = rearLeftMotor.getEncoder();
		frontRightEncoder = frontRightMotor.getEncoder();
		rearRightEncoder = rearRightMotor.getEncoder();

		frontLeftPID = frontLeftMotor.getPIDController();
		rearLeftPID = rearLeftMotor.getPIDController();
		frontRightPID = frontRightMotor.getPIDController();
		rearRightPID = rearRightMotor.getPIDController();

		FL_P = 8.59; FR_P = 8.59;
		FL_I = -3.28; FR_I = -3.28;
		FL_D = 0;	 FR_D = 0;

		RL_P = 8.59; RR_P = 8.59;
		RL_I = -3.28; RR_I = -3.28;
		RL_D = 0;	 RR_D = 0;
		
		kMax =1; kMin =-1;
		maxRPM = 5700;

		frontLeftPID.setP(FL_P); frontRightPID.setP(FL_P);
		frontLeftPID.setI(FL_I); frontRightPID.setI(FL_I);
		frontLeftPID.setD(FL_D); frontRightPID.setD(FL_D);

		rearLeftPID.setP(FL_P); rearRightPID.setP(FL_P);
		rearLeftPID.setI(FL_I); rearRightPID.setI(FL_I);
		rearLeftPID.setD(FL_D); rearRightPID.setD(FL_D);

		frontLeftPID.setOutputRange(kMin, kMax);
		rearLeftPID.setOutputRange(kMin, kMax);
		frontRightPID.setOutputRange(kMin, kMax);
		rearRightPID.setOutputRange(kMin, kMax);

	}
	
	public void cartesianDrive(double x, double y, double z) {
		chassis.driveCartesian(x, y, z);
	}

	public void cartesianDrive(double x, double y, double z, double gyro)
	 {
		chassis.driveCartesian(x, y, z, gyro);
	}
	public void cartesianPID(double x, double y , double z)
	{
			frontLeft = 0; rearLeft = 1; frontRight = 2; rearRight = 3;
			Vector2d input = new Vector2d(y, x);

			double[] wheelSpeeds = new double[4];
			wheelSpeeds[frontLeft] = -input.x + input.y + z;
			wheelSpeeds[rearLeft] = input.x + input.y + z;
			wheelSpeeds[frontRight] = -input.x + input.y + z;
			wheelSpeeds[rearRight] = input.x + input.y + z;

			normalize(wheelSpeeds);

			frontLeftSetpoint = wheelSpeeds[frontLeft] * maxRPM;
			rearLeftSetpoint = -wheelSpeeds[rearLeft] * maxRPM;
			frontRightSetpoint = wheelSpeeds[frontRight] * maxRPM;
			rearRightSetpoint = -wheelSpeeds[rearRight] * maxRPM;

			frontLeftPID.setReference(frontLeftSetpoint, ControlType.kVelocity);
			rearLeftPID.setReference(rearLeftSetpoint, ControlType.kVelocity);
			frontRightPID.setReference(frontRightSetpoint, ControlType.kVelocity);
			rearRightPID.setReference(rearRightSetpoint, ControlType.kVelocity);	
	 }

	 public void normalize(double[] wheelSpeeds) 
	 {
	 double maxMagnitude = Math.abs(wheelSpeeds[0]);
	 for (int i = 1; i < wheelSpeeds.length; i++) {
	   double temp = Math.abs(wheelSpeeds[i]);
	   if (maxMagnitude < temp) {
		 maxMagnitude = temp;
	   }
	 }
	 if (maxMagnitude > 1.0) {
	   for (int i = 0; i < wheelSpeeds.length; i++) {
		 wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
	   }
	 }
	}
	
	public void DriveDirection(int direction, double power)
	{	
		if (direction == Forward)
		{
			cartesianDrive(0,power, 0);
		}
		else if (direction == Right)
		{
			cartesianDrive(power, 0, 0);
		}
		else if (direction == Backward)
		{
			cartesianDrive(0,-power,0);
		}
		else if (direction == Left)
		{
			cartesianDrive(-power, 0, 0);
		}
	}

	public void driveToPID(double distance)
	{
		double wheelRotations = distance/wheelDiameter;
		frontLeftPID.setReference(wheelRotations, ControlType.kPosition);
		rearLeftPID.setReference(wheelRotations, ControlType.kPosition);
		frontRightPID.setReference(wheelRotations, ControlType.kPosition);
		rearRightPID.setReference(wheelRotations, ControlType.kPosition);
	}

	public void getMotorTemps()
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
	public void getEncoderReadouts() 
	{
		double frontLeftVel = frontLeftEncoder.getVelocity()/countsPerRev*wheelDiameter;
		double rearLeftVel = rearLeftEncoder.getVelocity()/countsPerRev*wheelDiameter;
		double frontRightVel = frontRightEncoder.getVelocity()/countsPerRev*wheelDiameter;
		double rearRightVel = rearRightEncoder.getVelocity()/countsPerRev*wheelDiameter;

		double frontLeftPos = frontLeftEncoder.getPosition()/countsPerRev*wheelDiameter;
		double rearLeftPos = rearLeftEncoder.getPosition()/countsPerRev*wheelDiameter;
		double frontRightPos = frontRightEncoder.getPosition()/countsPerRev*wheelDiameter;
		double rearRightPos = rearRightEncoder.getPosition()/countsPerRev*wheelDiameter;

		SmartDashboard.putNumber("Front Left Velocity: ", frontLeftVel);
		SmartDashboard.putNumber("Rear Left Velocity: ", rearLeftVel);
		SmartDashboard.putNumber("Front Right Velocity: ", frontRightVel);
		SmartDashboard.putNumber("Rear Right Velocity: ", rearRightVel);

		SmartDashboard.putNumber("Front Left Position: ", frontLeftPos);
		SmartDashboard.putNumber("Rear Left Position: ", rearLeftPos);
		SmartDashboard.putNumber("Front Right Position: ", frontRightPos);
		SmartDashboard.putNumber("Rear Right Position: ", rearRightPos);

	}

	public void SmartDashPID()
	{
		SmartDashboard.putNumber("Front Left P: ", FL_P);
		SmartDashboard.putNumber("Front Left I: ", FL_I);
		SmartDashboard.putNumber("Front Left D: ", FL_D);

		SmartDashboard.putNumber("Rear Left P: ", RL_P);
		SmartDashboard.putNumber("Rear Left I: ", RL_I);
		SmartDashboard.putNumber("Rear Left D: ", RL_D);
		
		SmartDashboard.putNumber("Front Right P: ", FR_P);
		SmartDashboard.putNumber("Front Right I: ", FR_I);
		SmartDashboard.putNumber("Front Right D: ", FR_D);

		SmartDashboard.putNumber("Rear Right P: ", RR_P);
		SmartDashboard.putNumber("Rear Right I: ", RR_I);
		SmartDashboard.putNumber("Rear Right D: ", RR_D);

		double FLP = SmartDashboard.getNumber("Front Left P: ", 0);
		double FLI = SmartDashboard.getNumber("Front Left I: ", 0);
		double FLD = SmartDashboard.getNumber("Front Left D: ", 0);
		
		double RLP = SmartDashboard.getNumber("Rear Left P: ", 0);
		double RLI = SmartDashboard.getNumber("Rear Left I: ", 0);
		double RLD = SmartDashboard.getNumber("Rear Left D: ", 0);
		
		double FRP = SmartDashboard.getNumber("Front Right P: ", 0);
		double FRI = SmartDashboard.getNumber("Front Right I: ", 0);
		double FRD = SmartDashboard.getNumber("Front Right D: ", 0);

		double RRP = SmartDashboard.getNumber("Rear Right P: ", 0);
		double RRI = SmartDashboard.getNumber("Rear Right I: ", 0);
		double RRD = SmartDashboard.getNumber("Rear Right D: ", 0);

		if((FLP !=FL_P)) {frontLeftPID.setP(FLP); FL_P = FLP;}
		if((FLI !=FL_I)) {frontLeftPID.setP(FLI); FL_I = FLI;}
		if((FLD !=FL_D)) {frontLeftPID.setP(FLD); FL_D = FLD;}

		if((RLP !=RL_P)) {rearLeftPID.setP(RLP); RL_P = RLP;}
		if((RLI !=RL_I)) {rearLeftPID.setP(RLI); RL_I = RLI;}
		if((RLD !=RL_D)) {rearLeftPID.setP(RLD); RL_D = RLD;}

		if((FRP !=FR_P)) {frontRightPID.setP(FRP); FR_P = FRP;}
		if((FRI !=FR_I)) {frontRightPID.setP(FRI); FR_I = FRI;}
		if((FRD !=FR_D)) {frontRightPID.setP(FRD); FR_D = FRD;}

		if((RRP !=RR_P)) {rearRightPID.setP(RRP); RR_P = RRP;}
		if((RRI !=RR_I)) {rearRightPID.setP(RRI); RR_I = RRI;}
		if((RRD !=RR_D)) {rearRightPID.setP(RRD); RR_D = RRD;}

	
	}

	public void SmartdashDrive()
	{
		distance = SmartDashboard.getNumber("Distance To Drive:", 0);
		SmartDashboard.putNumber("Distance to Drive: ", distance);

		driveToPID(distance);
	}

	public void periodic()
	{
		getMotorTemps();
		//getEncoderReadouts();
		//SmartDashPID();
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