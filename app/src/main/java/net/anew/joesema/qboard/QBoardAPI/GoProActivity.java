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
    private String[] fpsCodes;
    private String[] fovCodes;
    private String currentRes;
    private String currentFPS;
    private String currentFOV;
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

        // toast text WILL be changed so if it isn't something messed up
        optionsToast = Toast.makeText(GoProActivity.this, "Something messed up", Toast.LENGTH_LONG);

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
                    sendCommand(URI.create(commandURL + "/gp/gpControl/command/mode?p=0"));
                    // start the recording
                    sendCommand(URI.create(commandURL + "/gp/gpControl/command/shutter"));
                    bRecord.setText("STOP RECORDING");

                }
                else {
                    recording = false;
                    // stop the recording
                    sendCommand(URI.create(commandURL + "/gp/gpControl/command/shutter"));
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
                // set up the other seekbars to be compatible with the selected resolution & generate toast text
                // https://havecamerawilltravel.com/gopro/gopro-hero-4-black-video-photo-modes/
                switch (progress)
                {
                    case 1: // 4k
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(3);
                        fpsCodes = new String[] {"10", "9", "8"};
                        fovSeekBar.setEnabled(false);
                        break;
                    case 2: // 4k SuperView
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        optionsToast.setText("Set to 4k SuperView @ 24 fps Ultra Wide");
                        break;
                    case 3: // 2.7k
                        break;
                    case 4: // 2.7k SuperView

                        fovSeekBar.setEnabled(false);
                        break;
                    case 5: // 2.7k 4:3

                        fovSeekBar.setEnabled(false);
                        break;
                    case 6: // 1440p

                        fovSeekBar.setEnabled(false);
                        break;
                    case 7: // 1080p
                        break;
                    case 8: // 1080p SuperView

                        fovSeekBar.setEnabled(false);
                        break;
                    case 9: // 960p

                        fovSeekBar.setEnabled(false);
                        break;
                    case 10: // 720p (240 fps, narrow fov)
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        optionsToast.setText("Set to 720p @ 240 fps narrow FOV");
                        break;
                    case 11: // 720p (other stuff)
                        break;
                    case 12: // 720p SuperView

                        fovSeekBar.setEnabled(false);
                        break;
                    case 13: // WVGA
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        optionsToast.setText("Set to WVGA(480p) @ 240 fps narrow FOV");
                        break;
                }
                // send the settings to the camera & show text
                sendCommand(URI.create(settingsUri("2", Integer.toString(progress))));
                optionsToast.show();
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

        //fpsSeekbar stuff

        //fovSeekbar stuff

    }

    //Sourced from: https://github.com/KonradIT/CamControl/blob/master/mobile/src/main/java/com/chernowii/camcontrol/camera/goproAPI/Camera.java
    private void sendCommand(URI command)
    {
        final Request request = new Request.Builder()
                .url(HttpUrl.get(command))
                .build();
    }


    // FPS and FOV constants. And the method to create the associated URI
    // Sourced from: https://github.com/KonradIT/CamControl/blob/master/mobile/src/main/java/com/chernowii/camcontrol/camera/goproAPI/Constants.java

    public static String settingsUri(String param, String val) {
        return commandURL + "/gp/gpControl/setting/" + param + "/" + val;
    }

    //FPS
    public static URI FR240fps= URI.create(settingsUri("3","0"));
    public static URI FR120fps= URI.create(settingsUri("3","1"));
    public static URI FR100fps= URI.create(settingsUri("3","2"));
    public static URI FR60fps= URI.create(settingsUri("3","5"));
    public static URI FR50fps= URI.create(settingsUri("3","6"));
    public static URI FR48fps= URI.create(settingsUri("3","7"));
    public static URI FR30fps= URI.create(settingsUri("3","8"));
    public static URI FR25fps= URI.create(settingsUri("3","9"));
    public static URI FR24fps= URI.create(settingsUri("3","10"));
    public static URI FR15fps= URI.create(settingsUri("3","11"));
    public static URI FR12point5fps= URI.create(settingsUri("3","12"));

    //FOV
    public static URI Wide= URI.create(settingsUri("4","0"));
    public static URI Medium= URI.create(settingsUri("4","1"));
    public static URI Narrow= URI.create(settingsUri("4","2"));


}
