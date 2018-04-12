package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.anew.joesema.qboard.R;

import static java.lang.Thread.getAllStackTraces;
import static java.lang.Thread.sleep;

public class skipatrolcontacted extends AppCompatActivity {

    private Button backButton;
    Bundle extras;
    Handler handler;
    Runnable alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skipatrolcontacted);
        extras = getIntent().getExtras();

        backButton = findViewById(R.id.backButton);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEmergency();
            }
        });

        alert = new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(skipatrolcontacted.this, AlertSent.class);
                if (extras != null) {
                    intent.putExtra("eNumber", extras.getString("eNumber"));
                }
                startActivity(intent);
                finish();
            }
        };

        handler = new Handler();
        handler.postDelayed(alert, 10000);


    }


    private void cancelEmergency()
    {

        Intent intent = new Intent(skipatrolcontacted.this, Emergency.class);
        if (extras != null) {
            intent.putExtra("eNumber", extras.getString("eNumber"));
        }
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        cancelEmergency();
        handler.removeCallbacks(alert);
    }


}
