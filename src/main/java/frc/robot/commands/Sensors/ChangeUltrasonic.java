/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Sensors;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.RobotMap.Robot.Sensors;
import frc.robot.subsystems.UltrasonicSensor;

public class ChangeUltrasonic extends InstantCommand {
  boolean intake;
  public ChangeUltrasonic(boolean isIntake) {
    requires(Robot.ultra);
    intake = isIntake;
  }

  @Override
  protected void initialize() 
  {
    UltrasonicSensor.sonar = (intake ? new Ultrasonic(Sensors.INTAKE_TRIG, Sensors.INTAKE_ECHO) :
                                        new Ultrasonic(Sensors.DRIVETRAIN_TRIG, Sensors.DRIVETRAIN_ECHO));
  }

}
