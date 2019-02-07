package frc.robot.commands.DriverAssistance.Parallel;

import frc.robot.Robot;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToward extends Command 
{

	private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
	private final double DRIVE_TRAIN_MOTOR_POWER = .3;
	private final double LIMELIGHT_DRIVE_STRAIGHT_P_VALUE = 0.045;
    private final double VERTICAL_THRESHOLD = 10;
    private static boolean VALID_TARGET = false;

	
    public DriveToward()
    {
    	requires(Robot.limelight);
    	requires(Robot.driveTrain);
    }
    
    protected void initialize()
    {
    	
    	Robot.limelight.SetCameraPipeline(Limelight.HATCH_PIPELINE);
    	Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        Robot.limelight.SetLEDMode(Limelight.LED_ON); 
        VALID_TARGET = Robot.limelight.isTargetsExist();
    }

    protected void execute()
    {
        limelightData = Robot.limelight.GetLimelightData();
        if (VALID_TARGET) {
    	Robot.driveTrain.DriveStraightWVision(DRIVE_TRAIN_MOTOR_POWER, limelightData[Limelight.HORIZONTAL_OFFSET], LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }
}

    protected boolean isFinished()
    {
        return (limelightData[Limelight.VERTICAL_OFFSET] >= VERTICAL_THRESHOLD )
        || VALID_TARGET == false;    
    }

    protected void end()
    {
    	System.out.println("Finished");
    	Robot.driveTrain.StopMotors();
    }
protected void interrupted()
    {
    	this.end();
    }
}
