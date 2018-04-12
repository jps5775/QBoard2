package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.anew.joesema.qboard.R;

public class MainActivity extends AppCompatActivity {

    private Button bScanDevices;
    private Button bRunCritique;
    private Button bViewRunData;
    private Button bViewAggData;
    private Button bEmergency;
    private boolean isConnected = true; // change later data is attained
    private String eNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bScanDevices = (Button)findViewById(R.id.bScanDevices);
        bRunCritique = (Button)findViewById(R.id.bRunCritique);
        bViewRunData = (Button)findViewById(R.id.bViewRunData);
        bViewAggData = (Button)findViewById(R.id.bViewAggregateData);
        bEmergency = findViewById(R.id.bViewEmergencyInfo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eNumber = extras.getString("eNumber");
        }
        bScanDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO: Use only for selecting connections
                Intent intent = new Intent(MainActivity.this, ShowDevices.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });

        bRunCritique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(noConnection()){
                    return;
                }

                Intent intent = new Intent(MainActivity.this, RunCritique.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });

        bViewRunData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(noConnection()){
                    return;
                }

                // TODO: View Run Data
                Intent intent = new Intent(MainActivity.this, ViewRunData.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });

        bViewAggData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(noConnection()){
                    return;
                }

                // TODO: View Agg Data
                Intent intent = new Intent(MainActivity.this, ViewAggData.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });

        bEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Emergency.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });
    }

    private boolean noConnection(){
        if(!isConnected){
            Toast.makeText(MainActivity.this, "Connect to your Device to the Snowboard!",
                    Toast.LENGTH_LONG).show();
            return true;
        }else{
            return false;
        }
    }

}