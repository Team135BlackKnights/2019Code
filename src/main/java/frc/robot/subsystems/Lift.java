package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

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
   
    private Lift()
    {
		LeftLiftTalon = new TalonSRX(KLift.LIFT_LEFT_TALON);
		RightLiftTalon = new TalonSRX(KLift.LIFT_RIGHT_TALON);
		LeftLiftVictor = new VictorSPX(KLift.LIFT_LEFT_VICTOR);
		RightLiftVictor = new VictorSPX(KLift.LIFT_RIGHT_VICTOR);
		
		initializeMotorController(RightLiftTalon , KLift.LIFT_LEFT_TALON);
    }

	public void initializeMotorController(TalonSRX talon, int Talon_id)
	{
		talon.setNeutralMode(NeutralMode.Brake);
		talon.follow(LeftLiftTalon);
	}
	public void initializeVictor(VictorSPX victor, int Victor_id)
	{
		victor.follow(LeftLiftTalon);
	}
	
	public double getEncoderVelocity()
	{
		return (double)LeftLiftTalon.getSelectedSensorVelocity(0);
	}
	
	public double getEncoderPosition()
	{
		return (double)LeftLiftTalon.getSelectedSensorPosition(0);
	}
	
	public void RunLift(double power) 
	{
		LeftLiftTalon.set(ControlMode.PercentOutput, power);
	}

	public void periodic() 
	{
		SmartDashboard.putNumber("ManipJoystick Y ", Robot.oi.GetJoystickYValue(RobotMap.OI.MANIP_JOYSTICK));
		SmartDashboard.putNumber("Lift Value", LeftLiftTalon.getMotorOutputPercent());
		SmartDashboard.putNumber("Lift Encoder Position", getEncoderPosition());
		SmartDashboard.putNumber("Lift Encoder Velocity", getEncoderVelocity());
	}
	@Override
	protected void initDefaultCommand() {setDefaultCommand(new RunLift());}
	public static Lift getInstance(){if (instance == null){instance = new Lift();}return instance; }
}
