package frc.robot.commands.SubsystemDefaults;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.subsystems.*;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue;
    private double RightJoystickYValue;
    private double leftJoystickZValue;

    public static boolean isFieldOrientated;
    public double robotAngle; 
    public boolean isCompBot;

    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
        isCompBot = Robot.isCompBot();
        if  (isCompBot)
        {
            robotAngle = Robot.navx.getFusedAngle();
        }
        else 
        {
            robotAngle = Robot.pigeon.getFusedAngle();
        }
    }

    protected void initialize() {
        Robot.limelight.SetCameraPipeline(Limelight.VISION_PIPELINE);
        Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        Robot.limelight.SetLEDMode(Limelight.LED_OFF);
    }

    protected double shouldControlsBeNegative() {
        return ((robotAngle % 180) < 45) || ((robotAngle % 180) > 135) ? -1 : 1;
    }

    protected void execute() {
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        RightJoystickXValue = Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * Robot.oi.returnRightSlider();
        leftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * Robot.oi.returnLeftSlider();
        if (OI.turnTo0.get()) {
            leftJoystickZValue += Robot.driveTrain.turnToAnglePID(90);
        }
        if (OI.turnToRocketClose.get()) {
            leftJoystickZValue += Robot.driveTrain.turnToAnglePID(30);
        }
        if (OI.turnToRocketFar.get()) {
            leftJoystickZValue += Robot.driveTrain.turnToAnglePID(145);
        }
        // SmartDashboard.putNumber("RightX", RightJoystickXValue);
        // SmartDashboard.putNumber("RightY", RightJoystickYValue);
        // SmartDashboard.putNumber("LeftZ", leftJoystickZValue);
        if (OI.fieldOrientated()) {
            Robot.driveTrain.cartesianDrive(RightJoystickXValue * shouldControlsBeNegative(),
                    -RightJoystickYValue * shouldControlsBeNegative(), -leftJoystickZValue * .35,
                    Robot.pigeon.getFusedAngle());
        } else {
            Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue, -leftJoystickZValue * .35);
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
