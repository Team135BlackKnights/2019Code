package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.*;
import frc.robot.RobotMap;

public class Lift extends Subsystem 
{

    public static Lift instance;
    
    public TalonSRX LeftLiftTalon, RightLiftTalon;
    public VictorSPX LeftLiftVictor, RightLiftVictor;

	public static TalonSRX LeadMotor;
   
   
    private Lift()
    {
    
    LeftLiftTalon = new TalonSRX(RobotMap.Robot.Lift.LIFT_LEFT_TALON);
    RightLiftTalon = new TalonSRX(RobotMap.Robot.Lift.LIFT_RIGHT_TALON);
    LeftLiftVictor = new VictorSPX(RobotMap.Robot.Lift.LIFT_LEFT_VICTOR);
    RightLiftVictor = new VictorSPX(RobotMap.Robot.Lift.LIFT_RIGHT_VICTOR);

		LeadMotor = LeftLiftTalon;
		
		initializeTalon(LeftLiftTalon , RobotMap.Robot.Lift.LIFT_LEFT_TALON);
    }

    public static Lift initializeLift()
	{
		if (instance == null)
		{
			instance = new Lift();
		}
		return instance; 
	}
	public void initializeTalon(TalonSRX talon, int Talon_id)
  {
	  talon.setNeutralMode(NeutralMode.Brake);
	if(!(Talon_id == LeadMotor.getDeviceID()))
	{
		talon.follow(LeadMotor);
	}
  }
  public void initializeVictor(VictorSPX victor, int Victor_id)
  {
	  victor.setNeutralMode(NeutralMode.Brake);
	if(!(Victor_id == LeadMotor.getDeviceID()))
	{
		victor.follow(LeadMotor);
	}
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
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunLift());
	}
}
