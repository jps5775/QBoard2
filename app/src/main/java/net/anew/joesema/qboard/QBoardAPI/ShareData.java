package net.anew.joesema.qboard.QBoardAPI;

import android.support.v7.app.AppCompatActivity;

import android.graphics.*;

import net.anew.joesema.qboard.R;

import java.io.File;
import java.net.URL;

import static android.graphics.Bitmap.Config.ARGB_8888;

/**
 * Created by Amber Grace Ebersole on 4/11/2018.
 */

public class ShareData extends AppCompatActivity {
    private SimuBoard simuBoard;
    private static double accelData;
    private static String LayoverText;


    public void main(String[] args) throws Exception {
        accelData = simuBoard.getAccelerometer()[1];
        LayoverText = "I was shredding at " + accelData + " mph while Snowboarding today!";

        //static Bitmap = createBitmap(Picture source, int 230, int 2300, Bitmap.Config ARGB_8888);

        Bitmap bm = BitmapFactory.decodeResource(getResourses(), R.drawable.imagebmp);

        //Bitmap bitmap = new bitmap (..\res\ShareImage.bmp); // Load your bitmap here
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(10);
        canvas.drawText(LayoverText, x, y, paint);


        /**final BufferedImage image = ImageIO.read(new URL( "http://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png"));

        Graphics g = image.getGraphics();
        g.setFont(g.getFont().deriveFont(30f));
        g.drawString(LayoverText, 100, 100);
        g.dispose();
        ImageIO.write(image, "png", new File("test.png"));**/
    }
}
