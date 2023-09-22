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
        int idPutMoney = 0;
        int idCustomer = 0;
        int taiOrXiu = 0;

        String sql = "SELECT * FROM putMoney  WHERE idPhong= ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idPhong);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                idPutMoney = resultSet.getInt("idPutMoney");
                idCustomer = resultSet.getInt("idCustomer");
                taiOrXiu = resultSet.getInt("taiOrXiu");

                resultSet.close();
                return new PutMoney(idPutMoney, idPhong, idCustomer, taiOrXiu);
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
                        rs.getInt("idCustomer"),
                        rs.getInt("taiOrXiu")));
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
                        rs.getInt("idCustomer"),
                        rs.getInt("taiOrXiu")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void add(int idPutMoney, int idPhong, int idCustomer, int taiOrXiu) {
        String sql = "INSERT INTO putMoney(idPutMoney, idPhong, idCustomer,taiOrXiu) VALUES(?,?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPutMoney);
            pst.setInt(2, idPhong);
            pst.setInt(3, idCustomer);
            pst.setInt(4, taiOrXiu);
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


    public static List<Integer> getNumberPutMoney(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n" +
                    " where idPhong=?\n");
            ps.setInt(1, idPhong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("idCustomer"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Integer> getListTaiOrXiu(int idPhong, int taiOrXiu) {
        Connection conn = ConnectionUtil.conn;
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  putMoney\n" +
                    " where idPhong=? and taiOrXiu=?\n");
            ps.setInt(1, idPhong);
            ps.setInt(2, taiOrXiu);
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
//        System.out.println(getNumberPutMoney(247717).size());
        System.out.println(getListTaiOrXiu(161785,1).size());

    }
}
