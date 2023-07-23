package xucxac.mysql;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.InformationInRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class PlayerListInformation {
    static PreparedStatement preparedStatement = null;

    public static ObservableList<InformationInRoom> getDataListPlayer(int idPhong) {
        Connection conn = ConnectionUtil.conn;
        ObservableList<InformationInRoom> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM  listPlayers\n" +
                    " where listPlayers.idPhong=?\n");
            ps.setString(1, String.valueOf(idPhong));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new InformationInRoom(rs.getInt("idphong"),
                        Collections.singletonList(rs.getInt("idcustomer"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(PlayerListInformation.getDataListPlayer(222));
    }
}
