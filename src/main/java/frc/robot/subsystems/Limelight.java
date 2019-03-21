package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

//import frc.robot.commands.*;

public class Limelight extends Subsystem {
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

	public final int NUMBER_OF_LIMELIGHT_CHARACTERISTICS = 5;
	public final int VALID_TARGET = 0;
	public final int HORIZONTAL_OFFSET = 1;
	public final int VERTICAL_OFFSET = 2;
	public final int TARGET_AREA = 3;
	public final int TARGET_SKEW = 4;
	public double[] limelightData = new double[NUMBER_OF_LIMELIGHT_CHARACTERISTICS];

	public int LED_ON = 0;
	public int LED_OFF = 1;
	public int LED_BLINKING = 2;

	public int VISION_PROCESSOR = 0;
	public int DRIVER_CAMERA = 1;

	public int VISION_PIPELINE = 0;
	public int HATCH_PIPELINE = 1;
	public int BALL_PIPELINE = 2;

	public double[] GetLimelightData() {
		limelightData[VALID_TARGET] = validTargetEntry.getDouble(0.0);
		limelightData[HORIZONTAL_OFFSET] = horizontalOffsetEntry.getDouble(0.0);
		limelightData[VERTICAL_OFFSET] = verticalOffsetEntry.getDouble(0.0);
		limelightData[TARGET_AREA] = targetAreaEntry.getDouble(0.0);
		limelightData[TARGET_SKEW] = targetSkewEntry.getDouble(0.0);
		return limelightData;
	}

	public void SetLEDMode(int onOrOff) 
	{ledModeEntry.setNumber(onOrOff);}

	public void SetCameraMode(int cameraMode) 
	{cameraModeEntry.setNumber(cameraMode);}

	public void SetCameraPipeline(int pipeline) 
	{limelightPipelineEntry.setNumber(pipeline);}
	public void initLimelight(int ledMode, int pipeline)
	{
		SetLEDMode(ledMode);
		SetCameraPipeline(pipeline);
	}

	@Override
	public void periodic() 	{}

	public static Limelight getInstance() {if (instance == null) {instance = new Limelight();}return instance;}

	public void initDefaultCommand() {}
}