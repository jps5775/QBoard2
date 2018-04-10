package net.anew.joesema.qboard.QBoardAPI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

public class ShowDevices extends AppCompatActivity {

    private EditText tvShowDevices;
    private Button bShowDevicesScan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_devices);

        tvShowDevices = (EditText) findViewById(R.id.tvShowDevices);
        bShowDevicesScan = (Button) findViewById(R.id.bShowDevicesScan);

        bShowDevicesScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvShowDevices.setText("true");
            }
        });



    }
}
