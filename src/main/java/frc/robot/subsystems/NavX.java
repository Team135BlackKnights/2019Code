package frc.robot.subsystems;
	
	import com.kauailabs.navx.frc.AHRS;
	
	import edu.wpi.first.wpilibj.SerialPort;
	import edu.wpi.first.wpilibj.command.Subsystem;
	import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
	
	import frc.robot.*;
	
public class NavX extends Subsystem 
{
	private static NavX instance;
	public double initAngle = 0.0;
	public AHRS navx;

	@Override
	protected void initDefaultCommand() {}

	public NavX() 
	{
		navx = new AHRS(SerialPort.Port.kUSB1);
		navx.reset();
		initAngle = navx.getFusedHeading();
	}

	public static NavX initializeNavX() 
	{
		if (instance == null) 
		{
			instance = new NavX();
		}
		return instance;
	}

	public void reset() 
	{
		navx.reset();
	}

	public double getFusedAngle() 
	{
		return ((navx.getFusedHeading() + initAngle) % 360.0);
	}

	public void periodic() 
	{
		SmartDashboard.putNumber("Robot Angle", Robot.navx.getFusedAngle());
	}
}