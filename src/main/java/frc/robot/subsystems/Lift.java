package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.RunLift;
import frc.robot.commands.SubsystemDefaults.*;
import frc.robot.commands.Sensors.*;
import frc.robot.RobotMap.Robot.*;

public class Lift extends Subsystem {
	public static Lift instance;

	public TalonSRX LeftLiftTalon, RightLiftTalon;
	public VictorSPX LeftLiftVictor, RightLiftVictor;

	public Encoder encoder;

	public double kP = 1.0;
	public static double setpoint = 0;
	public static int setpointIndex = 0;
	public double motorValue = 0;

	private Lift() {
		LeftLiftTalon = new TalonSRX(KLift.LIFT_LEFT_TALON);
		RightLiftTalon = new TalonSRX(KLift.LIFT_RIGHT_TALON);
		LeftLiftVictor = new VictorSPX(KLift.LIFT_LEFT_VICTOR);
		RightLiftVictor = new VictorSPX(KLift.LIFT_RIGHT_VICTOR);

		initializeMotorController(RightLiftTalon);
		initializeMotorController(LeftLiftVictor);
		initializeMotorController(RightLiftVictor);
		encoder = new Encoder(KLift.ENCODER_A, KLift.ENCODER_B);
	}

	public void initializeMotorController(TalonSRX talon) {
		talon.setNeutralMode(NeutralMode.Brake);
		talon.follow(LeftLiftTalon);
	}

	public void initializeMotorController(VictorSPX victor) {
		victor.setNeutralMode(NeutralMode.Brake);
		victor.follow(LeftLiftTalon);
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
		LeftLiftTalon.set(ControlMode.PercentOutput, power);
		RightLiftTalon.set(ControlMode.PercentOutput, power);
		LeftLiftVictor.set(ControlMode.PercentOutput, power);
		RightLiftVictor.set(ControlMode.PercentOutput, power);
	}

	public void setToPosition() {
		double encoderPosition = getEncoderPosition();
		double targetPosition = setpoint;
		SmartDashboard.putNumber("targetposition", targetPosition);
		double direction = (targetPosition - encoderPosition) < 0 ? -1.0 : 1.0;
		setpoint = setpoint < -20 ? -20 : setpoint;
		double error = targetPosition - encoderPosition;
		if ((Math.abs(error) > 15)) {
			double dividevalue = Math.abs(targetPosition) < 1.0 ? 1.0 : Math.abs(targetPosition);
			dividevalue /= Math.abs(error) > 100 ? 2.0 : 1.0;
			SmartDashboard.putNumber("RunLiftValue", kP * (error) / dividevalue / 1.2 + 0.1 * direction);
			SmartDashboard.putNumber("Error", (error) / dividevalue);
			RunLift(kP * (error) / dividevalue / 1.2 + 0.1 * direction);
			encoderPosition = getEncoderPosition();
			error = targetPosition - encoderPosition;
		} else {
			RunLift(0);
		}
	}

	public void periodic() {
		SmartDashboard.putNumber("Lift Motor Output Percent", LeftLiftTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("Setpoint", setpoint);
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		SmartDashboard.putNumber("Lift Encoder Velocity", getEncoderVelocity());

		SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());

		SmartDashboard.putData("Move Lift 0(9)", new RunLift(0));
		SmartDashboard.putData("Move Lift 50(10)", new RunLift(1));
		SmartDashboard.putData("Move Lift 100(11)", new RunLift(2));
		SmartDashboard.putData("Back to Joysticks", new RunLiftAnalog());
		setToPosition();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift(-1));
	}

	public static Lift getInstance() {
		if (instance == null) {
			instance = new Lift();
		}
		return instance;
	}
}
