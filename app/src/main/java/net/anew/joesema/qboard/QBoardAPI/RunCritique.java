package net.anew.joesema.qboard.QBoardAPI;

import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import net.anew.joesema.qboard.R;

/**
 * Created by laurenritter on 4/3/18.
 * Coded by Lauren Riter and Wesley Mauk
 */

public class RunCritique extends AppCompatActivity {
    private SimuBoard simuBoard;
    private String speedEval;
    private String gyroEval;
    private String gForceEval;
    private double speed;

    private EditText speedAdvice;
    private EditText gyroAdvice;
    private EditText gForceAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.critique_advice);

        speedAdvice = (EditText)findViewById(R.id.speedAdvice);
        gyroAdvice = (EditText)findViewById(R.id.gyroAdvice);
        gForceAdvice = (EditText)findViewById(R.id.gForceAdvice);
        runSpeed();
        runGyro();
        //runGForce(new Device("", true));
    }

    public void runSpeed(){ // We need to get the top speed data of the most previous run so we can get the data.
        // we're setting speed to a static number for now
        // the real way do it would calculate speed from accelerometer data
        // but we're doing static for now to get it okay for submission
        speed = 26;

        speedEval = "";
        if((speed >= 25) && (speed <= 35)){ // Between 25 and 35 is average top speed
            speedEval = "You are achieving the average top snowboarding speed." +
                        "Keep shredding!";
        } else if ((speed > 35)) {
            speedEval = "You are going extremely fast at this point. Ensure you are wearing proper protective gear." +
                        "At this speed, small changes in movement can result in injury.";
        } else if(speed < 25){ // advice for speed found on SnowboardLab
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
        //advice from https://snowboardaddiction.com/blogs/intermediate-riding/17238148-how-to-turn-on-a-snowboard
        gyroEval = "Try using your head to spot in order to make sharper turns. +" +
                "Your core should also be engage so that you can turn the snowboard underneath you.";
        /*if((simuBoard.getGyroscope()[1] >= 90) && (simuBoard.getGyroscope()[1] <= 270)){ //We are saying that the average is between these degrees
            gyroEval="Your rotation is on point.";
        }else if(simuBoard.getGyroscope()[1] < 90){
            gyroEval="you probably fell on your face";
        }else if(simuBoard.getGyroscope()[1] > 270){
            gyroEval="you're killing the snowboard game.";
        }
        */
        gyroAdvice.setText(gyroEval);
    }

    public void runGForce(Device device){
        gForceEval = "";
        if((simuBoard.getGyroscope(device)[1] >= 1) && (simuBoard.getGyroscope(device)[1] <= 2.5)){
            gForceEval = "You're on the right track! Keep tearin' it up!";
        }else if(simuBoard.getGyroscope(device)[1] < 1){
            gForceEval = "Try to distribute your weight evenly on the board. +" +
                    "Sometimes the trail can effect your g forces. Try stepping off the bunny hill.";
        }else if(simuBoard.getGyroscope(device)[1] > 2.5){ //Olympic level
            gForceEval = "You're riding at Olympic level!";
        }

        gForceAdvice.setText(gForceEval);
    }
}
