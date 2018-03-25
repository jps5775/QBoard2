package net.anew.joesema.qboard.QBoardAPI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

public class MainActivity extends AppCompatActivity {

    private EditText gryoText;
    private EditText accelText;
    private EditText connectedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Intent intent = new Intent(this, SimuBoardTest.class);
        startActivity(intent);
    }
}