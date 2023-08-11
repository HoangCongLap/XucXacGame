//package xucxac.mysql;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import xucxac.database.ConnectionUtil;
//import xucxac.database.entites.RoomUser;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class MysqlConnectRooms {
//    public static ObservableList<RoomUser> getDataAllRooms() {
//        Connection conn = ConnectionUtil.conn;
//        ObservableList<RoomUser> list = FXCollections.observableArrayList();
//        try {
//            PreparedStatement ps = conn.prepareStatement("select * from rooms");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new RoomUser(rs.getInt("id"),
//                        rs.getInt("idcustomerOwner"),
//                        rs.getInt("soNguoi")));
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//
//    }
//}
