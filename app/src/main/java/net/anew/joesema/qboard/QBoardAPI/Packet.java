package net.anew.joesema.qboard.QBoardAPI;


import java.util.Calendar;
import java.util.Date;

public class Packet {

    private double[] gyro;
    private double[] accel;
    private double temp;
    private Date time;

    public Packet(double[] gyro, double[] accel, double temp)
    {
        for(int i = 0; i < 3; i++)
        {
            this.getGyro()[i] = gyro[i];
            this.getAccel()[i] = accel[i];
        }
        this.setTemp(temp);
        setTime(Calendar.getInstance().getTime());
    }

    public double[] getGyro() {
        return gyro;
    }

    public void setGyro(double[] gyro) {
        this.gyro = gyro;
    }

    public double[] getAccel() {
        return accel;
    }

    public void setAccel(double[] accel) {
        this.accel = accel;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
