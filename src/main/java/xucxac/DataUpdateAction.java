package xucxac;

import xucxac.data.CurrentRoom;
import xucxac.mysql.table.ListPlayers;

import java.util.List;
import java.util.TimerTask;

public class DataUpdateAction extends TimerTask {
    public void run() {
        if (CurrentRoom.roomUser != null) {
            List<Integer> playersInRoom = ListPlayers.getPlayersInRoom(CurrentRoom.roomUser.getIdPhong());
            CurrentRoom.informationInRoom.refresh(playersInRoom);
        }
    }

    public static void main(String[] args) {
        ListPlayers.insert(310873,22);
    }
}