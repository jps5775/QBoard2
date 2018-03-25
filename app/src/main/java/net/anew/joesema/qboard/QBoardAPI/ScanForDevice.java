package net.anew.joesema.qboard.QBoardAPI;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;

import java.util.logging.Level;

/**
 * Information found on Android Developer Website
 * Created by laurenritter on 3/22/18.
 */

public class ScanForDevice extends ListActivity {
    private BluetoothAdapter bluetoothAdapter;
    private boolean scanning;
    private Handler handler;
    private LeDeviceListAdapter leDeviceListAdapter;

    //time alloted to scan for devices
    private static final long SCAN_PERIOD = 10000;


    //start the scan
    private void scan(final boolean enable){
        if(enable) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanning = false;
                    bluetoothAdapter.stopLeScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            scanning = true;
            bluetoothAdapter.startLeScan(leScanCallback);
        }else{
            scanning = false;
            bluetoothAdapter.stopLeScan(leScanCallback);
        }
    }

    //stop the scan
    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            runOnUiThread(new Runnable(){
                @Override
                public void run(){
                    leDeviceListAdapter.addDevice(device);
                    leDeviceListAdapter.notifyDataSetChanged();
                }
            });
        }
    }
}
