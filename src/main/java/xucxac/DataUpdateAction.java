package xucxac;

import xucxac.data.CurrentRoom;
import xucxac.data.PutMoneyInBoard;
import xucxac.data.RoomManage;
import xucxac.database.entites.PutMoney;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.ListPlayers;
import xucxac.mysql.table.MoneyPuts;
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
        if (PutMoneyInBoard.listPutMoney != null) {
//            List<PutMoney> putMoneyInBoard = MoneyPuts.getDataAll();
            List<PutMoney> putMoneyInBoard = MoneyPuts.getDataInPutMoney(CurrentRoom.informationInRoom.getIdPhong());
            PutMoneyInBoard.listPutMoney.refresh(putMoneyInBoard);
        }
    }

    public static void main(String[] args) {
//            ListPlayers.insert(139489,55);
//        ListPlayers.insert(381934,30);
//        Rooms.add(14, 22, 6);
        MoneyPuts.add(1, 292865, 44);
    }
}