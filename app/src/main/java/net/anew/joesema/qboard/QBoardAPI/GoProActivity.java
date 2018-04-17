package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.anew.joesema.qboard.R;

/**
 * Created by laurenritter on 4/12/18.
 */

public class GoProActivity extends AppCompatActivity {

    private Button bConnect;
    private Button bRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gopro);

        bConnect = findViewById(R.id.connectButton);
        bRecord = findViewById(R.id.startRecordingButton);

        // HTTP Request stuff

        boolean recording = false;

        bConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just open the android's wifi setting
                startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));

            }
        });

        bRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recording == false) {
                    // start the recording

                }
                else {
                    // stop the recording

                }

            }
        });

    }


}
