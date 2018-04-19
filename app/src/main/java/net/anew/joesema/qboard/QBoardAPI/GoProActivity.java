package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import net.anew.joesema.qboard.R;

import java.net.URI;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by laurenritter on 4/12/18.
 * Coded by Wesley Mauk
 */

public class GoProActivity extends AppCompatActivity {

    private Button bConnect;
    private Button bRecord;
    private Button bViewStream;
    private SeekBar resolutionSeekBar;
    private SeekBar fpsSeekBar;
    private SeekBar fovSeekBar;
    private Toast optionsToast;
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
        resolutionSeekBar = findViewById(R.id.resolutionSeekBar);
            resolutionSeekBar.setMin(1);
            resolutionSeekBar.setMax(13);
        fpsSeekBar = findViewById(R.id.fpsSeekBar);
        fovSeekBar = findViewById(R.id.fieldSeekBar);


        optionsToast = Toast.makeText(GoProActivity.this, "Settings: ", Toast.LENGTH_LONG);

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

        resolutionSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // set up the other seekbars to be compatible with the selected resolution
                // https://havecamerawilltravel.com/gopro/gopro-hero-4-black-video-photo-modes/
                switch (progress)
                {
                    case 1: // 4k
                        break;
                    case 2: // 4k SuperView
                        break;
                    case 3: // 2.7k
                        break;
                    case 4: // 2.7k SuperView
                        break;
                    case 5: // 2.7k 4:3
                        break;
                    case 6: // 1440p
                        break;
                    case 7: // 1080p
                        break;
                    case 8: // 1080p SuperView
                        break;
                    case 9: // 960p
                        break;
                    case 10: // 720p (240 fps, narrow fov)
                        break;
                    case 11: // 720p (other stuff)
                        break;
                    case 12: // 720p SuperView
                        break;
                    case 13: // WGVA
                        break;
                }
                // send the settings to the camera & show a toast about the updated settings
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //
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
