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
import frc.robot.RobotMap.Robot.KIntake;
import frc.robot.RobotMap.Robot.Pneumatics;
public class Intake extends Subsystem 
{
    public static Intake instance;
    
    public TalonSRX intakeElbow;
	public VictorSPX leftIntake, rightIntake;
	public Solenoid solenoid;
	public Compressor compressor;
	public DigitalInput vexButton;

    private Intake()
    {
		intakeElbow = new TalonSRX(KIntake.INTAKE_ELBOW_TALON);
		leftIntake = new VictorSPX(KIntake.LEFT_INTAKE_VICTOR);
		rightIntake = new VictorSPX(KIntake.RIGHT_INTAKE_VICTOR);
		vexButton = new DigitalInput(KIntake.VEX_BUTTON_ID);	

		intakeElbow.setNeutralMode(NeutralMode.Brake);
		leftIntake.setNeutralMode(NeutralMode.Coast);
		rightIntake.setNeutralMode(NeutralMode.Coast);
		leftIntake.setInverted(KIntake.leftInverted);
		rightIntake.setInverted(KIntake.rightInverted);

		solenoid = new Solenoid(Pneumatics.INTAKE_SOLENOID);
		compressor = new Compressor(Pneumatics.COMPRESSOR_ID);
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
		leftIntake.set(ControlMode.PercentOutput, power);
		rightIntake.set(ControlMode.PercentOutput, power);
	}
	public void RunElbow(double power)
	{
		intakeElbow.set(ControlMode.PercentOutput, power);	
	}
	public void MoveSolenoid(boolean position)
	{
		solenoid.set(position);
	}
	@Override
	protected void initDefaultCommand() {setDefaultCommand(new MoveIntakeElbow());}
	public static Intake getInstance(){if (instance == null){instance = new Intake();}return instance; }
}
