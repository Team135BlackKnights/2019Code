/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap.Robot.KLift;

public class RunLiftButtons extends Command {
  private int setPointIndex;
  public RunLiftButtons(int upOrDown) {
    requires(Robot.lift);
    setPointIndex = (setPointIndex + upOrDown ) < 0 ? 0 : setPointIndex + upOrDown;
    setPointIndex = setPointIndex > 4 ? 4 : setPointIndex;
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Setpoint Value", KLift.LIFT_SETPOINTS[setPointIndex]);
    Robot.lift.setToPosition(KLift.LIFT_SETPOINTS[setPointIndex], 3.0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.lift.RunLift(0);
  }

  @Override
  protected void interrupted() {
  }
}
