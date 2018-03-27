package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

public class MainActivity extends AppCompatActivity {

    private EditText gryoText;
    private EditText accelText;
    private EditText connectedText;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = new Intent(this, SimuBoardTest.class);
        startActivity(intent);


    }

    //information taken from Android Developer Website
    public void setBluetoothAdapter(){
        //initializes the bluetooth adapter
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        if(bluetoothAdapter == null || !bluetoothAdapter.isEnabled()){
            Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetoothIntent, REQUEST_ENSBLE_BT);
        }
    }
}