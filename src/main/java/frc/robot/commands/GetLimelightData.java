package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.command.Command;

public class GetLimelightData extends Command 
{

	public double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
    public GetLimelightData()
    {
        requires(Robot.limelight);
    }

    protected void initialize()
    {
    	Robot.limelight.SetCameraPipeline(Limelight.HATCH_PIPELINE);
    	Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
    	Robot.limelight.SetLEDMode(Limelight.LED_ON);  
    }

    protected void execute()
    {
    	limelightData = Robot.limelight.GetLimelightData();
    }

    protected boolean isFinished()
    {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
