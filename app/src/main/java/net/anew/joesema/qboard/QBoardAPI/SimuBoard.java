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

    private ScanForDevice scanForDevice;

    /**
     * Connects to the snowboard

     –Component parses and converts data to doubles

     –Returns calls for accelerometer, gyroscope, temp data

     –Data returned should be realistic, and the sensor data values should change over successive calls
     */

    private Random numberGenerator;

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
        scanForDevice.scan(boolean enabled);
        /**
        List<Device> list = new ArrayList<Device>();
        for (int i = 0; i < 10; i++) {
            list.add(new Device( "000" + i, false));
        }
        return list;
         */
    }

    public boolean pairPhone(Device deviceToConnect){
        deviceToConnect.setPaired(true);
        return true;
    }

    /**
     * Returns the accelerometer data from the 16-byte data packet.
     * @return A double array representing the x, y, and z accelerations.
     */
    public double[] getAccelerometer(){
        return new double[]{numberGenerator.nextDouble(),
                numberGenerator.nextDouble(), numberGenerator.nextDouble()};
    }

    /**
     * Returns the gyroscope data from the 16-byte data packet.
     * @return A double array representing the x, y, and z orientations.
     */
    public double[] getGyroscope(){
        return new double[]{numberGenerator.nextDouble(),
                numberGenerator.nextDouble(), numberGenerator.nextDouble()};
    }

    /**
     * Returns the current temperature.
     * @return A double representing the degrees in Fahrenheit.
     */
    public double getTemperature(){

        return numberGenerator.nextDouble() * 50;
    }

}
