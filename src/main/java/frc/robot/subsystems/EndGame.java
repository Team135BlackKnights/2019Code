package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.commands.*;
import frc.robot.RobotMap;

public class EndGame extends Subsystem 
{

    public static EndGame instance;
    public CANSparkMax endGameMotor;
    
   
    private EndGame()
    {
		initializeMotor();
    }

    public static EndGame initializeEndGame()
	{
		if (instance == null)
		{
			instance = new EndGame();
		}
		return instance; 
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
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunEndGame());
	}
}
