package net.anew.joesema.qboard.QBoardAPI;

public class TimeStamp {

    private String gyroData;
    private String accelData;
    private String date;
//Going to make getters and setters
    // Pushes data to firebase with timestamps
    public TimeStamp(){

    }
    public TimeStamp(String newGyroData, String newAccelData, String newDate){
        setGyroData(newGyroData);
        setAccelData(newAccelData);
        setDate(newDate);
    }

    public String getGyroData() {
        return gyroData;
    }

    public void setGyroData(String gyroData) {
        this.gyroData = gyroData;
    }

    public String getAccelData() {
        return accelData;
    }

    public void setAccelData(String accelData) {
        this.accelData = accelData;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
