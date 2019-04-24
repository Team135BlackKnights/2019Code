package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.MotorCommands.*;
import frc.robot.commands.Sensors.*;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Robot.*;

public class Lift extends Subsystem {
	public static Lift instance;
	public CANSparkMax LeftLiftSpark, RightLiftSpark;

	public CANEncoder encoder;

	public static double setpoint = 0;
	public static int setpointIndex = 0;

	private Lift() { // Here we are configuring everything in the lift subsystem 
		LeftLiftSpark = new CANSparkMax(KLift.LIFT_LEFT_SPARK, MotorType.kBrushless);
		RightLiftSpark = new CANSparkMax(KLift.LIFT_RIGHT_SPARK, MotorType.kBrushless);

		LeftLiftSpark.setIdleMode(IdleMode.kBrake);
		RightLiftSpark.setIdleMode(IdleMode.kBrake);

		encoder = new CANEncoder(LeftLiftSpark);

		setpoint = getEncoderPosition();
	}

	public double getEncoderVelocity() {
		return encoder.getVelocity();
	}

	public double getEncoderPosition() {
		return encoder.getPosition();
	}

	public void resetEncoders() {
		encoder.setPosition(0);
	}

	public void RunLift(double power) {
		LeftLiftSpark.set(power);
		RightLiftSpark.set(power);
	}

	public void setToPosition() {
		// This is a command that when it is run in a loop, will keep the lift at 
		//the same position until told other wise, and allows it to be set to a position
		double encoderPosition = getEncoderPosition();
		double targetPosition = setpoint;

		double direction = (targetPosition - encoderPosition) < 0 ? -1.0 : 1.0;
				
		double error = targetPosition - encoderPosition;

		if (Math.abs(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK)) > 0) 
		{
			RunLift(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK));
			setpoint = getEncoderPosition();
		} 
		else if ((Math.abs(error) > KLift.LIFT_TOLERANCE)) {
			if (Math.abs(error) > KLift.LIFT_PIDTOLERANCE)
				RunLift(direction * 0.8);
			else
			{
				RunLift(direction * 
				(KLift.STATICP + 
				(KLift.ERRORP * Math.abs(error) / 
				( (targetPosition > encoderPosition) ? targetPosition : encoderPosition)
				)
				)
				);
			}
			SmartDashboard.putNumber("Error", error);
			SmartDashboard.putNumber("Lift Motor Value", direction * KLift.ERRORP * Math.abs(error) / ( (targetPosition > encoderPosition) ? targetPosition : encoderPosition));
		} 
		else 
		{
			RunLift(0);
		}
	}

	public void periodic() {
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());
		setToPosition();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift(-1));
	}

	public static Lift getInstance() {if (instance == null) {instance = new Lift();}return instance;}
}