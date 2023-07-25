package xucxac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xucxac.consts.BoardGameConsts;
import xucxac.database.ConnectionUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    private static final String LOGIN_XML_FILE = "Login.fxml";
    private static final String INFORMATIONCUSTOMER_XML_FILE = "InformationCustomer.fxml";
//    public static int idAccount=BoardGameConsts.ranDomIdPhong();
    @FXML
    private Button SignUpButton;

    @FXML
    private PasswordField signUpPassWord;

    @FXML
    private TextField signUpUserName;

    @FXML
    private PasswordField signUpComfirmPassWord;

    private Stage stage;
    private Scene scene;
    private Parent root;
    static int idAccount=0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       idAccount=BoardGameConsts.ranDomIdPhong();
    }

    @FXML
    public void signUp( ActionEvent event) {
        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO account(username, password,id) VALUES(?,?,?);\n";
        StringBuilder sb = new StringBuilder();
        System.out.println("id Account:"+idAccount);
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, signUpUserName.getText());
            pst.setString(2, signUpPassWord.getText());
            pst.setString(3, String.valueOf(idAccount));
//  kiểm tra userName đã điền thông tin chưa
            if (signUpUserName.getText().equals("")) {
                signUpUserName.setStyle("-fx-border-color: red;");
                showAlert("Usename is required!\n", "", null);
                signUpUserName.requestFocus();

            } else {
                signUpUserName.setStyle("-fx-border-color: CFCFCF;");
            }
//  kiểm tra password đã điền thông tin chưa
            String password = new String(signUpPassWord.getText());
            if (signUpPassWord.getText().equals("")) {
                signUpPassWord.setStyle("-fx-border-color: red;");
                showAlert("Password is required!\n", "", null);
//                focus đến vị trí chưa nhập
                signUpPassWord.requestFocus();

            } else {
                signUpComfirmPassWord.setStyle("-fx-border-color: CFCFCF;");
            }
            //  kiểm tra confirmPassword đã điền thông tin chưa
            String confirm = new String(signUpComfirmPassWord.getText());
            if (!password.equals("") && !password.equals(confirm)) {
                signUpComfirmPassWord.getText();
                signUpPassWord.setStyle("-fx-border-color: red;");
                signUpComfirmPassWord.setStyle("-fx-border-color: red;");
                showAlert("Password and ConfirmPass must be the same!\n", "", null);
                signUpPassWord.requestFocus();
            } else {
                signUpPassWord.setStyle("-fx-border-color: CFCFCF;");
                signUpComfirmPassWord.setStyle("-fx-border-color: CFCFCF;");
            }

            if (!signUpUserName.getText().equals("") && !signUpPassWord.getText().equals("") && !signUpComfirmPassWord.getText().equals("") && password.equals(confirm)) {
//                JOptionPane.showConfirmDialog(null, "succes");
                pst.execute();
                personalInformation( event);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAlert(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }

    @FXML
    public void exitSignUp(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(LOGIN_XML_FILE));
        root = loader.load();
        LoginController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void personalInformation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(INFORMATIONCUSTOMER_XML_FILE));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
