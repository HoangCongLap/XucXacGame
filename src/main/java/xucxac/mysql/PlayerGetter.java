//package xucxac.mysql;
//
//import xucxac.database.entites.Player;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static xucxac.database.ConnectionUtil.conn;
//
//public class PlayerGetter {
//    public static Player getPlayerId(int idAccount) {
//        int id = 0;
//        String name = null;
//        int cardMoney = 0;
//        int moneyTotal = 0;
//        String gender = null;
//        String sql = "SELECT * FROM customer  WHERE idAccount= ?";
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setInt(1, idAccount);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                id = resultSet.getInt("id");
//                name = resultSet.getString("name");
//                gender = resultSet.getString("gender");
//                cardMoney = resultSet.getInt("CardMoney");
//                moneyTotal = resultSet.getInt("moneyTotal");
//                resultSet.close();
//                return new Player(id, name, gender, cardMoney, moneyTotal, idAccount);
//            }
//
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//}
