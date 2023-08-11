package xucxac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xucxac.data.CurrentAccount;
import xucxac.data.CurrentUser;
import xucxac.data.RoomManage;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Account;
import xucxac.database.entites.ListRoom;
import xucxac.database.entites.Player;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.Customers;
import xucxac.mysql.table.Rooms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import static xucxac.mysql.table.Accounts.getAccount;


public class LoginController implements Initializable {

    private static final String IFORMATIONCUSTOMER_XML_FILE = "InformationCustomer.fxml";
    private static final String ROOMCREATE_XML_FILE = "RoomCreate.fxml";
    private static final String SIGNUP_XML_FILE = "SignUp.fxml";

    static String id = null;
    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField passWordTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;
//    Stage dialogStage = new Stage();
//    Scene scene;


    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ResultSet resultSett = null;
    Boolean checkLogin=false;

    public LoginController() {
        connection = ConnectionUtil.connectdb();
    }

    public void login(ActionEvent event) throws IOException {
        String username=null;
        String password=null;
        String idAccount = null;
        String userName = userNameTextField.getText();
        String pass = passWordTextField.getText();
//        String userName = "HoangCongLap";
//        String pass = "123";
        String sql = "SELECT * FROM account WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, pass);

//            preparedStatement.setString(3, idAccount);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Wrong User and password", "Failed", null);

            } else {
                username=resultSet.getString("username");
                password=resultSet.getString("password");
                // có thông tin của tài khoản trong CurrentAccount
                Account getAccount = getAccount(userName);
                int idLogin= getAccount.getId();

                Account account= new Account(idLogin,userName,password);
                CurrentAccount.account=account;
                Player player= Customers.getPlayerId(idLogin);
                CurrentUser.player =player;




//             String l=resultSet.getString(idAccount);
//                System.out.println("login:"+l);
//                getPlayerId();
//                infoBox("Login Successfull", "Success", null);
//              username=resultSet.getString("username");
//              password=resultSet.getString("password");
                RoomManage.listRoom= new ListRoom(Rooms.getDataAll());


                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ROOMCREATE_XML_FILE));
                root = loader.load();
//                RoomCreateController scene2Controller = loader.getController();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
//            System.out.format("%s,%s",username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void signUpAtionLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(SIGNUP_XML_FILE));
        root = loader.load();
//        SignUpController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
