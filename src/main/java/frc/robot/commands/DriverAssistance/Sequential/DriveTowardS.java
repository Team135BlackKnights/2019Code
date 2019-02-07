package frc.robot.commands.DriverAssistance.Sequential;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight;

public class DriveTowardS extends InstantCommand 
{

private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
private final double VERTICAL_THRESHOLD = 10;
private final double DRIVE_TRAIN_MOTOR_POWER =.3;
private static double LIMELIGHT_DRIVE_STRAIGHT_P_VALUE = .004;
private static boolean VALID_TARGET = false;

  public DriveTowardS() 
  {
    requires(Robot.limelight);
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() 
  {

    VALID_TARGET = Robot.limelight.isTargetsExist();
    Robot.limelight.SetCameraPipeline(Limelight.HATCH_PIPELINE);
    Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
    Robot.limelight.SetLEDMode(Limelight.LED_ON);
  }

  @Override
  protected void execute() 
  {
    limelightData = Robot.limelight.GetLimelightData();
        if (VALID_TARGET) {
    	Robot.driveTrain.DriveStraightWVision(DRIVE_TRAIN_MOTOR_POWER, limelightData[Limelight.HORIZONTAL_OFFSET], LIMELIGHT_DRIVE_STRAIGHT_P_VALUE);
    }
  }

  @Override
  protected boolean isFinished() 
  {
    return (limelightData[Limelight.VERTICAL_OFFSET] >= VERTICAL_THRESHOLD )
    || VALID_TARGET == false;
    }

    protected void end()
    {
      Robot.driveTrain.StopMotors();
    }
    protected void interrupted()
    {
    	this.end();
    }
}
