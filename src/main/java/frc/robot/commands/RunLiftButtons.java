/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap.Robot.KLift;
import frc.robot.subsystems.Lift;

public class RunLiftButtons extends InstantCommand {
  public RunLiftButtons(int upOrDown) {
    requires(Robot.lift);
    Lift.setpointIndex += upOrDown;
    if (Lift.setpointIndex < 0){Lift.setpointIndex = 0;}
    if (Lift.setpointIndex > 4){Lift.setpointIndex = 4;}
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Setpoint Value", KLift.LIFT_SETPOINTS[Lift.setpointIndex]);
    SmartDashboard.putBoolean("Is ButtonLift Running", true);
    Robot.lift.setToPosition(KLift.LIFT_SETPOINTS[Lift.setpointIndex], 1.0);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    SmartDashboard.putBoolean("Is ButtonLift Running", false);
  }

  @Override
  protected void interrupted() {
  }
}
