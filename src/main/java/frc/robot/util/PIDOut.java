package frc.robot.util;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOut implements PIDOutput {

	public double output = 0.0;

	@Override
	public void pidWrite(double output) {
		this.output = output;
	}

}