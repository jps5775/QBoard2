package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.util.Log;

import java.util.*;

/**
 * Information taken from https://www.bignerdranch.com/blog/bluetooth-low-energy-part-1/
 * Created by laurenritter on 3/29/18.
 */

public class BtleScanCallback extends ScanCallback {
    private final static String TAG = ScanForDevice.class.getSimpleName();
    HashMap<String, BluetoothDevice> scanResults;


    public BtleScanCallback(HashMap<String, String> scanResults){
    }

    @Override
    public void onScanResult(int callbackType, ScanResult result){
        addScanResult(result);
    }

    @Override
    public void onBatchScanResults(List<ScanResult> results){
        for(ScanResult result:results){
            addScanResult(result);
        }
    }

    private class GattClientCallback extends BluetoothGattCallback { }

    @Override
    public void onScanFailed(int errorCode){
        Log.e(TAG, "BLE Scan Failed with code" + errorCode);
    }

    private void addScanResult(ScanResult result){
        BluetoothDevice device = result.getDevice();
        String deviceAddress = device.getAddress();
        scanResults.put(deviceAddress, device);
    }
}
