package net.anew.joesema.qboard.QBoardAPI;

import android.app.Activity;
import android.os.*;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

/**
 * Created by laurenritter on 4/3/18.
 */

public class RunCritique extends Activity{
    private SimuBoard simuBoard;
    private String speedEval;
    private String gyroEval;
    private String gForceEval;

    private EditText speedAdvice;
    private EditText gyroAdvice;
    private EditText gForceAdvice;

    public void onCreate(Bundle savedInstanceState, Device device){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.critique_advice);

        speedAdvice = findViewById(R.id.speedAdvice);
        gyroAdvice = findViewById(R.id.gyroAdvice);
        gForceAdvice = findViewById(R.id.gForceAdvice);
        runSpeed();
        runGyro();
        runGForce(device);
    }

    public void runSpeed(){ // We need to get the average data of the most previous run so we can get the data.
        speedEval = "";
        if((simuBoard.getAccelerometer()[1] >= 25) && (simuBoard.getAccelerometer()[1] <= 35)){ // Between 25 and 35 is average top speed
            speedEval = "You are achieving the average top snowboarding speed." +
                        "Keep shredding!";
        } else if ((simuBoard.getAccelerometer()[1] > 35)) {
            speedEval = "You are going extremely fast at this point. Ensure you are wearing proper protective gear." +
                        "At this speed, small changes in movement can result in injury.";
        } else if(simuBoard.getAccelerometer()[1] < 25){ // advice for speed found on SnowboardLab
            speedEval="Make sure that you:" +
                    "1. Have both feet fully on the board. If any portion of your feet are sticking out then that will create lag." +
                    "2. Your board may need more wax on it." +
                    "3. Make sure that you are relaxing your knees. The lower you crouch down, the faster you will go." +
                    "4. Riding flat on the snowboard will pick up more traction. You should usually not be touching heelside or toeside. This technique is called riding the edges." +
                    "5. Make sure you are distributing the weight between both of your feet properly.";
        }

        speedAdvice.setText(speedEval);
    }

    public void runGyro(){
        gyroEval = "";
        if((simuBoard.getGyroscope()[1] >= 90) && (simuBoard.getGyroscope()[1] <= 270)){ //We are saying that the average is between these degrees
            gyroEval="Your rotation is on point.";
        }else if(simuBoard.getGyroscope()[1] < 90){
            gyroEval="you probably fell on your face";
        }else if(simuBoard.getGyroscope()[1] > 270){
            gyroEval="you're killing the snowboard game.";
        }

        gyroAdvice.setText(gyroEval);
    }

    public void runGForce(Device device){
        gForceEval = "";
        if((simuBoard.getGyroscope(device)[1] >= 1) && (simuBoard.getGyroscope(device)[1] <= 2.5)){
            gForceEval = "guess what? you're normal and no one cares what you do";
        }else if(simuBoard.getGyroscope(device)[1] < 1){
            gForceEval = "why are you crawling down the hill?";
        }else if(simuBoard.getGyroscope(device)[1] > 2.5){ //Olympic level
            gForceEval = "OKAY showoff";
        }

        gForceAdvice.setText(gForceEval);
    }
}
