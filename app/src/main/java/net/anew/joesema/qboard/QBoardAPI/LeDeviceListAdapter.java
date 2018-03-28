package net.anew.joesema.qboard.QBoardAPI;

import android.view.LayoutInflater;
import java.util.UUID;

import java.util.ArrayList;
import android.bluetooth.*;
import android.os.*;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.app.*;

/**
 * Information taken from https://android.googlesource.com/platform/development/+/7167a054a8027f75025c865322fa84791a9b3bd1/samples/BluetoothLeGatt/src/com/example/bluetooth/le/DeviceScanActivity.java
 * Created by laurenritter on 3/27/18.
 */

public class LeDeviceListAdapter extends BaseAdapter {
    private ArrayList<BluetoothDevice> leDevices;
    private LayoutInflater inflater;

    public LeDeviceListAdapter(){
        super();
        leDevices = new ArrayList<BluetoothDevice>();
        inflater = DeviceScanActivity.this.getLayoutInflater();
    }

    public void addDevice(BluetoothDevice device){
        if(!leDevices.contains(device)){
            leDevices.add(device);
        }
    }

    public BluetoothDevice getDevice(int position){
        return leDevices.get(position);
    }

    public void clear(){
        leDevices.clear();
    }

    @Override
    public int getCount() {
        return leDevices.size();
    }

    @Override
    public Object getItem(int i){
        return leDevices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        // General ListView optimization code.
        if (view == null) {
            view = inflator.inflate(R.layout.listitem_device, null);
            viewHolder = new ViewHolder();
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        BluetoothDevice device = leDevices.get(i);
        final String deviceName = device.getName();
        if (deviceName != null && deviceName.length() > 0)
            viewHolder.deviceName.setText(deviceName);
        else
            viewHolder.deviceName.setText(R.string.unknown_device);
        viewHolder.deviceAddress.setText(device.getAddress());
        return view;
    }

    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                }
            });
        }
    }
}
