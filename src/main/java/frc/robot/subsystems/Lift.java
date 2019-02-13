package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.*;
import frc.robot.RobotMap.Robot.KLift;

public class Lift extends Subsystem 
{
    public static Lift instance;
    
    public TalonSRX LeftLiftTalon, RightLiftTalon;
    public VictorSPX LeftLiftVictor, RightLiftVictor;
   
	//public Encoder encoder;

	public double kP = 0.4;
	public static double setpoint = 0;
	public static int setpointIndex = 0;
	public double motorValue = 0;

    private Lift()
    {
		LeftLiftTalon = new TalonSRX(KLift.LIFT_LEFT_TALON);
		RightLiftTalon = new TalonSRX(KLift.LIFT_RIGHT_TALON);
		LeftLiftVictor = new VictorSPX(KLift.LIFT_LEFT_VICTOR);
		RightLiftVictor = new VictorSPX(KLift.LIFT_RIGHT_VICTOR);
		
		initializeMotorController(RightLiftTalon);
		initializeMotorController(LeftLiftVictor);
		initializeMotorController(RightLiftVictor);
		//encoder = new Encoder(5, 6);
    }

	public void initializeMotorController(TalonSRX talon)
	{
		talon.setNeutralMode(NeutralMode.Brake);
		talon.follow(LeftLiftTalon);
	}
	public void initializeMotorController(VictorSPX victor)
	{
		victor.setNeutralMode(NeutralMode.Brake);
		victor.follow(LeftLiftTalon);
	}
/*
	public double getEncoderVelocity()
	{
		return encoder.getRate();
	}
	
	public double getEncoderPosition()
	{
		return encoder.get();
	}
	
	public void resetEncoders()
	{
		encoder.reset();
	}
*/
	public void RunLift(double power) 
	{
		LeftLiftTalon.set(ControlMode.PercentOutput, power);
		RightLiftTalon.set(ControlMode.PercentOutput, power);
		LeftLiftVictor.set(ControlMode.PercentOutput, power);
		RightLiftVictor.set(ControlMode.PercentOutput, power);
	}
/*
	public void setToPosition()
	{
		double encoderPosition = getEncoderPosition();
		double targetPosition = setpoint;
		double direction = (targetPosition - encoderPosition) < 0 ? -1.0 : 1.0;
		if ( (Math.abs(targetPosition - encoderPosition) > 15))
		{
			SmartDashboard.putNumber("RunLiftValue", kP * (targetPosition - encoderPosition) / targetPosition + kP * direction);
			SmartDashboard.putNumber("Error", (targetPosition - encoderPosition) / targetPosition);
			RunLift(kP * (targetPosition - encoderPosition) / targetPosition + kP * direction);
			encoderPosition = getEncoderPosition();	
			motorValue = kP * (targetPosition - encoderPosition) / targetPosition + kP * direction;
		}
		RunLift(motorValue);
	}

	public void periodic() 
	{
		SmartDashboard.putNumber("Lift Motor Output Percent", LeftLiftTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("Setpoint", setpoint);
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		SmartDashboard.putNumber("Lift Encoder Velocity", getEncoderVelocity());

		SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());

		SmartDashboard.putData("Move Lift 0(9)", new RunLiftButtons(0));
		SmartDashboard.putData("Move Lift 50(10)", new RunLiftButtons(1));
		SmartDashboard.putData("Move Lift 100(11)", new RunLiftButtons(2));

		setToPosition();
	}
	*/
	@Override
	protected void initDefaultCommand() {setDefaultCommand(new RunLiftAnalog());}//setDefaultCommand(new RunLift(-1));}
	public static Lift getInstance(){if (instance == null){instance = new Lift();}return instance; }
}
