package net.anew.joesema.qboard.QBoardAPI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import net.anew.joesema.qboard.R;

/**
 * Created by Amber Grace Ebersole on 4/11/2018.
 */

public class ShareData extends AppCompatActivity {
    private SimuBoard simuBoard;
    private static double accelData;
    private static String LayoverText;
    private Button createImage;

    //share_data_view view = new share_data_view(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_data_view);

        createImage = (Button) findViewById(R.id.generateButton);

        createImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = BitmapFactory.decodeResource(ShareData.this.getResources(), R.drawable.imagebmp);

                //Bitmap bitmap = new bitmap (..\res\ShareImage.bmp); // Load your bitmap here
                Canvas canvas = new Canvas(bitmap);
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                paint.setTextSize(12);
                canvas.drawText(LayoverText, 100, 100, paint);

                v.draw(canvas);
            }
        });
    }
    public void main(String[] args) throws Exception {
        accelData = simuBoard.getAccelerometer()[1];
        LayoverText = "I was shredding at " + accelData + " mph while Snowboarding today!";

        //static Bitmap = createBitmap(Picture source, int 230, int 2300, Bitmap.Config ARGB_8888);


        /**final BufferedImage image = ImageIO.read(new URL( "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(LayoverText, 100, 100);
        g.dispose();
        ImageIO.write(image, "png", new File("test.png"));**/
    }

}
