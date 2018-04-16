package net.anew.joesema.qboard.QBoardAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.anew.joesema.qboard.R;

import org.w3c.dom.Text;

/**
 * Created by Amber Grace Ebersole on 4/11/2018.
 */

public class ShareData extends AppCompatActivity {
    private SimuBoard simuBoard;
    private static double accelData;
    private static String LayoverText;
    private Button createImage;

    private Button bShare;
    private TextView tOverlay;
    private TextView tShareWith;
    private TextView tHashtag;
    private ImageView iPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_data_view);

        //Set view elements
        bShare = (Button)findViewById(R.id.shareButton);
        tOverlay = (TextView)findViewById(R.id.textView5);
        tShareWith = (TextView)findViewById(R.id.textView3);
        tHashtag = (TextView)findViewById(R.id.textView4);
        iPicture = (ImageView)findViewById(R.id.imageView);


        Bundle extras = getIntent().getExtras();

        createImage = (Button) findViewById(R.id.generateButton);
        //accelData = simuBoard.getAccelerometer()[1];
        LayoverText = "I was shredding at " + accelData + " mph while Snowboarding today!";


        createImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Bitmap bitmap = BitmapFactory.decodeResource(ShareData.this.getResources(), R.drawable.imagebmp);
//                Canvas canvas = new Canvas(bitmap);

                int w = 250, h = 250;

                Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                Bitmap bmp = Bitmap.createBitmap(w, h, conf);
                Canvas canvas = new Canvas(bmp);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setTextSize(12);
                canvas.drawText(LayoverText, 100, 100, paint);

                v.draw(canvas);

                iPicture.setImageDrawable(new BitmapDrawable(getResources(), bmp));
            }
        });
    }

}
