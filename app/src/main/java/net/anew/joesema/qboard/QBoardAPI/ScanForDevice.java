package net.anew.joesema.qboard.QBoardAPI;

import android.Manifest;
import android.app.ListActivity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import net.anew.joesema.qboard.R;

import java.util.*;

/**
 * Information taken from https://www.bignerdranch.com/blog/bluetooth-low-energy-part-1/
 * Created by laurenritter on 3/29/18.
 */

public class ScanForDevice extends AppCompatActivity{
    private boolean scanning;
    private BluetoothAdapter bluetoothAdapter;
    private BtleScanCallback btleScanCallback;
    protected HashMap<String, String> scanResults;
    private Handler handler;
    private BluetoothLeScanner bluetoothLeScanner;
    private BluetoothLeAdvertiser bluetoothLeAdvertiser;
    private BluetoothGattServer gattServer;
    private BluetoothManager bluetoothManager;
    private final static String TAG = ScanForDevice.class.getSimpleName();
    private BluetoothGatt mGatt;

    protected static final int SCAN_PERIOD = 10000;
    private final static int REQUEST_ENABLE_BT = 1;
    private final static int REQUEST_FINE_LOCATION = 2;
    protected final static UUID SERVICE_UUID = UUID.fromString("5154474C-9000-0101-0001-000000000001");


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

    }

    @Override
    protected void onResume(){
        super.onResume();
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
            finish();
            return;
        }
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            finish();
            return;
        }
        if(!bluetoothAdapter.isMultipleAdvertisementSupported()){
            finish();
            return;
        }
        bluetoothLeAdvertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        setupServer();
        startAdvertising();
    }

    private void startAdvertising(){
        if(bluetoothLeAdvertiser == null){
            return;
        }

        AdvertiseSettings settings = new AdvertiseSettings.Builder().setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_BALANCED).setConnectable(true).setTimeout(0).setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_LOW).build();
        ParcelUuid parcelUuid = new ParcelUuid(SERVICE_UUID);
        AdvertiseData advertiseData = new AdvertiseData.Builder().setIncludeDeviceName(true).addServiceUuid(parcelUuid).build();
        bluetoothLeAdvertiser.startAdvertising(settings, advertiseData, advertiseCallback);
    }

    private AdvertiseCallback advertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            super.onStartSuccess(settingsInEffect);
            Log.d(TAG, "Peripheral advertising started.");
        }

        @Override
        public void onStartFailure(int errorCode){
            Log.d(TAG, "Peripheral advertising failed: " + errorCode);
        }
    };

    private void setupServer(){
        BluetoothGattService service = new BluetoothGattService(SERVICE_UUID, BluetoothGattService.SERVICE_TYPE_PRIMARY);
        gattServer.addService(service);
    }

    protected void onPause(){
        super.onPause();
        stopAdvertising();
        stopServer();
    }

    private void stopServer(){
        if(gattServer != null){
            gattServer.close();
        }
    }

    private void stopAdvertising(){
        if(bluetoothLeAdvertiser != null){
            bluetoothLeAdvertiser.stopAdvertising(advertiseCallback);
        }
    }

    public void startScan(){
        if(!hasPermissions() || scanning) {
            return;
        }


        List<ScanFilter> filters = new ArrayList<>();
        ScanSettings settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build();
        scanResults = new HashMap<>();
        btleScanCallback = new BtleScanCallback(scanResults);
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
        bluetoothLeScanner.startScan(filters, settings, btleScanCallback);
        scanning = true;
        handler = new Handler();
        handler.postDelayed(this::stopScan, SCAN_PERIOD);
    }

    private void stopScan(){
        if(scanning && bluetoothAdapter != null && bluetoothAdapter.isEnabled() && bluetoothLeScanner != null){
            bluetoothLeScanner.stopScan(btleScanCallback);
            scanComplete();
        }

        btleScanCallback = null;
        scanning = false;
        handler = null;
    }

    private void scanComplete(){
        if(scanResults.isEmpty()){
            return;
        }
        for(String deviceAddress : scanResults.keySet()){
            Log.d(TAG, "Found device: " + deviceAddress);
        }
    }

    private boolean hasPermissions(){
        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            requestBluetoothEnable();
            return false;
        }else if(!hasLocationPermissions()){
            requestLocationPermission();
            return false;
        }
        return true;
    }

    private void requestBluetoothEnable(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        Log.d(TAG, "Requested user enables Bluetooth. Try starting the scan again.");
    }

    private boolean hasLocationPermissions(){
        return checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission(){
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }

    private void connectDevice(BluetoothDevice device) {
        Log.d(TAG, "Connecting to " + device.getAddress());
        GattClientCallback gattClientCallback = new GattClientCallback();
        mGatt = device.connectGatt(this, false, gattClientCallback);
    }

    public void disconnectGattServer() {
        Log.d(TAG, "Closing Gatt connection");
        if (mGatt != null) {
            mGatt.disconnect();
            mGatt.close();
        }
    }

    private class GattClientCallback extends BluetoothGattCallback {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            Log.d(TAG, "onConnectionStateChange newState: " + newState);

            if (status == BluetoothGatt.GATT_FAILURE) {
                Log.d(TAG, "Connection Gatt failure status " + status);
                disconnectGattServer();
                return;
            } else if (status != BluetoothGatt.GATT_SUCCESS) {
                // handle anything not SUCCESS as failure
                Log.d(TAG, "Connection not GATT sucess status " + status);
                disconnectGattServer();
                return;
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d(TAG, "Connected to device " + gatt.getDevice().getAddress());
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d(TAG,"Disconnected from device");
                disconnectGattServer();
            }
        }
    }

}
