package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class DriveWithJoysticks extends Command 
{

    private double RightJoystickXValue, RightJoystickYValue, LeftJoystickZValue;
    private double sliderValue; 
    public double desiredPos, releasePos;
    public DriveWithJoysticks()
    {
        requires(Robot.driveTrain);
    }

    protected void initialize() 
    {
        Robot.limelight.initLimelight(Robot.limelight.LED_OFF, Robot.limelight.DRIVER_CAMERA);
    }
        
    protected void execute() 
    {
        sliderValue = 1;// ((Robot.oi.returnRightSlider() *.75) +.25);
        RightJoystickYValue = Robot.oi.GetJoystickYValue(RobotMap.KOI.RIGHT_JOYSTICK) * sliderValue;
        RightJoystickXValue = -Robot.oi.GetJoystickXValue(RobotMap.KOI.RIGHT_JOYSTICK) * sliderValue;
        LeftJoystickZValue = Robot.oi.GetJoystickZValue(RobotMap.KOI.LEFT_JOYSTICK) * sliderValue;
       if (OI.SlowDown())
       {
        Robot.driveTrain.cartesianDrive(RightJoystickXValue/2, RightJoystickYValue/2,-LeftJoystickZValue*.4);
       }
       else {
        Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
     } 
        if (Robot.oi.isEndGamePressed())
        {
               
      desiredPos = 234;
      releasePos = 135;
   
    while (Robot.endgame.getEncoderPosition() < desiredPos)
   {
    Robot.endgame.RunEndGame(1);
    Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);

   }
    Robot.endgame.RunEndGame(0);
    Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);

   if (Robot.endgame.getEncoderPosition() >= releasePos)
		{
            Robot.endgame.movePiston(true);
            Robot.driveTrain.cartesianDrive(RightJoystickXValue, RightJoystickYValue,-LeftJoystickZValue *.40);
        }
    
        }
    }


    protected boolean isFinished() { return false; }

    protected void end() {Robot.driveTrain.StopMotors(); }

    protected void interrupted() {this.end(); }
}
