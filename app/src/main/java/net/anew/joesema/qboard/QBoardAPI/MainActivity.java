package net.anew.joesema.qboard.QBoardAPI;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

public class MainActivity extends AppCompatActivity {

    private Button bTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bTitle = (Button)findViewById(R.id.bTitleStart);

        bTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SimuBoardTest.class);
                startActivity(intent);
            }
        });

    }
}