package frc.robot.commands.MotorCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap.Robot.KLift;
import frc.robot.subsystems.Lift;

public class RunLift extends Command {
  // This is the command where we take an input, either buttons or the joystick and set the lift to the desired position
  boolean isButton;

  public RunLift(int position) {
    requires(Robot.lift);
    Lift.setpointIndex = (position == -1 ? Lift.setpointIndex : position);
  }

  @Override
  protected void initialize() {

  }

  protected void execute() {
    int whichButtonPressed = OI.liftButtons();
    if (whichButtonPressed == -1) {
    } else {
      Lift.setpointIndex = whichButtonPressed;
      SmartDashboard.putNumber("Setpoint Value", Lift.setpointIndex);
      Lift.setpoint = KLift.LIFT_SETPOINTS[Lift.setpointIndex];
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

}
