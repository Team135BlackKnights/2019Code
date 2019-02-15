package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.RobotMap.Robot.KLift;
import frc.robot.subsystems.Lift;

public class RunLift extends Command {
  boolean isButton;

  public RunLift(int position) {
    requires(Robot.lift);
    Lift.setpointIndex = (position == -1 ? Lift.setpointIndex : position);
  }

  @Override
  protected void initialize() {

  }

  protected void execute() {
    Lift.setpoint += Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK) * 5;
    SmartDashboard.putNumber("Joystick Setpoint Add", Robot.oi.GetJoystickYValue(RobotMap.KOI.MANIP_JOYSTICK) * 5);

    int whichButtonPressed = OI.liftButtons();
    if (whichButtonPressed == -1) {
    } else {
      SmartDashboard.putNumber("Setpoint Value", Lift.setpointIndex);
      Lift.setpoint = KLift.LIFT_SETPOINTS[Lift.setpointIndex];
    }

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
