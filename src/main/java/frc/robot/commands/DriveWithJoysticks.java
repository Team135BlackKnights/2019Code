package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.*;
import frc.robot.subsystems.*;

/**
 *
 */
public class DriveWithJoysticks extends Command 
{

    private double RightJoystickXValue;
    private double RightJoystickYValue;
    private double leftJoystickZValue;

    public DriveWithJoysticks() 
    {
        requires(Robot.driveTrain);
    }

    protected void initialize() 
    {
        Robot.limelight.SetCameraPipeline(Limelight.VISION_PIPELINE);
        Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        Robot.limelight.SetLEDMode(Limelight.LED_ON);
   }


    protected void execute() 
    {
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.OI.RIGHT_JOYSTICK);
        RightJoystickXValue = Robot.oi.GetJoystickXValue(RobotMap.OI.RIGHT_JOYSTICK);
        leftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.OI.LEFT_JOYSTICK);

        if (OI.isSwapPressed() == false)
        {
        RightJoystickXValue = (Robot.navx.getFusedAngle() % 180) > 45 && (Robot.navx.getFusedAngle() % 180) < 135 ? RightJoystickXValue * -1 : RightJoystickXValue;
        RightJoystickYValue = (Robot.navx.getFusedAngle() % 180) > 45 && (Robot.navx.getFusedAngle() % 180) < 135 ? RightJoystickYValue * -1 : RightJoystickYValue;
        Robot.driveTrain.driveCartesian(RightJoystickXValue, RightJoystickYValue, -leftJoystickZValue*.35, -Robot.navx.getFusedAngle());
        } else if (OI.isSwapPressed() == true )
        {
        Robot.driveTrain.driveCartesian(RightJoystickXValue, RightJoystickYValue, -leftJoystickZValue*.35,0);
        }
    }
       
    protected boolean isFinished() 
    {
        return false;
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
