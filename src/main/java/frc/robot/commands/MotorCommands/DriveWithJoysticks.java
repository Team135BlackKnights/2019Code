package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue, RightJoystickYValue, LeftJoystickZValue;
    public boolean isPIDDrive ;
    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.limelight.initLimelight(Robot.limelight.LED_OFF, Robot.limelight.DRIVER_CAMERA);
        isPIDDrive = Robot.driveTrain.isPIDDrive;
    }
        
    protected void execute() {

        isPIDDrive = SmartDashboard.getBoolean("Is PID DRIVE", false);

        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        RightJoystickXValue = -Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        LeftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * Robot.oi.returnLeftSlider();
        if (isPIDDrive)
        {
            if (OI.fullSpeedTurn())
            {
                Robot.driveTrain.cartesianPID(RightJoystickXValue, RightJoystickYValue, -LeftJoystickZValue);
            }
            else 
            {
                Robot.driveTrain.cartesianPID(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
            }
        }
        else {
            if (OI.fullSpeedTurn())
            {
                Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue, -LeftJoystickZValue);
            }
            else 
            {
                Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
            }
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
