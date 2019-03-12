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

	public double kP = 1.0;
	public static double setpoint = 0;
	public static int setpointIndex = 0;
	public double motorValue = 0;

	private Lift() {
		
			LeftLiftSpark = new CANSparkMax(KLift.LIFT_LEFT_SPARK, MotorType.kBrushless);
			RightLiftSpark = new CANSparkMax(KLift.LIFT_RIGHT_SPARK, MotorType.kBrushless);
			LeftLiftSpark.setIdleMode(IdleMode.kBrake);
			RightLiftSpark.setIdleMode(IdleMode.kBrake);
	}

	public void RunLift(double power) {
			LeftLiftSpark.set(power);
			RightLiftSpark.set(power);
		
	}
	public void setToPosition() {
		RunLift(Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK));
	}

	public void periodic() {
		//SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		Robot.driveTrain.chassis.feedWatchdog();
		//SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());

		SmartDashboard.putNumber("Left Motor TemperatureF ", LeftLiftSpark.getMotorTemperature() * 9/5 + 32);
		SmartDashboard.putNumber("Right Motor TemperatureF ", RightLiftSpark.getMotorTemperature() * 9/5 + 32);
		SmartDashboard.putNumber("Left Motor Temperature ", LeftLiftSpark.getMotorTemperature());
		SmartDashboard.putNumber("Right Motor Temperature ", RightLiftSpark.getMotorTemperature());
		//SmartDashboard.putData("Move Lift 0(9)", new RunLift(0));
		//SmartDashboard.putData("Move Lift 50(10)", new RunLift(1));
		//SmartDashboard.putData("Move Lift 100(11)", new RunLift(2));
		setToPosition();
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new RunLift(-1));
	}

	public static Lift getInstance() {if (instance == null) {instance = new Lift();}return instance;}
}
