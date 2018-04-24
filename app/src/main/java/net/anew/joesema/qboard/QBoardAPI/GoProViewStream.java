package net.anew.joesema.qboard.QBoardAPI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import net.anew.joesema.qboard.R;

import java.io.IOException;
import java.net.URI;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GoProViewStream extends AppCompatActivity {

    private VideoView videoView;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gopro_livefeed);

        videoView = findViewById(R.id.goproVideoView);

        //start the stream
        final Request request = new Request.Builder()
                .url(HttpUrl.get(URI.create("http://10.5.5.9/gp/gpControl/execute?p1=gpStream&a1=proto_v2&c1=restart")))
                .build();
        try {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        videoView.setVideoURI(Uri.parse("udp//10.5.5.9:8554"));
        videoView.start();


    }
}
