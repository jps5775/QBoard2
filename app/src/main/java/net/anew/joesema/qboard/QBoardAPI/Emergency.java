package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

public class Emergency extends AppCompatActivity {

    private EditText emergencyNumber;
    private Button bSaveNumber;
    private Button bUseLastNumber;
    private Button bSendSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_emergency_signal);

        // import the buttons and text fields
        Intent intent = new Intent(Emergency.this, MainActivity.class);
    }



}
