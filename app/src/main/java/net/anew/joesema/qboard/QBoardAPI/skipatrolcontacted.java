package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.anew.joesema.qboard.R;

import static java.lang.Thread.getAllStackTraces;
import static java.lang.Thread.sleep;

public class skipatrolcontacted extends AppCompatActivity {

    private Button backButton;
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skipatrolcontacted);

        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelEmergency();
            }
        });

        try {
            sleep(10000);
            Intent intent = new Intent(skipatrolcontacted.this, AlertSent.class);
            if (extras != null) {
                intent.putExtra("eNumber", extras.getString("eNumber"));
            }
            startActivity(intent);
        }
        catch (Exception e)
        {
            cancelEmergency();
        }

    }


    private void cancelEmergency()
    {
        Intent intent = new Intent(skipatrolcontacted.this, Emergency.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("eNumber", extras.getString("eNumber"));
        setResult(RESULT_OK, intent);
        finish();
    }

}
