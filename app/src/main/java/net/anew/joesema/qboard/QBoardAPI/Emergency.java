package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.anew.joesema.qboard.R;

// Class coded by Wesley Mauk

public class Emergency extends AppCompatActivity {

    protected String currentNumber;

    private EditText emergencyNumber;
    private Button bSaveNumber;
    private Button bUseLastNumber;
    private Button bSendSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_emergency_signal);

        // import the buttons and text fields
        emergencyNumber = findViewById(R.id.editText2);
        bSaveNumber = findViewById(R.id.button4);
        bUseLastNumber = findViewById(R.id.useLastNumber);
        bSendSignal = findViewById(R.id.button5);

        // Joe's intent thing
        final Bundle extras = getIntent().getExtras();

        //button stuff
        bUseLastNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(extras != null)
                {
                    emergencyNumber.setText(extras.getString("eNumber"));
                    saveNumber();
                }
                else
                {
                    Toast.makeText(Emergency.this, "There is no previously used emergency number!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        bSaveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumber();
            }
        });

        bSendSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Emergency.this, skipatrolcontacted.class);
                intent.putExtra("eNumber", currentNumber);
                startActivity(intent);
            }
        });

    }

    private void saveNumber()
    {
        currentNumber = emergencyNumber.getText().toString();
        Toast.makeText(Emergency.this, "Number Saved: " + currentNumber,
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Emergency.this, MainActivity.class);
        intent.putExtra("eNumber", currentNumber);
        startActivity(intent);
        //super.onBackPressed();
    }



}
