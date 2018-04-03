package net.anew.joesema.qboard.QBoardAPI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by JoeSema on 3/25/18.
 */

public class SimuBoardTest extends AppCompatActivity {

    private EditText gryoText;
    private EditText accelText;
    private EditText connectedText;
    private Button connectButton;
    private Button genButton;
    private SimuBoard board;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simuboard_test);

        board = new SimuBoard();

        gryoText = (EditText) findViewById(R.id.etGryo);
        accelText = (EditText)findViewById(R.id.etAccel);
        connectedText = (EditText)findViewById(R.id.etConnect);
        connectButton = (Button)findViewById(R.id.bConnect);
        genButton = (Button) findViewById(R.id.bGenData);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME: add real device
                //real connection stuff
                HashMap<String, String> devicesToConnectTo = board.searchDevices();
                board.pairPhone();


                //fake connection stuff
                /*List<Device> list = board.searchDevices();
                for (int i = 0; i < list.size(); i++){
                    Log.v("Test Connections:", " " + list.get(i));
                }

                Log.v("Connected with: ", "" + list.get(0));
                if (board.pairPhone(list.get(0))){
                    connectedText.setText("true");
                }*/

            }
        });

        genButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // FIXME: add real device

                double[] gData = board.getGyroscope();
                double[] aData = board.getAccelerometer();
                String gyroData = "x: " + gData[0] + "\ny: "
                        + gData[1] + "\nz: " + gData[2];
                String accelData = "x: " + aData[0] + "\ny: "
                        + aData[1] + "\nz: " + aData[2];
                gryoText.setText(gyroData);
                accelText.setText(accelData);
            }
        });
    }

}
