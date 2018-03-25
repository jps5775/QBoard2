package net.anew.joesema.qboard.QBoardAPI;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JoeSema on 3/20/18.
 */

public class SimuBoard {

    private Random numberGenerator;
    private Device mainDevice;
    private double[] gyroData;
    private double[] accelData;
    private double temperature = -1;

    /**
     * Default constructor
     */
    public SimuBoard(){
        numberGenerator = new Random();
    }

    /**
     * Searches for bluetooth enabled devices to connect to.
     * @return A List of devices representing all bluetooth enabled devices in the area
     */
    public List<Device> searchDevices(){
        List<Device> list = new ArrayList<Device>();
        for (int i = 0; i < 10; i++) {
            list.add(new Device( "000" + i, false));
        }
        return list;
    }
    /**
     * Connects the device passed in to the main phone device.
     * @return A boolean representing that the device is paired.
     */
    public boolean pairPhone(Device deviceToConnect){
        deviceToConnect.setPaired(true);
        mainDevice = deviceToConnect;
        return true;
    }

    /**
     * Returns the accelerometer data from the 16-byte data packet.
     * @return A double array representing the x, y, and z accelerations in meters/second.
     */
    public double[] getAccelerometer(){
        if (mainDevice == null){return null;}
        // first time generating accel data
        if (accelData == null) accelData = new double[]{numberGenerator.nextDouble(),
                numberGenerator.nextDouble(), numberGenerator.nextDouble()};

        for (int i = 0; i < accelData.length; i++){
            accelData[i] = (accelData[i] * 1.3);
            if(accelData[i] > 9) accelData[i] = 1.0;
        }
        return accelData;
    }

    /**
     * Returns the gyroscope data from the 16-byte data packet.
     * @return A double array representing the x, y, and z angular velocity in degrees/second.
     */
    public double[] getGyroscope(){
        if (mainDevice == null){return null;}
        if(gyroData == null) gyroData = new double[]{numberGenerator.nextDouble(),
                numberGenerator.nextDouble(), numberGenerator.nextDouble()};

        for (int i = 0; i < gyroData.length; i++){
            gyroData[i] = (gyroData[i] * 1.3);
            if(gyroData[i] > 9) gyroData[i] = 1.0;
        }

        return gyroData;
    }

    /**
     * Returns the current temperature.
     * @return A double representing the degrees in Fahrenheit.
     */
    public double getTemperature(){
        if (mainDevice == null){return -1;}
        if(temperature == -1) temperature = numberGenerator.nextDouble() * 50;
        return temperature;
    }

}
