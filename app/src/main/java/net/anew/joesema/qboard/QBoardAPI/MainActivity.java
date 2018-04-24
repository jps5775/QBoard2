package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import net.anew.joesema.qboard.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton bScanDevices;
    private ImageButton bRunCritique;
    private ImageButton bViewRunData;
    private ImageButton bViewAggData;
    //private Button bShareData;
    private ImageButton bEmergency;
    private ImageButton bGoPro;
    private boolean isConnected = true; // change later data is attained
    private String eNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        bScanDevices = (ImageButton)findViewById(R.id.bScanDevices);
        bRunCritique = (ImageButton)findViewById(R.id.bRunCritique);
        bViewRunData = (ImageButton)findViewById(R.id.bViewRunData);
        bViewAggData = (ImageButton)findViewById(R.id.bViewAggregateData);
        //bShareData = (Button)findViewById(R.id.bShareData);
        bEmergency = (ImageButton)findViewById(R.id.bViewEmergencyInfo);
        bGoPro = (ImageButton)findViewById(R.id.bGoProButton);
        
        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eNumber = extras.getString("eNumber");
            Toast.makeText(MainActivity.this, extras.getString("eNumber"),
                    Toast.LENGTH_LONG).show();
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

        bGoPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoProActivity.class);
                if(extras != null)
                {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eNumber = extras.getString("eNumber");
            // testing toast
            Toast.makeText(MainActivity.this, extras.getString("eNumber"),
                    Toast.LENGTH_LONG).show();

        }

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