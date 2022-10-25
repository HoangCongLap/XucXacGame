package xucxac;

import javafx.fxml.FXML;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    public TextField userNameTextField;

    @FXML
    private TextField passWordTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent event) throws IOException {
        String userName = userNameTextField.getText();
        String pass = passWordTextField.getText();
        if (!userName.equals("") && !pass.equals("")) {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BoardGame.fxml"));
            root = loader.load();

            BoardGameController scene2Controller = loader.getController();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("'UserName' hoặc 'PassWord' chưa đúng");
            alert.show();
        }
    }
}