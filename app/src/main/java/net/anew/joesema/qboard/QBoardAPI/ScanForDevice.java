package net.anew.joesema.qboard.QBoardAPI;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.util.logging.Handler;

/**
 * Information taken from Android Developer Website
 * Created by laurenritter on 3/27/18.
 */

public class ScanForDevice extends ListActivity {
    private BluetoothAdapter bluetoothAdapter;
    private boolean scanning;
    private Handler handler;

    private static final long SCAN_PERIOD = 10000;
//    private LeDeviceListAdapter leDeviceListAdapter;

    private void scanLeDevice(final boolean enable){
        if(enable){
            //stops scanning after certain amount of time
            handler.postDelayed(new Runnable(){
                @Override
                public void run(){
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

    private LeDeviceListAdapter leDeviceListAdapter;

    //scan callback
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