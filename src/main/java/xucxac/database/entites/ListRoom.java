package xucxac.database.entites;

import xucxac.database.entites.RoomUser;

import java.util.ArrayList;
import java.util.List;

public class ListRoom {
    private List<RoomUser> roomUsers= new ArrayList<>();

    public List<RoomUser> getRoomUsers() {
        return roomUsers;
    }

    public void setRoomUsers(List<RoomUser> roomUsers) {
        this.roomUsers = roomUsers;
    }

    public void refresh(List<RoomUser> roomUsers) {
        this.roomUsers.clear();
        this.roomUsers.addAll(roomUsers);
    }

            public ListRoom(List<RoomUser> roomUsers) {
        this.roomUsers = roomUsers;
    }
}
