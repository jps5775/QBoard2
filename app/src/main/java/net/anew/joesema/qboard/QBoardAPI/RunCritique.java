package net.anew.joesema.qboard.QBoardAPI;

/**
 * Created by laurenritter on 4/3/18.
 */

public class RunCritique {
    private SimuBoard simuBoard;
    private String speedEval;
    private String gyroEval;
    private String gForceEval;

    public String runSpeed(){ // We need to get the average data of the most previous run so we can get the data.
        speedEval = "";
        if((simuBoard.getAccelerometer()[1] >= 25) && (simuBoard.getAccelerometer()[1] <= 35)){ // Between 25 and 35 is average top speed
            speedEval = "you fly";
        } else if ((simuBoard.getAccelerometer()[1] > 35)) {
            speedEval = "you super fly";
        } else if(simuBoard.getAccelerometer()[1] < 25){
            speedEval="you're slow and need to change your life";
        }

        return speedEval;
    }

    public String runGyro(){
        gyroEval = "";
        if((simuBoard.getGyroscope()[1] >= 90) && (simuBoard.getGyroscope()[1] <= 270)){
            gyroEval="you're just normal...sucks";
        }else if(simuBoard.getGyroscope()[1] < 90){
            gyroEval="you probably fell on your face";
        }else if(simuBoard.getGyroscope()[1] > 270){
            gyroEval="you're killing the snowboard game.";
        }

        return gyroEval;
    }

    public String runGForce(Device device){
        gForceEval = "";
        if((simuBoard.getGyroscope(device)[1] >= 1) && (simuBoard.getGyroscope(device)[1] <= 2.5)){
            gForceEval = "guess what? you're normal and no one cares what you do";
        }else if(simuBoard.getGyroscope(device)[1] < 1){
            gForceEval = "why are you crawling down the hill?";
        }else if(simuBoard.getGyroscope(device)[1] > 2.5){
            gForceEval = "OKAY showoff";
        }

        return gForceEval;
    }
}
