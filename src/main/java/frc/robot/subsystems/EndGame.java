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
	public boolean TF = false;
	public DigitalInput limitSwitch;


	private EndGame() {
		initializeMotor();
		endgameEncoder = endGameMotor.getEncoder();
		piston = new Solenoid(Pneumatics.ENDGAME_PISTON);
		piston.set(TF);
		limitSwitch = new DigitalInput(0);

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
	
	public boolean isSwitchPressed()
	{
		return limitSwitch.get();
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
	@Override
	public void periodic() {
		SmartDashboard.putBoolean("Switch Pressed", isSwitchPressed());
	}
}
