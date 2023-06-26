package xucxac.database;

import xucxac.database.entites.Player;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static xucxac.database.ConnectionUtil.conn;

public class PlayerDatabase {
    public static Player getPlayers(int idAccount) {
        int id = 0;
        String name = null;
        int cardMoney = 0;
        int moneyTotal = 0;
        String gender = null;
        String sql = "SELECT * FROM customer  WHERE id=?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, idAccount);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                gender = resultSet.getString("gender");
                cardMoney = resultSet.getInt("CardMoney");
                moneyTotal = resultSet.getInt("moneyTotal");
                resultSet.close();
                return new Player(id, name, gender, cardMoney, moneyTotal,idAccount);
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public static void insertCustomer(Player player ){
        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO customer(id, name,gender,cardMoney,idAccount) VALUES(?,?,?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, player.getId());
            pst.setString(2, player.getName());
            pst.setString(3,player.getGender());
            pst.setInt(4,player.getCardMoney());
            pst.setInt(5,player.getIdAccount());
            JOptionPane.showConfirmDialog(null, "succes");
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        Player players = getPlayers(1);
//        System.out.println("players = " + players);
//    }
//public static void main(String[] args) {
//    Player player = new Player(10, "test", "trai", 0, 0, 403465);
//    insertCustomer(player);
//    }
}