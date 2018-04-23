package net.anew.joesema.qboard.QBoardAPI;

public class TimeStamp {

    public String gyroData;
    public String accelData;
    public String date;
//Going to make getters and setters
    // Pushes data to firebase with timestamps
    public TimeStamp(String newGyroData, String newAccelData, String newDate){
        gyroData = newGyroData;
        accelData = newAccelData;
        date = newDate;
    }
}
