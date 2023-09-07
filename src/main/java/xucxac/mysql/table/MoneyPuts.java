package xucxac.mysql.table;

import xucxac.database.ConnectionUtil;
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

    public static void add(int idPutMoney,int idPhong, int idCustomer) {
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

    public static void main(String[] args) {
        remove(292865);
    }
}
