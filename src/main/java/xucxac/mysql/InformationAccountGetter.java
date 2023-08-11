//package xucxac.mysql;
//
//import xucxac.database.entites.Account;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import static xucxac.database.ConnectionUtil.conn;
//
//public class InformationAccountGetter {
//    public static Account getAccount(String useName) {
//        String password = null;
//        int id = 0;
//        String sql = "SELECT * FROM account  WHERE username= ?";
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement(sql);
//            preparedStatement.setString(1, useName);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                id = resultSet.getInt("id");
//                password = resultSet.getString("password");
//                resultSet.close();
//                return new Account(id,useName,password);
//            }
//
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public static void main(String[] args) {
//        Account account = getAccount("77777");
//        System.out.println("players = " + account);
//        System.out.println(account.getId());
//    }
//}
