package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue, RightJoystickYValue, LeftJoystickZValue;

    private boolean isTurnFast;

    public DriveWithJoysticks() {requires(Robot.driveTrain); }

    protected void initialize() 
    {
        Robot.limelight.initLimelight(Robot.limelight.LED_OFF, Robot.limelight.DRIVER_CAMERA);
        isTurnFast = Robot.oi.IsTurnFast();

        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        RightJoystickXValue = -Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        LeftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * Robot.oi.returnLeftSlider();

    }
        
    protected void execute()
    {
        if (isTurnFast)
        {
        Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue);
        }
        else {
        Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
        }
    }

    protected boolean isFinished() {return false; }

    protected void end() {Robot.driveTrain.StopMotors();}

    protected void interrupted() {this.end();}
}