/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriverAssistance.Sequential;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StrafeS extends InstantCommand 
{

private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];
private final double Strafe_POWER = .3;
private final double X_THRESHOLD_TO_STOP_STRAFING = 1;
private boolean VALID_TARGET;
	
  public StrafeS() 
  {
    requires(Robot.limelight);
    requires(Robot.driveTrain);
  }
  @Override
  protected void initialize() 
  {
    Robot.limelight.SetCameraPipeline(Limelight.HATCH_PIPELINE);
    Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
    Robot.limelight.SetLEDMode(Limelight.LED_ON);
  }

  @Override
  protected void execute() 
  {
    VALID_TARGET = Robot.limelight.isTargetsExist();
    limelightData = Robot.limelight.GetLimelightData();

     if (VALID_TARGET && limelightData[Limelight.HORIZONTAL_OFFSET] +8 >0) 
    {
      Robot.driveTrain.driveCartesian(0, Strafe_POWER,0, 0);
    } 
    else if (VALID_TARGET && limelightData[Limelight.HORIZONTAL_OFFSET] < 0) 
    {
      Robot.driveTrain.driveCartesian(0, -Strafe_POWER,0, 0);
    } 
    else 
    {
      end();
    }
    SmartDashboard.putString("Command Running", "Strafe");

    }
  @Override
  protected boolean isFinished() 
  {
    return (limelightData[Limelight.HORIZONTAL_OFFSET] <= X_THRESHOLD_TO_STOP_STRAFING
    && limelightData[Limelight.HORIZONTAL_OFFSET] +8 >= -X_THRESHOLD_TO_STOP_STRAFING 
    && limelightData[Limelight.HORIZONTAL_OFFSET] != 0)
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
