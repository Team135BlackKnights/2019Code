package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
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
   
	public Encoder encoder;

	public double kP = 0.3;
	public double setpoint;
	public static int setpointIndex = 0;

    private Lift()
    {
		LeftLiftTalon = new TalonSRX(KLift.LIFT_LEFT_TALON);
		RightLiftTalon = new TalonSRX(KLift.LIFT_RIGHT_TALON);
		LeftLiftVictor = new VictorSPX(KLift.LIFT_LEFT_VICTOR);
		RightLiftVictor = new VictorSPX(KLift.LIFT_RIGHT_VICTOR);
		
		initializeMotorController(RightLiftTalon);
		initializeMotorController(LeftLiftVictor);
		initializeMotorController(RightLiftVictor);
		encoder = new Encoder(5, 6);
		//LeftLiftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		//LeftLiftTalon.setSelectedSensorPosition(0, 0, 10);
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

	public void RunLift(double power) 
	{
		LeftLiftTalon.set(ControlMode.PercentOutput, power);
		RightLiftTalon.set(ControlMode.PercentOutput, power);
		LeftLiftVictor.set(ControlMode.PercentOutput, power);
		RightLiftVictor.set(ControlMode.PercentOutput, power);
	}

	public void setToPosition(int targetPosition, double timeout)
	{
		double encoderPosition = getEncoderPosition();
		double direction = (targetPosition - encoderPosition) < 0 ? -1.0 : 1.0;
		Timer timer = new Timer();
		timer.start();
		while ( (Math.abs(targetPosition - encoderPosition) > 10) && timer.get() < timeout)
		{
			SmartDashboard.putNumber("RunLiftValue", kP * (targetPosition - encoderPosition) / targetPosition + kP * direction);
			SmartDashboard.putNumber("Error", (targetPosition - encoderPosition) / targetPosition);
			RunLift(kP * (targetPosition - encoderPosition) / targetPosition + kP * direction);
			encoderPosition = getEncoderPosition();	
			
		}
		RunLift(0);
	}

	public void periodic() 
	{
		SmartDashboard.putNumber("ManipJoystick Y ", Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK));
		SmartDashboard.putNumber("Lift Motor Output Percent", LeftLiftTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		SmartDashboard.putNumber("Lift Encoder Velocity", getEncoderVelocity());
		SmartDashboard.putData("Reset Lift Encoder", new resetEncoderLift());
		SmartDashboard.putData("Move Lift Up", new RunLiftButtons(2));
		SmartDashboard.putData("Move Lift Down", new RunLiftButtons(1));
	}
	@Override
	protected void initDefaultCommand() {setDefaultCommand(new RunLiftAnalog());}
	public static Lift getInstance(){if (instance == null){instance = new Lift();}return instance; }
}
