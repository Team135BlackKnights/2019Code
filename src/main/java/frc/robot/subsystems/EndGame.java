package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

public class EndGame extends Subsystem 
{

    public static EndGame instance;
	public CANSparkMax endGameMotor;
	public CANEncoder endgameEncoder; 
	public Solenoid piston;
	public boolean TF = false;

    private EndGame()
    {
		initializeMotor();
		endgameEncoder = endGameMotor.getEncoder();
		piston = new Solenoid(RobotMap.Robot.Pneumatics.ENDGAME_PISTON);
		piston.set(TF);
	}
	public void initializeMotor()
	{
		endGameMotor = new CANSparkMax(RobotMap.Robot.EndGame.END_GAME_SPARK, MotorType.kBrushless);
		endGameMotor.setIdleMode(IdleMode.kBrake);
	}
	public void RunEndGame(double power)
	{
		endGameMotor.set(power);
	}
	public void movePiston(boolean TF)
	{
		piston.set(TF);
	}
	@Override
	protected void initDefaultCommand() {}
	public static EndGame getInstance(){if (instance == null){instance = new EndGame();}return instance; }
}
