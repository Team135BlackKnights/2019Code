package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap.Robot.*;

public class Intake extends Subsystem {
	public static Intake instance;

	public TalonSRX intakeElbow;
	public VictorSPX intake;
	public Solenoid solenoid;
	public Compressor compressor;

	private Intake() {
		intakeElbow = new TalonSRX(KIntake.INTAKE_ELBOW_TALON);
		intake = new VictorSPX(KIntake.INTAKE_VICTOR);

		intakeElbow.setNeutralMode(NeutralMode.Brake);

		intake.setNeutralMode(NeutralMode.Brake);
		intake.setInverted(KIntake.leftInverted);

		solenoid = new Solenoid(Pneumatics.INTAKE_SOLENOID);
		compressor = new Compressor();

		compressor.setClosedLoopControl(true);
		compressor.start();
		MoveSolenoid(true);
	}

	public void setCompressorOff() {
		compressor.setClosedLoopControl(false);
		compressor.stop();
	}

	public void setCompressorOn() {
		compressor.setClosedLoopControl(true);
	}

	public void RunIntake(double power) {
		intake.set(ControlMode.PercentOutput, power);
	}

	public void RunElbow(double power) {
		intakeElbow.set(ControlMode.PercentOutput, power);
	}

	public void MoveSolenoid(boolean position) {
		solenoid.set(position);
	}

	public void periodic() {
	}

	@Override
	protected void initDefaultCommand() {

	}

	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
	}
}
