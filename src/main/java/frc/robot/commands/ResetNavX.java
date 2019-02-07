package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ResetNavX extends InstantCommand 
{

    public ResetNavX() 
    {
        super();
    }

    protected void initialize() 
    {
    		Robot.navx.initAngle = 0;
            Robot.navx.reset();
            SmartDashboard.putString("NavX", "Is Resetting");
    	}
    }