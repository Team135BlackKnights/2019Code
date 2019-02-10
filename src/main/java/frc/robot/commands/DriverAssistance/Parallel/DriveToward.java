package frc.robot.commands.DriverAssistance.Parallel;

import frc.robot.Robot;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.command.Command;

public class DriveToward extends Command 
{
	private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
	
	private final double LIMELIGHT_DRIVE_STRAIGHT_P_VALUE = 0.045;
    private final double AREA_THRESHOLD = 8;
    private static boolean VALID_TARGET = false;
    private double DRIVE_TRAIN_MOTOR_POWER;
	
    private int _pipeline;
    public DriveToward(int pipeline)
    {
        requires(Robot.limelight);
        requires(Robot.driveTrain);
        this._pipeline =pipeline;

    }
   
    protected void initialize()
    {
    	
    	Robot.limelight.SetCameraPipeline(_pipeline);
    	Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        if (_pipeline == 2)
		{
			Robot.limelight.SetLEDMode(Limelight.LED_OFF);  
		}
		else {
		Robot.limelight.SetLEDMode(Limelight.LED_ON);  
		}        VALID_TARGET = Robot.limelight.isTargetsExist();
    }

    protected void execute()
    {
        if( this._pipeline == 2) {
            DRIVE_TRAIN_MOTOR_POWER =.3;
            }
            else {
                DRIVE_TRAIN_MOTOR_POWER =.15;
            }
        limelightData = Robot.limelight.GetLimelightData();
        if (VALID_TARGET) {
    	Robot.driveTrain.DriveStraightWVision(DRIVE_TRAIN_MOTOR_POWER, limelightData[Limelight.HORIZONTAL_OFFSET], LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }
}

    protected boolean isFinished()
    {
        return (limelightData[Limelight.TARGET_AREA] >= AREA_THRESHOLD )
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
