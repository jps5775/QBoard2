package net.anew.joesema.qboard.QBoardAPI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import net.anew.joesema.qboard.R;


/**
 * Created by JoeSema on 3/31/18.
 */

public class GraphTest extends AppCompatActivity{

    LineGraphSeries<DataPoint> series;
    SimuBoard board;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_test);

        board = new SimuBoard();
        board.pairPhone(new Device("id", false));

        double x, y;
        x = 0;
        GraphView graph = (GraphView) findViewById(R.id.graphTest);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 100; i++){
            double[] array = board.getAccelerometer();
            x = x + .1;
            y = array[0];
            series.appendData(new DataPoint(x,y), true, 100);
        }
        graph.addSeries(series);
    }
}
