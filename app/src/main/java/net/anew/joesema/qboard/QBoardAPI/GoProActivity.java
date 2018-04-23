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
    private String[] fpsCodes = new String[]{};
    private String[] fovCodes = new String[]{};
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
            resolutionSeekBar.setMax(14);
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
                        currentRes = "4k";
                        fpsCodes = new String[] {"10", "9", "8"};
                        optionsToast.setText("Set to 4k");
                        fovSeekBar.setEnabled(false);
                        break;
                    case 2: // 4k SuperView
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        currentRes = "4k SuperView";
                        optionsToast.setText("Set to 4k SuperView @ 24 fps Ultra Wide");
                        break;
                    case 3: // 2.7k
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(6);
                        fpsCodes = new String[] {"10", "9", "8", "7", "6", "5"};
                        fovSeekBar.setEnabled(true);
                        fovSeekBar.setMin(1);
                        fovSeekBar.setMax(2);
                        fovCodes = new String[] {"1", "0"};
                        currentRes = "2.7k";
                        optionsToast.setText("Set to 2.7k");
                        break;
                    case 4: // 2.7k SuperView
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(2);
                        fpsCodes = new String[] {"9", "8"};
                        fovSeekBar.setEnabled(false);
                        currentRes = "2.7k SuperView";
                        optionsToast.setText("Set to 2.7k SuperView Ultra Wide");
                        break;
                    case 5: // 2.7k 4:3
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(2);
                        fpsCodes = new String[] {"9", "8"};
                        fovSeekBar.setEnabled(false);
                        currentRes = "2.7k";
                        optionsToast.setText("Set to 2.7k 4:3");
                        break;
                    case 6: // 1440p
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(6);
                        fpsCodes = new String[] {"10", "9", "8", "7", "6", "5"};
                        currentRes = "1440p";
                        optionsToast.setText("Set to 1440p Ultra Wide");
                        fovSeekBar.setEnabled(false);
                        break;
                    case 7: // 1080p
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(7);
                        fpsCodes = new String[] {"10", "9", "8", "7", "6", "5", "1"};
                        currentRes = "1080p";
                        optionsToast.setText("Set to 1080p");
                        fovSeekBar.setEnabled(true);
                        fovSeekBar.setMin(1);
                        fovSeekBar.setMax(3);
                        fovCodes = new String[] {"2", "1", "0"};
                        break;
                    case 8: // 1080p SuperView
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(6);
                        fpsCodes = new String[] {"10", "9", "8", "7", "6", "5"};
                        currentRes = "1080p SuperView";
                        optionsToast.setText("Set to 1080p SuperView Ultra Wide");
                        fovSeekBar.setEnabled(false);
                        break;
                    case 9: // 960p
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(2);
                        fpsCodes = new String[] {"6", "1"};
                        currentRes = "960p SuperView";
                        optionsToast.setText("Set to 960p Ultra Wide");
                        fovSeekBar.setEnabled(false);
                        break;
                    case 10: // 720p (240 fps, narrow fov)
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        currentRes = "720p";
                        optionsToast.setText("Set to 720p @ 240 fps narrow FOV");
                        break;
                    case 11: // 720p (other stuff)
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(5);
                        fpsCodes = new String[] {"9", "8", "6", "5", "1"};
                        currentRes = "720p";
                        optionsToast.setText("Set to 720p");
                        fovSeekBar.setEnabled(true);
                        fovSeekBar.setMin(1);
                        fovSeekBar.setMax(3);
                        fovCodes = new String[] {"2", "1", "0"};
                        break;
                    case 12: // 720p SuperView
                        fpsSeekBar.setEnabled(true);
                        fpsSeekBar.setMin(1);
                        fpsSeekBar.setMax(3);
                        fpsCodes = new String[] {"6", "5", "1"};
                        currentRes = "720p SuperView";
                        optionsToast.setText("Set to 720p Ultra Wide");
                        fovSeekBar.setEnabled(false);
                        break;
                    case 13: // WVGA
                        fpsSeekBar.setEnabled(false);
                        fovSeekBar.setEnabled(false);
                        currentRes = "WVGA(480p)";
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
        fpsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  if(fpsSeekBar.isEnabled() && fpsCodes.length > 0) {
                      sendCommand(URI.create(settingsUri("3", fpsCodes[progress - 1])));
                  }
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

              }
        });

        //fovSeekbar stuff
        fovSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fovSeekBar.isEnabled() && fovCodes.length > 0) {
                    sendCommand(URI.create(settingsUri("4", fpsCodes[progress - 1])));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //Sourced from: https://github.com/KonradIT/CamControl/blob/master/mobile/src/main/java/com/chernowii/camcontrol/camera/goproAPI/Camera.java
    private void sendCommand(URI command) {
        final Request request = new Request.Builder()
                .url(HttpUrl.get(command))
                .build();
    }


    // The method to create the associated URI
    // Sourced from: https://github.com/KonradIT/CamControl/blob/master/mobile/src/main/java/com/chernowii/camcontrol/camera/goproAPI/Constants.java

    public static String settingsUri(String param, String val) {
        return commandURL + "/gp/gpControl/setting/" + param + "/" + val;
    }


}
