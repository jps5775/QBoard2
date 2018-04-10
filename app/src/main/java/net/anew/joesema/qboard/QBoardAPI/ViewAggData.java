package net.anew.joesema.qboard.QBoardAPI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

import java.util.List;

public class ViewAggData extends AppCompatActivity {

    private EditText gryoText;
    private EditText accelText;
    private EditText connectedText;
    private Button connectButton;
    private Button genButton;
    private SimuBoard board;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agg_data);

        board = new SimuBoard();

        gryoText = (EditText) findViewById(R.id.etAggGryo);
        accelText = (EditText)findViewById(R.id.etAggAccel);
        connectedText = (EditText)findViewById(R.id.etAggConnect);
        connectButton = (Button)findViewById(R.id.bAggConnect);
        genButton = (Button) findViewById(R.id.bAggGenData);

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
                double[] gData = new double[3];
                double[] aData = new double[3];
                double[] gyroSum = new double[3];
                double[] accelSum = new double[3];
                for (int i = 0; i < 10; i++){
                    gData = board.getGyroscope();
                    aData = board.getAccelerometer();
                    gyroSum[0] = gyroSum[0] + gData[0];
                    gyroSum[1] = gyroSum[1] + gData[1];
                    gyroSum[2] = gyroSum[2] + gData[2];
                    accelSum[0] = accelSum[0] + aData[0];
                    accelSum[1] = accelSum[1] + aData[1];
                    accelSum[2] = accelSum[2] + aData[2];
                }

                gyroSum[0] = gyroSum[0] / 10;
                gyroSum[1] = gyroSum[1] / 10;
                gyroSum[2] = gyroSum[2] / 10;
                accelSum[0] = accelSum[0] / 10;
                accelSum[1] = accelSum[1] / 10;
                accelSum[2] = accelSum[2] / 10;

                String gyroData = "x: " + gyroSum[0] + "\ny: "
                        + gyroSum[1] + "\nz: " + gyroSum[2];
                String accelData = "x: " + accelSum[0] + "\ny: "
                        + accelSum[1] + "\nz: " + accelSum[2];
                gryoText.setText(gyroData);
                accelText.setText(accelData);
            }
        });
    }


}
