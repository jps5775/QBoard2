package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.anew.joesema.qboard.R;

public class AlertSent extends AppCompatActivity {

    private String eNumber;
    private Button backButton;
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertsentview);

        eNumber = extras.getString("eNumber");
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlertSent.this, Emergency.class);

                startActivity(intent);
            }
        });

    }

    public void sendAlert()
    {
        // do stuff with the emergency number
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("eNumber", extras.getString("eNumber"));
        setResult(RESULT_OK, intent);
        finish();
    }

}
