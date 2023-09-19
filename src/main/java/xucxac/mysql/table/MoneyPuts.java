package xucxac.mysql.table;

import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Player;
import xucxac.database.entites.PutMoney;
import xucxac.database.entites.RoomUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static xucxac.database.ConnectionUtil.conn;

public class MoneyPuts {
    public static PutMoney getPutMoney(int idPhong) {
        int idPutMoney=0;
        int idCustomer=0;

        String sql = "SELECT * FROM putMoney  WHERE idPhong= ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idPhong);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idPutMoney = resultSet.getInt("idPutMoney");
                idCustomer = resultSet.getInt("idCustomer");

                resultSet.close();
                return new PutMoney(idPutMoney,idPhong,idCustomer);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PutMoney> getDataAll() {
        Connection conn = ConnectionUtil.conn;
        List<PutMoney> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PutMoney(rs.getInt("idPutMoney"),
                        rs.getInt("idPhong"),
                        rs.getInt("idCustomer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<PutMoney> getDataInPutMoney(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<PutMoney> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n" +
                    " where idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new PutMoney(rs.getInt("idPutMoney"),
                        rs.getInt("idPhong"),
                        rs.getInt("idCustomer")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void add(int idPutMoney, int idPhong, int idCustomer) {
        String sql = "INSERT INTO putMoney(idPutMoney, idPhong, idCustomer) VALUES(?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPutMoney);
            pst.setInt(2, idPhong);
            pst.setInt(3, idCustomer);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(int idPhong) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "DELETE FROM putMoney WHERE idPhong = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> getIdRoom() {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idPhong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Integer> getIdCustomer(int idPutMoney,int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n" +
                    " where idPutMoney=? AND idPhong=?\n");
            ps.setInt(1, idPutMoney);
            ps.setInt(2, idPhong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idCustomer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
//        remove(292865);
        System.out.println(getIdCustomer(1,219466));

    }
}
