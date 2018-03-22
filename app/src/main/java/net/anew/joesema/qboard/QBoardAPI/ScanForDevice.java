package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

/**
 * Information found on Android Developer Website
 * Created by laurenritter on 3/22/18.
 */

public class ScanForDevice extends ListActivity {
    private BluetoothAdapter bluetoothAdapter;
    private boolean scanning;
    private Handler handler;

    //time alloted to scan for devices
    private static final long SCAN_PERIOD = 10000;

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

}
