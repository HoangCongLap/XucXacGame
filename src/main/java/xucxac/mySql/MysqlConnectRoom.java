package xucxac.mySql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.ConnectionUtil;
import xucxac.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlConnectRoom {
    public static ObservableList<User> getDataAllUsers() {
        Connection conn = ConnectionUtil.conn;
        ObservableList<User> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from rooms");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getString("idPhong"),
                        rs.getString("tenPhong"),
                        rs.getString("soNguoi")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;

    }
}
