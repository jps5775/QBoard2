package net.anew.joesema.qboard.QBoardAPI;

import java.util.ArrayList;

public class Run {

    private ArrayList<Packet> packetList;

    public Run()
    {
        packetList = new ArrayList<Packet>();
    }

    public ArrayList<Packet> getPacketList() {
        return packetList;
    }

    public void addPacket(Packet packet)
    {
        packetList.add(packet);
    }
}
