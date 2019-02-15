package frc.robot.commands.SubsystemDefaults;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.*;
import frc.robot.subsystems.*;

public class DriveWithJoysticks extends Command {

    private double RightJoystickXValue;
    private double RightJoystickYValue;
    private double leftJoystickZValue;

    public DriveWithJoysticks() {
        requires(Robot.driveTrain);
    }

    protected void initialize() {
        Robot.limelight.SetCameraPipeline(Limelight.VISION_PIPELINE);
        Robot.limelight.SetCameraMode(Limelight.VISION_PROCESSOR);
        Robot.limelight.SetLEDMode(Limelight.LED_OFF);
    }

    protected double shouldControlsBeNegative() {
        return ((Robot.navx.getFusedAngle() % 180) > 45) && ((Robot.navx.getFusedAngle() % 180) < 135) ? -1 : 1;
    }

    protected void execute() {
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK);
        RightJoystickXValue = Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK);
        leftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK);

        if (OI.isSwapPressed()) {
            RightJoystickXValue *= shouldControlsBeNegative();
            RightJoystickYValue *= shouldControlsBeNegative();
            Robot.driveTrain.cartesianDrive(RightJoystickXValue * shouldControlsBeNegative(),
                    RightJoystickYValue * shouldControlsBeNegative(), -leftJoystickZValue * .35,
                    -Robot.navx.getFusedAngle());
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
