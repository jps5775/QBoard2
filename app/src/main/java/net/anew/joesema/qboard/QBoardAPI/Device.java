package net.anew.joesema.qboard.QBoardAPI;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by JoeSema on 3/20/18.
 */

/**
 * This simulates a device with bluetooth connectability
 */

public class Device {

    private String serviceUUID;
    public byte[] payload;
    private boolean isPaired;

    public Device(String serviceUUID, boolean isPaired){
        this.serviceUUID = serviceUUID;
        this.isPaired = isPaired;
    }

    public String getServiceUUID() {
        return serviceUUID;
    }

    public void setServiceUUID(String serviceUUID) {
        this.serviceUUID = serviceUUID;
    }

    public boolean isPaired() {
        return isPaired;
    }

    public void setPaired(boolean paired) {
        isPaired = paired;
    }

}
