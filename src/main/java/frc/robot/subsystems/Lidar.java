package frc.robot.subsystems;

import java.util.TimerTask;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Lidar {
    private I2C i2c;
    private byte[] distance;
    private java.util.Timer updater;
    private final int LIDAR_ADDR = 0x62;
    private final int LIDAR_CONFIG_REGISTER = 0x00;
    private final int LIDAR_DISTANCE_REGISTER = 0x8f;
    private static Lidar instance;

    public Lidar() {
        i2c = new I2C(Port.kMXP, LIDAR_ADDR);
        distance = new byte[2];
        updater = new java.util.Timer();
    }

    /**
     * Return Disatnce in centimeters
     *
     * @return distance in cm
     */
    private int getDistance() {
        return (int) Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
    }

    /**
     * Return Distance in Inches
     * 
     * @return distance in inches
     */
    public double getDistanceIn() {
        return (double) getDistance() * 0.393701;
    }

    public double getDistanceFt() {
        return (double) getDistanceIn() / 12;
    }

    public void getDistanceReadOuts() {
        SmartDashboard.putNumber("Distance In", getDistanceIn());
        SmartDashboard.putNumber("Distance Ft", getDistanceFt());
    }

    public void start() {
        updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);
    }

    /**
     * Stop the background sensor-polling task.
     */
    public void stop() {
        updater.cancel();
        updater = new java.util.Timer();
    }

    /**
     * Read from the sensor and update the internal "distance" variable with the
     * result.
     */
    private void update() {
        i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
        Timer.delay(0.04); // Delay for measurement to be taken
        i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
        Timer.delay(0.005); // Delay to prevent over polling
    }

    public void periodic() {
        SmartDashboard.putNumber("Lidar inches: ", getDistanceIn());
        SmartDashboard.putNumber("Lidar feet: ", getDistanceFt());
    }

    public static Lidar getInstance() {
        if (instance == null) {
            instance = new Lidar();
        }
        return instance;
    }

    /**
     * Timer task to keep distance updated
     *
     */
    private class LIDARUpdater extends TimerTask {
        public void run() {
            while (true) {
                update();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
