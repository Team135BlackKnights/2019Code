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
import frc.robot.commands.Sensors.*;
import frc.robot.*;
import frc.robot.commands.MotorCommands.*;

public class EndGame extends Subsystem {

	public static EndGame instance;
	public CANSparkMax endGameMotor;
	public CANEncoder endgameEncoder;
	public Solenoid 
	piston,
	pistoon;
	public boolean TF = false;
	public DigitalInput limitSwitch;
	public static double setpoint =0;
	public static double Tolerance = 5;
	public static double PTolerance = 10;



	private EndGame() {
		initializeMotor();
		endgameEncoder = endGameMotor.getEncoder();
		piston = new Solenoid(Pneumatics.ENDGAME_PISTON);
		pistoon = new Solenoid(Pneumatics.ENDGAME_PISTOON);
		piston.set(false);
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
	public void setToPosition()
	{
		double currentPosition = getEncoderPosition();
		double desiredPosition = setpoint;

		double direction = (desiredPosition - currentPosition) < 0 ? -1.0: 1.0;

		double distanceFrom = desiredPosition - currentPosition;

		if (Robot.oi.isUpEndPressed())
		{
			RunEndGame(1);
			setpoint = currentPosition;
		}
		else if (Robot.oi.isDownEndPressed())
		{
			RunEndGame(-1);
			setpoint = currentPosition;
		}
		else if((Math.abs(distanceFrom) >Tolerance))
		{
			if (Math.abs(distanceFrom) >PTolerance)
			{
				RunEndGame(direction * .75);
			}
			else 
			{
				RunEndGame(direction * (Math.abs(distanceFrom)/(desiredPosition > currentPosition ? desiredPosition : currentPosition)));
			}
		}
		else 
		{
			RunEndGame(0);
		}
		}
	
	public double getEncoderPosition()
	{
		return endgameEncoder.getPosition();
	}

	public void movePiston(boolean TF) {
		piston.set(TF);
	}
	public void movePistoon(boolean TF)
	{
		pistoon.set(TF);
	}
	public void resetEncoder()
	{
		endgameEncoder.setPosition(0);
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
		setDefaultCommand(new RunEndGame());
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
		SmartDashboard.putData("Reset EndEncoder" , new resetEncoderEndgame());
	}
}
