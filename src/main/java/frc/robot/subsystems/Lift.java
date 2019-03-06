package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MotorCommands.*;
import frc.robot.commands.Sensors.*;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Robot.*;

public class Lift extends Subsystem {
	public static Lift instance;
	public boolean isCompBot;
	public TalonSRX LeftLiftTalon, RightLiftTalon;
	public CANSparkMax LeftLiftSpark, RightLiftSpark;

	public Encoder encoder;

	public double kP = 1.0;
	public static double setpoint = 0;
	public static int setpointIndex = 0;
	public double motorValue = 0;

	private Lift() {
		
			LeftLiftSpark = new CANSparkMax(KLift.LIFT_LEFT_SPARK, MotorType.kBrushless);
			RightLiftSpark = new CANSparkMax(KLift.LIFT_RIGHT_SPARK, MotorType.kBrushless);

			LeftLiftSpark.setIdleMode(IdleMode.kBrake);
			RightLiftSpark.setIdleMode(IdleMode.kBrake);
		
		encoder = new Encoder(KLift.ENCODER_A, KLift.ENCODER_B);
	}

	public double getEncoderVelocity() {
		return encoder.getRate();
	}

	public double getEncoderPosition() {
		return encoder.get();
	}

	public void resetEncoders() {
		encoder.reset();
	}

	public void RunLift(double power) {
			LeftLiftSpark.set(power);
			RightLiftSpark.set(power);
		
	}
	public void setToPosition() {
		double encoderPosition = getEncoderPosition();
		double targetPosition = setpoint;
		SmartDashboard.putNumber("targetposition", targetPosition);
		double direction = (targetPosition - encoderPosition) < 0 ? -1.0 : 1.0;
		setpoint = setpoint < -20 ? -20 : setpoint;
		double error = targetPosition - encoderPosition;
		if (Math.abs(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK)) > 0) 
		{
			RunLift(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK));
			setpoint = getEncoderPosition();
		} 
		else if ((Math.abs(error) > 15)) {
			double dividevalue = Math.abs(targetPosition) < 1.0 ? 1.0 : Math.abs(targetPosition);
			dividevalue /= Math.abs(error) > 30 ? 2.0 : 1.0;
			SmartDashboard.putNumber("RunLiftValue", kP * (error) / dividevalue + 0.35 * direction);
			SmartDashboard.putNumber("Error", (error) / dividevalue);
			RunLift(kP * (error) / dividevalue + 0.35 * direction);
			encoderPosition = getEncoderPosition();
			error = targetPosition - encoderPosition;
		} 
		else 
		{
			RunLift(0);
		}
	}

	public void periodic() {
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		Robot.driveTrain.chassis.feedWatchdog();
		SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());

		SmartDashboard.putData("Move Lift 0(9)", new RunLift(0));
		SmartDashboard.putData("Move Lift 50(10)", new RunLift(1));
		SmartDashboard.putData("Move Lift 100(11)", new RunLift(2));
		setToPosition();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift(-1));
	}

	public static Lift getInstance() {if (instance == null) {instance = new Lift();}return instance;}
}
