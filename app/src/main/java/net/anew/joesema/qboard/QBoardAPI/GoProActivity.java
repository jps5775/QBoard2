package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.anew.joesema.qboard.R;

import java.net.URI;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by laurenritter on 4/12/18.
 */

public class GoProActivity extends AppCompatActivity {

    private Button bConnect;
    private Button bRecord;
    private Button bViewStream;
    boolean recording = false;
    // HTTP Request stuff
    private static final String commandURL = "http://10.5.5.9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gopro);

        bConnect = findViewById(R.id.connectButton);
        bRecord = findViewById(R.id.startRecordingButton);
        bViewStream = findViewById(R.id.viewFeedButton);

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
                    recording = true;
                    //make sure the camera is in video mode
                    sendCommand(URI.create(commandURL + "/command/mode?p=0"));
                    // start the recording
                    sendCommand(URI.create(commandURL + "/command/shutter"));
                    bRecord.setText("STOP RECORDING");

                }
                else {
                    recording = false;
                    // stop the recording
                    sendCommand(URI.create(commandURL + "/command/shutter"));
                    bRecord.setText("START RECORDING");
                }

            }
        });

        bViewStream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just open the android's wifi setting
                startActivity(new Intent(GoProActivity.this, GoProViewStream.class));

            }
        });

    }


    //Sourced from: https://github.com/KonradIT/CamControl/blob/master/mobile/src/main/java/com/chernowii/camcontrol/camera/goproAPI/Camera.java
    private void sendCommand(URI command)
    {
        final Request request = new Request.Builder()
                .url(HttpUrl.get(command))
                .build();
    }

}
