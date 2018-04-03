package net.anew.joesema.qboard.QBoardAPI;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by JoeSema on 3/20/18.
 */

public class SimuBoard {

    private Random numberGenerator;
    private Device mainDevice;
    private double[] gyroData;
    private double[] accelData;
    private double temperature = -1;
    ScanForDevice scan = new ScanForDevice();

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
    public HashMap<String, String> searchDevices(HashMap<String, BluetoothDevice> hashy){

        scan.startScan(hashy);

        return scan.scanResults;
    }
    /**
     * Connects the device passed in to the main phone device.
     * @return A boolean representing that the device is paired.
     */
    public boolean pairPhone(){
        Device deviceToConnect = new Device(scan.SERVICE_UUID.toString(), false);
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
     * Overloaded working getAccelerometer method
     * @param device The device which contains the 16-byte data packet
     * @return A double array representing the x, y, and z accelerations in meters/second.
     */
    public double[] getAccelerometer(Device device){
        // Example 16 byte payload:
        /*
            byte[] bytePayload = {(byte)0x84, (byte)0x01, (byte)0xbe, (byte)0xff,(byte)0xa3,
                                    (byte)0x0f,(byte)0xfb,(byte)0xff,(byte)0xd5,(byte)0xff,
                                    (byte)0xf4,(byte)0xff,(byte)0xa7,(byte)0xff,(byte)0x00,
                                    (byte)0x00};
         */
        byte[] bytePayload = device.payload;

        // get hex from payload
        String x_axis = "";
        String y_axis = "";
        String z_axis = "";

        StringBuilder hexPayloadStr = new StringBuilder();
        for (byte b : bytePayload) {
            hexPayloadStr.append(String.format("%02X", b));
        }

        // swap bytes
        x_axis = hexPayloadStr.substring(2, 4) + hexPayloadStr.substring(0, 2);
        y_axis = hexPayloadStr.substring(6, 8) + hexPayloadStr.substring(4, 6);
        z_axis = hexPayloadStr.substring(10, 12) + hexPayloadStr.substring(8, 10);

        // 16 bit conversion after swapped
        int x_axisDec = Integer.parseInt(x_axis, 16 );
        int y_axisDec = Integer.parseInt(y_axis, 16 );
        int z_axisDec = Integer.parseInt(z_axis, 16 );

        // convert each axis into g's
        // Formula: g = (decimal / 32768) * 8
        double[] gForces = new double[3];
        gForces[0] = (x_axisDec / (double)32768) * 8;
        gForces[1] = (y_axisDec / (double)32768) * 8;
        gForces[2] = (z_axisDec / (double)32768) * 8;
        // ex: 0x8401beffa30ffbffd5fff4ffa7ff0000

        return gForces;
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

    public double[] getGyroscope(Device device){
        // Example 16 byte payload:
        /*
            byte[] bytePayload = {(byte)0x84,(byte)0x01,(byte)0xbe, byte)0xff,(byte)0xa3,
                                    (byte)0x0f,(byte)0xfb,(byte)0xff,(byte)0xd5,(byte)0xff,
                                    (byte)0xf4,(byte)0xff,(byte)0xa7,(byte)0xff,(byte)0x00,
                                    (byte)0x00};
         */
        byte[] bytePayload = device.payload;

        // get hex from payload
        String x_axis = "";
        String y_axis = "";
        String z_axis = "";

        StringBuilder hexPayloadStr = new StringBuilder();
        for (byte b : bytePayload) {
            hexPayloadStr.append(String.format("%02X", b));
        }

        // swap bytes
        x_axis = hexPayloadStr.substring(14, 16) + hexPayloadStr.substring(12, 14);
        y_axis = hexPayloadStr.substring(18, 20) + hexPayloadStr.substring(16, 18);
        z_axis = hexPayloadStr.substring(22, 24) + hexPayloadStr.substring(20, 22);

        // 16 bit conversion after swapped
        int x_axisDec = Integer.parseInt(x_axis, 16 );
        int y_axisDec = Integer.parseInt(y_axis, 16 );
        int z_axisDec = Integer.parseInt(z_axis, 16 );

        // convert each axis into g's
        // Formula: degs/sec = (decimal / 32768) * 2000
        double[] gForces = new double[3];
        gForces[0] = (x_axisDec / (double)32768) * 2000;
        gForces[1] = (y_axisDec / (double)32768) * 2000;
        gForces[2] = (z_axisDec / (double)32768) * 2000;

        return gForces;
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
