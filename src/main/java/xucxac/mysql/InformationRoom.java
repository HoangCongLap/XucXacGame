package xucxac.mysql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.InformationInRoom;
import xucxac.database.entites.RoomUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InformationRoom {
    public static ObservableList<RoomUser> getDataAllRoom(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        ObservableList<RoomUser> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  rooms\n" +
                    " where rooms.id=?");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomUser(rs.getString("id"),
                        rs.getInt("idcustomerOwner"),
                        rs.getInt("soNguoi")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

//    public static void main(String[] args) {
//        InformationRoom.getDataAllRoom(222);
//        System.out.println(InformationRoom.getDataAllRoom(222));
//    }
}
