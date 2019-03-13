package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.subsystems.DriveTrain;
import frc.robot.RobotMap.KOI;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue, RightJoystickYValue, LeftJoystickZValue;

    public static boolean isFieldOrientated, isCompBot;
    public double robotAngle; 

    public static final double POV_DRIVE_SPEED = .2;
    public static final double POV_STRAFE_SPEED = .35;

    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.limelight.initLimelight(Robot.limelight.LED_OFF, Robot.limelight.DRIVER_CAMERA);
    }

    protected void execute() {
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        RightJoystickXValue = Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        LeftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * Robot.oi.returnLeftSlider();
        
         if (OI.fullSpeedTurn())
         {
             Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue, -LeftJoystickZValue);
         }
         else 
         {
             Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
         }
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
