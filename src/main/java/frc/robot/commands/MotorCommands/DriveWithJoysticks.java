package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import frc.robot.*;
import frc.robot.RobotMap.KOI;
import frc.robot.subsystems.*;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue, RightJoystickYValue, LeftJoystickZValue;

    public static boolean isFieldOrientated, isCompBot;
    public double robotAngle; 

    public static final double POV_DRIVE_SPEED = .2;

    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
        isCompBot = Robot.isComp;
      
    }

    protected void initialize() {
        Robot.limelight.SetCameraPipeline(Limelight.VISION_PIPELINE);
        Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        Robot.limelight.SetLEDMode(Limelight.LED_OFF);
    }

    protected void execute() {
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        RightJoystickXValue = Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        LeftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * Robot.oi.returnLeftSlider();
      
         if (OI.PovDirection(KOI.RIGHT_JOYSTICK, OI.UP_POV))
         {
             Robot.driveTrain.cartesianDrive(0, POV_DRIVE_SPEED, 0);
             SmartDashboard.putString("POV DIRECTION","Up");

         }
         else  if (OI.PovDirection(KOI.RIGHT_JOYSTICK, OI.RIGHT_POV))
         {
             Robot.driveTrain.cartesianDrive(-POV_DRIVE_SPEED *1.5, 0, 0);
             SmartDashboard.putString("POV DIRECTION","Right");

         }
         else if (OI.PovDirection(KOI.RIGHT_JOYSTICK, OI.DOWN_POV))
         {
             Robot.driveTrain.cartesianDrive(0, -POV_DRIVE_SPEED , 0);
             SmartDashboard.putString("POV DIRECTION","Down");

         }
         else if (OI.PovDirection(KOI.RIGHT_JOYSTICK, OI.LEFT_POV))
         {
             Robot.driveTrain.cartesianDrive(POV_DRIVE_SPEED *1.5 , 0, 0);
             SmartDashboard.putString("POV DIRECTION","Left");

         }
         else if (OI.fullSpeedTurn())
         {
             Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue, -LeftJoystickZValue);
         }
         else 
         {
             Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.35);
         }
         SmartDashboard.putNumber("Right Pov Value", OI.GetAnglePov(1));
        }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        Robot.driveTrain.StopMotors();
    }

    protected void interrupted() {
        this.end();
    }
}
