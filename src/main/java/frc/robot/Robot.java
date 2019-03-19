
package frc.robot;


import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Lift lift;
	public static Intake intake;
	public static EndGame endgame;
	public static Limelight limelight;
	
	public static boolean isComp;
	static I2C Wire = new I2C(Port.kOnboard, 4);
	int LEDBRIGHTNESS = 128;
	int adder = 1;
	Command autonomousCommand;
	SendableChooser<String> chooser = new SendableChooser<>();
	static SendableChooser<Boolean> isCompBot = new SendableChooser<>();
	@Override
	public void robotInit() {
		
		isCompBot.setDefaultOption("IsCompBot",true); 
		isCompBot.addOption("IsPracticeBot", false);
		SmartDashboard.putData(isCompBot);
		isComp = isCompBot.getSelected();
		driveTrain = DriveTrain.getInstance();
		lift = Lift.getInstance();
		intake = Intake.getInstance();
		endgame = EndGame.getInstance();
		limelight = Limelight.getInstance();
		oi = OI.getInstance();

		Robot.limelight.SetLEDMode(Limelight.LED_OFF);
	}
	
	public void LEDON(String WriteString)
  {
    char[] CharArray = WriteString.toCharArray();
    byte[] WriteData = new byte[CharArray.length];
    byte[] readData = new byte[1000];
    for (int i = 0; i < CharArray.length; i++) {
      WriteData[i] = (byte) CharArray[i];
    }
    Wire.transaction(WriteData, WriteData.length, readData, 0);
  }

	@Override
	public void disabledInit() {}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		/*
		if (LEDBRIGHTNESS > 254)
		adder = -1;
		if (LEDBRIGHTNESS < 1)
		adder = 1;
		LEDBRIGHTNESS += adder;
		String toSend = "";
		toSend += LEDBRIGHTNESS;
		if (toSend.length() == 2)
		toSend = "0" + toSend.charAt(0) + toSend.charAt(1);
		if (toSend.length() == 1)
		toSend = "00" + toSend.charAt(0);
		*/
		//System.out.println(toSend);
		//LEDON("B" + toSend + "T");
		//LEDON("B" + toSend + "B");
	}

	@Override
	public void testPeriodic() {}

	public static boolean isCompBot()
	{
		return isComp;
	}
}