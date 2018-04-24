package net.anew.joesema.qboard.QBoardAPI;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.anew.joesema.qboard.R;

import java.util.ArrayList;
import java.util.List;


public class ViewAggList extends AppCompatActivity {

    public DatabaseReference databaseTimeStamps;
    private String eNumber;
    ListView listViewTimeStamps;
    List<TimeStamp> timeStampList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agg_list);
        databaseTimeStamps = FirebaseDatabase.getInstance().getReference("timestamps");

        listViewTimeStamps = (ListView) findViewById(R.id.listViewTimeStamps);

        timeStampList = new ArrayList<>();

        final Bundle extras = getIntent().getExtras();
        if (extras != null) {
            eNumber = extras.getString("eNumber");
            Toast.makeText(ViewAggList.this, extras.getString("eNumber"),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseTimeStamps.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                timeStampList.clear();

                for(DataSnapshot timeStampSnapshot : dataSnapshot.getChildren()){
                    TimeStamp timeStamp = timeStampSnapshot.getValue(TimeStamp.class);

                    timeStampList.add(timeStamp);
                }

                TimeStampList adapter = new TimeStampList(ViewAggList.this, timeStampList);
                listViewTimeStamps.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
