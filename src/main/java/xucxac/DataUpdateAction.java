package xucxac;

import xucxac.data.CurrentRoom;
import xucxac.data.RoomManage;
import xucxac.database.entites.ListRoom;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.ListPlayers;
import xucxac.mysql.table.Rooms;

import java.util.List;
import java.util.TimerTask;

public class DataUpdateAction extends TimerTask {
    public void run() {
        if (CurrentRoom.roomUser != null) {
            List<Integer> playersInRoom = ListPlayers.getPlayersInRoom(CurrentRoom.roomUser.getIdPhong());
            CurrentRoom.informationInRoom.refresh(playersInRoom);
        }
        if (RoomManage.listRoom != null) {
            List<RoomUser> roomNumber = Rooms.getDataAll();
            RoomManage.listRoom.refresh(roomNumber);
        }
    }

    public static void main(String[] args) {
//        ListPlayers.insert(310873,22);
        Rooms.add(10275, 22, 6);
    }
}