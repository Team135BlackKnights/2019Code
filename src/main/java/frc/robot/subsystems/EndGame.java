package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.Robot.*;

public class EndGame extends Subsystem {

	public static EndGame instance;
	public CANSparkMax endGameMotor;
	public Solenoid piston, pistoon;
	public boolean TF = false;
	public DigitalInput limitSwitch;

	private EndGame() 
	{
		initializeMotor();
		piston = new Solenoid(Pneumatics.ENDGAME_PISTON);
		pistoon = new Solenoid(Pneumatics.ENDGAME_PISTOON);
		piston.set(false);	pistoon.set(false);
		limitSwitch = new DigitalInput(Sensors.ENDGAME_SWITCH_ID);

	}
	public void initializeMotor() 
	{
		endGameMotor = new CANSparkMax(Endgame.END_GAME_SPARK, MotorType.kBrushless);
		endGameMotor.restoreFactoryDefaults();
		endGameMotor.setIdleMode(IdleMode.kBrake);
	}

	public void RunEndGame(double power) 
	{endGameMotor.set(power);}

	public void movePiston(boolean TF) 
	{piston.set(TF);}
	public void movePistoon(boolean TF)
	{pistoon.set(TF);}
	
	public boolean isSwitchPressed()
	{return limitSwitch.get();}
	
	@Override
	protected void initDefaultCommand() {}

	public static EndGame getInstance() {if (instance == null) {instance = new EndGame();}return instance;}	
}
