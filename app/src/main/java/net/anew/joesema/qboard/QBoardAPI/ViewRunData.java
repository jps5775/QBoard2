package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

import java.util.List;

/**
 * Created by JoeSema on 3/25/18.
 */

public class ViewRunData extends AppCompatActivity {

    private EditText gryoText;
    private EditText accelText;
    private EditText connectedText;
    private Button connectButton;
    private Button genButton;
    private SimuBoard board;
    private Button shareData;

    final Bundle extras = getIntent().getExtras();

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_run_data);

        board = new SimuBoard();

        gryoText = (EditText) findViewById(R.id.etGryo);
        accelText = (EditText)findViewById(R.id.etAccel);
        connectedText = (EditText)findViewById(R.id.etConnect);
        connectButton = (Button)findViewById(R.id.bConnect);
        genButton = (Button) findViewById(R.id.bGenData);
        shareData = (Button) findViewById(R.id.bShareData);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // FIXME: add real device
                //real connection stuff
                // is that what needs done? I fixed the search devices arguments thing but I'm not sure if its right -wesley
                //HashMap<String, String> devicesToConnectTo = board.searchDevices(new HashMap<String, BluetoothDevice>());
                //board.pairPhone();


                //fake connection stuff
                List<Device> list = board.searchDevices();
                for (int i = 0; i < list.size(); i++){
                    Log.v("Test Connections:", " " + list.get(i));
                }

                Log.v("Connected with: ", "" + list.get(0));
                if (board.pairPhone(list.get(0))){
                    connectedText.setText("true");
                }

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

        shareData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRunData.this, ShareData.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });
    }

}
