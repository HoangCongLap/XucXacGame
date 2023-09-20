package xucxac.mysql.table;

import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Dice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static xucxac.database.ConnectionUtil.conn;

public class Dices {
    public static List<Dice> getDataAll(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        List<Dice> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  dice\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Dice(rs.getInt("idPhong"),
                        rs.getInt("numberInDice"),
                        rs.getBoolean("ClickInRoll")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void add(int idPhong, int numberInDice,Boolean ClickInRoll ) {
        String sql = "INSERT INTO dice(idPhong, numberInDice,ClickInRoll) VALUES(?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.setInt(2, numberInDice);
            pst.setBoolean(3, ClickInRoll);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void update(int idPhong,Boolean ClickInRoll) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "UPDATE dice SET ClickInRoll = ? WHERE idPhong = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, ClickInRoll);
            pst.setInt(2, idPhong);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int getClickInRoll(int idPhong) {
        Connection conn = ConnectionUtil.conn;
       int clickInRoll=-1;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  dice\n" +
                    " where idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                clickInRoll=rs.getInt("ClickInRoll");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clickInRoll;
    }

    public static int getNumberInDice(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        int numberInDice=-1;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  dice\n" +
                    " where idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numberInDice=rs.getInt("numberInDice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberInDice;
    }
    public static void remove(int idPhong) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "DELETE FROM dice WHERE idPhong = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        add(406537,true);
        remove(900166);
    }
}
