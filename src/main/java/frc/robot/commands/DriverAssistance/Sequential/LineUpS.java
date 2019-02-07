/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriverAssistance.Sequential;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;
import frc.robot.subsystems.*;

public class LineUpS extends Command 
{
  private double[] limelightData = new double[Limelight.NUMBER_OF_LIMELIGHT_CHARACTERISTICS];

  private boolean targetExists = false;
  private final double TURNING_MOTOR_POWER= -.15;
  private final double X_THRESHOLD_TO_STOP_TURNING = 2;


  public LineUpS() 
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

    targetExists = false;
  }

  @Override
  protected void execute()
  {

    targetExists = Robot.limelight.isTargetsExist();
    limelightData = Robot.limelight.GetLimelightData();
    if (targetExists == false)  
    {
      Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Left);
    }
    else if (targetExists && limelightData[Limelight.HORIZONTAL_OFFSET]  > 0.0)  // If Target is to the Right, turn to the Right
    {
      Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Right);
    }
    else if (targetExists && limelightData[Limelight.HORIZONTAL_OFFSET] < 0.0)  //  If Target is to the Left, turn to the Left
    {
      Robot.driveTrain.TurnDriveTrain(TURNING_MOTOR_POWER, DriveTrain.DirectionToTurn.Left);
    }
    else  
    {
      Robot.driveTrain.StopMotors();
    }
    }
    
    

  @Override
  protected boolean isFinished() 
  {
    return (limelightData[Limelight.HORIZONTAL_OFFSET] <= X_THRESHOLD_TO_STOP_TURNING 
    && limelightData[Limelight.HORIZONTAL_OFFSET] >= -X_THRESHOLD_TO_STOP_TURNING
    && limelightData[Limelight.HORIZONTAL_OFFSET] != 0);  
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
