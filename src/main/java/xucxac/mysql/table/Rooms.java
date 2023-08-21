package xucxac.mysql.table;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Player;
import xucxac.database.entites.RoomUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static xucxac.database.ConnectionUtil.conn;

public class Rooms {
    public static List<RoomUser> getDataAll() {
        Connection conn = ConnectionUtil.conn;
        List<RoomUser> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  rooms\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomUser(rs.getInt("id"),
                        rs.getInt("idcustomerOwner"),
                        rs.getInt("soNguoi")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void add(int idPhong, int idCustomerOwner, int limitPlayerInRoom) {
        String sql = "INSERT INTO rooms(id, idcustomerOwner, soNguoi) VALUES(?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.setInt(2, idCustomerOwner);
            pst.setInt(3, limitPlayerInRoom);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(int idcustomerOwner) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "DELETE FROM rooms WHERE idcustomerOwner = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idcustomerOwner);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static RoomUser getRooms() {
        int id = 0;
        int idCustomerOwner = 0;
        int soNguoi = 0;
        String sql = "SELECT * FROM rooms";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                idCustomerOwner = resultSet.getInt("idcustomerOwner");
                soNguoi = resultSet.getInt("soNguoi");

                resultSet.close();
                return new RoomUser(id, idCustomerOwner, soNguoi);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Integer getLimitPlayer(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        int limitPlayer = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  rooms\n" +
                    " where rooms.id=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                limitPlayer = rs.getInt("soNguoi");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return limitPlayer;
    }


    public static void main(String[] args) {
//        InformationRoom.getDataAllRoom(222);
//        System.out.println(InformationRoom.getDataAllRoom(222));
        System.out.println(getLimitPlayer(972302));
    }
}
