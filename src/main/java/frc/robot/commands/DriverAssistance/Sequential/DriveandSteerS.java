package frc.robot.commands.DriverAssistance.Sequential;

import frc.robot.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveandSteerS extends CommandGroup {

    public DriveandSteerS() {
        requires(Robot.limelight);
        requires(Robot.driveTrain);
        Robot.limelight.SetLEDMode(Limelight.LED_ON);

        addSequential(new LineUpS());
        addSequential(new DriveTowardS());
        addSequential(new StrafeS());

    }
}
