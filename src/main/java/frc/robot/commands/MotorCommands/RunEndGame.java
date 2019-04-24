package frc.robot.commands.MotorCommands;

import frc.robot.*;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class RunEndGame extends Command {
	// This will run the endgame manipulator and allows for the pistons to auto release when at a certain point


	public RunEndGame() {
		requires(Robot.endgame);
	
	}

	protected void execute() 
	{
	if (!Robot.oi.isEndGamePressed()){}
	else {
		EndGame.setpoint = 234;
	}
	while(Robot.endgame.getEncoderPosition() > 135)
	{
		Robot.endgame.movePiston(true);
	}
	} 
		
	  
	  

	

	@Override
	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.endgame.RunEndGame(0);
	}

	protected void interrupted() {
		end();
	}
}
