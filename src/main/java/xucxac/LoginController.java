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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

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

    public LoginController() {
        connection = ConnectionUtil.connectdb();
    }

    public void login(ActionEvent event) throws IOException {
        String userName = userNameTextField.getText();
        String pass = passWordTextField.getText();
//        String userName = "HoangCongLap";
//        String pass = "123";
        String sql = "SELECT * FROM account WHERE username = ? and password = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                infoBox("Wrong user and password", "Failed", null);
            } else {
//                infoBox("Login Successfull", "Success", null);
//
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("InformationCustomer.fxml"));
                root = loader.load();
                InformationCustomer scene2Controller = loader.getController();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void signUpAtionLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("SignUp.fxml"));
        root = loader.load();
        SignUpController scene1Controller = loader.getController();
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
