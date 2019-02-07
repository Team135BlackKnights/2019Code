package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

//import frc.robot.commands.*;

public class Limelight extends Subsystem 
{
	private static Limelight instance;

	NetworkTableInstance networkTableInstance = NetworkTableInstance.getDefault();
	NetworkTable limelightTable = networkTableInstance.getTable("limelight");
	NetworkTableEntry validTargetEntry = limelightTable.getEntry("tv");
	NetworkTableEntry horizontalOffsetEntry = limelightTable.getEntry("tx");
	NetworkTableEntry verticalOffsetEntry = limelightTable.getEntry("ty");
	NetworkTableEntry targetAreaEntry = limelightTable.getEntry("ta");
	NetworkTableEntry targetSkewEntry = limelightTable.getEntry("tl");
	NetworkTableEntry ledModeEntry = limelightTable.getEntry("ledMode");
	NetworkTableEntry cameraModeEntry = limelightTable.getEntry("camMode");
	NetworkTableEntry limelightPipelineEntry = limelightTable.getEntry("pipeline");


	public static final int NUMBER_OF_LIMELIGHT_CHARACTERISTICS = 5;
	public static final int VALID_TARGET = 0;
	public static final int HORIZONTAL_OFFSET = 1;
	public static final int VERTICAL_OFFSET = 2;
	public static final int TARGET_AREA = 3;
	public static final int TARGET_SKEW = 4;
	public static double[] limelightData = new double[NUMBER_OF_LIMELIGHT_CHARACTERISTICS];

	public static int LED_ON = 0;
	public static int LED_OFF = 1;
	public static int LED_BLINKING = 2;

	public static int VISION_PROCESSOR = 0;
	public static int DRIVER_CAMERA = 1; 

	public static int VISION_PIPELINE = 0;
	public static int HATCH_PIPELINE = 1;

  public double[] GetLimelightData()
  {
    limelightData[VALID_TARGET] = validTargetEntry.getDouble(0.0);
    limelightData[HORIZONTAL_OFFSET] = horizontalOffsetEntry.getDouble(0.0);
    limelightData[VERTICAL_OFFSET] = verticalOffsetEntry.getDouble(0.0);
    limelightData[TARGET_AREA] = targetAreaEntry.getDouble(0.0);
    limelightData[TARGET_SKEW] = targetSkewEntry.getDouble(0.0);
    
    SmartDashboard.putNumber("Valid Target", limelightData[VALID_TARGET]);
    SmartDashboard.putNumber("Horizontal Offset", limelightData[HORIZONTAL_OFFSET]);
    SmartDashboard.putNumber("Vertical Offset", limelightData[VERTICAL_OFFSET]);
    SmartDashboard.putNumber("Target Area", limelightData[TARGET_AREA]);    	
    return limelightData;
  }
  
  public void SetLEDMode(int onOrOff)
  {
    ledModeEntry.setNumber(onOrOff);
    return;
  }
  
  public void SetCameraMode(int cameraMode)
  {
    cameraModeEntry.setNumber(cameraMode);
    return;
  }
  
  //  Sets the Vision Pipeline to retrieve data from
  public void SetCameraPipeline(int pipeline)
  {
    limelightPipelineEntry.setNumber(pipeline);
    return;
  }
  public boolean isTargetsExist()
    {
    	double numberOfValidTargets;
    	boolean targetsExist;
    	
    	numberOfValidTargets = validTargetEntry.getDouble(0.0);
    	
    	if (numberOfValidTargets > 0.0)
    	{
    		targetsExist = true;
    	}
    	else 
    	{
    		targetsExist = false;
    	}
    	
    	return targetsExist;
    }
  @Override
	public void periodic() 
	{
    SmartDashboard.putNumberArray("Limelight data values", limelightData);
    SmartDashboard.putNumber("Valid Target?", limelightData[VALID_TARGET]);
    SmartDashboard.putNumber("Horizontol Offset", limelightData[HORIZONTAL_OFFSET]);
    SmartDashboard.putNumber("Target Area", limelightData[TARGET_AREA]);
  }
		public static Limelight initializeLimelight()
		{
			if (instance == null){instance = new Limelight();}return instance;
		}

		public void initDefaultCommand() 
		{
	//		setDefaultCommand(new GetLimelightData());
		}
}