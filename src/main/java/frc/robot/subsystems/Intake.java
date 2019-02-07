package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.*;
import frc.robot.RobotMap;

public class Intake extends Subsystem 
{

    public static Intake instance;
    
    public TalonSRX IntakeElbow;
	public VictorSPX LeftIntake, RightIntake;
	public Solenoid solenoid;
	public Compressor compressor;
	public DigitalInput vexButton;

	public boolean leftInverted = false;
	public boolean rightInverted = false; 

     

    private Intake()
    {
  		InitializeMotors();
   		InitializePneumatics();
    }

    public static Intake initializeIntake()
	{
		if (instance == null)
		{
			instance = new Intake();
		}
		return instance; 
	}
	
	public void InitializeMotors()
	{
		IntakeElbow = new TalonSRX(RobotMap.Robot.Intake.INTAKE_ELBOW_TALON);
		LeftIntake = new VictorSPX(RobotMap.Robot.Intake.LEFT_INTAKE_VICTOR);
		RightIntake = new VictorSPX(RobotMap.Robot.Intake.RIGHT_INTAKE_VICTOR);
		vexButton = new DigitalInput(RobotMap.Robot.Intake.VEX_BUTTON_ID);	

		IntakeElbow.setNeutralMode(NeutralMode.Brake);
		LeftIntake.setNeutralMode(NeutralMode.Coast);
		RightIntake.setNeutralMode(NeutralMode.Coast);
		LeftIntake.setInverted(leftInverted);
		RightIntake.setInverted(rightInverted);
	}
	
	public void InitializePneumatics()
	{
		solenoid = new Solenoid(RobotMap.Robot.Pneumatics.INTAKE_SOLENOID);
		compressor = new Compressor(RobotMap.Robot.Pneumatics.COMPRESSOR_ID);
		compressor.setClosedLoopControl(true);

	}
	public void setCompressorOff()
	{
		compressor.setClosedLoopControl(false);
		compressor.stop();
	}
	
	public void setCompressorOn()
	{
		compressor.setClosedLoopControl(true);
	}

	
	public void RunIntake(double power)
	{
		LeftIntake.set(ControlMode.PercentOutput, power);
		RightIntake.set(ControlMode.PercentOutput, power);
	}
	public void RunElbow(double power)
	{
		IntakeElbow.set(ControlMode.PercentOutput, power);	
	}
	public void MoveSolenoid(boolean position)
	{
		solenoid.set(position);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new MoveIntakeElbow());
	}
}
