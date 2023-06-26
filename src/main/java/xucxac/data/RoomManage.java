package xucxac.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.MysqlConnectRoom;

import java.util.ArrayList;
import java.util.List;

public class RoomManage {
    public static ObservableList<RoomUser> rooms = FXCollections.observableArrayList();

    public void loadDB() {
        ObservableList<RoomUser> dataAllUsers = MysqlConnectRoom.getDataAllUsers();
        rooms.removeAll();
        rooms.addAll(dataAllUsers);
    }

    //5s ->loadDB
}
