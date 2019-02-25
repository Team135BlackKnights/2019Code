package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap.Robot.*;

public class EndGame extends Subsystem {

	public static EndGame instance;
	public CANSparkMax endGameMotor;
	public CANEncoder endgameEncoder;
	public Solenoid piston;
	public Solenoid pistoon;
	public boolean TF = false;
	public DigitalInput limitSwitch;


	private EndGame() {
		initializeMotor();
		endgameEncoder = endGameMotor.getEncoder();
		piston = new Solenoid(Pneumatics.ENDGAME_PISTON);
		pistoon = new Solenoid(Pneumatics.ENDGAME_PISTOON);
		piston.set(true);
		pistoon.set(false);
		limitSwitch = new DigitalInput(Sensors.ENDGAME_SWITCH_ID);

	}

	public void initializeMotor() {
		endGameMotor = new CANSparkMax(Endgame.END_GAME_SPARK, MotorType.kBrushless);
		endGameMotor.setIdleMode(IdleMode.kBrake);
	}

	public void RunEndGame(double power) {
		endGameMotor.set(power);
	}

	public void movePiston(boolean TF) {
		piston.set(TF);
	}
	public void movePistoon(boolean TF)
	{
		pistoon.set(TF);
	}
	
	public boolean isSwitchPressed()
	{
		return limitSwitch.get();
	}
	public void getData()
	{
		double endGameVel = endgameEncoder.getVelocity();
		double endGamePos = endgameEncoder.getPosition();
		boolean switchPos = isSwitchPressed();

		SmartDashboard.putNumber("EndGame Velocity: ", endGameVel);
		SmartDashboard.putNumber("EndGame Position: ", endGamePos);

		SmartDashboard.putBoolean("Is Switch Set", switchPos);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public static EndGame getInstance() {
		if (instance == null) {
			instance = new EndGame();
		}
		return instance;
	}
	public void periodic()
	{
		getData();
	}
}
