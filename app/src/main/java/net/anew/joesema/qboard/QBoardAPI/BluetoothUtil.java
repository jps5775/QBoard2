package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.*;
import android.support.annotation.Nullable;

import java.util.*;


/**
 * Created by laurenritter on 4/3/18.
 */

public class BluetoothUtil {
    public static List<BluetoothGattCharacteristic> findCharacteristics(BluetoothGatt bluetoothGatt){
        List<BluetoothGattCharacteristic> matchingCharacteristics = new ArrayList<>();
        List<BluetoothGattService> serviceList = bluetoothGatt.getServices();
        BluetoothGattService btGattService = BluetoothUtil.findService(serviceList);
        if(btGattService == null){
            return matchingCharacteristics;
        }

        List<BluetoothGattCharacteristic> characteristicList = btGattService.getCharacteristics();
        for (BluetoothGattCharacteristic characteristic : characteristicList) {
            if (isMatchingCharacteristic(characteristic)) {
                matchingCharacteristics.add(characteristic);
            }
        }

        return  matchingCharacteristics;
    }

    @Nullable
    public static BluetoothGattCharacteristic findEchoCharacteristic(BluetoothGatt bluetoothGatt){
        return findCharacteristic(bluetoothGatt, CHARACTERISTIC_ECHO_STRING);
    }
}
