package xucxac.mysql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnectPlayer {
    public static ObservableList<Player> getDataAllCustomer() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<Player> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from customer");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Player(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getInt("cardMoney"),
                        rs.getInt("moneyTotal"),
                        rs.getInt("idAccount")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(getDataAllCustomer());
    }
}
