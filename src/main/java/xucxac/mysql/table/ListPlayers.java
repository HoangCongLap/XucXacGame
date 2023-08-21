package xucxac.mysql.table;

import xucxac.database.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListPlayers {
    static PreparedStatement preparedStatement = null;

    public static List<Integer> getPlayersInRoom(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  listPlayers\n" +
                    " where listPlayers.idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idcustomer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    // ghi xuống Dbase danh sách người chơi có trong 1 phòng
    public static void insert(int idPhong, int idcustomer) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO listPlayers(idPhong, idcustomer) VALUES(?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.setInt(2, idcustomer);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(int idcustomer) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "DELETE FROM listPlayers WHERE idcustomer = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idcustomer);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void removeAll(int idPhong) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "DELETE FROM listPlayers WHERE idPhong = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getNumberOfPlayersInRoom(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  listPlayers\n" +
                    " where listPlayers.idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idcustomer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list.size();
    }
    public static void main(String[] args) {
        System.out.println(ListPlayers.getNumberOfPlayersInRoom(972302));
//        ListPlayers.removeAll(373105);

    }
}
