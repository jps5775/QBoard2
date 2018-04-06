package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.*;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Information taken from Big Nerd Ranch
 * Created by laurenritter on 4/3/18.
 */

public class GattServerViewModel extends BaseObservable{
    private BluetoothDevice bluetoothDevice;

     public GattServerViewModel (BluetoothDevice device){
         bluetoothDevice = device;
     }

    @Bindable
    public String getServerName(){
         if(bluetoothDevice == null){
             return "";
         }

         return bluetoothDevice.getAddress();
     }
}
