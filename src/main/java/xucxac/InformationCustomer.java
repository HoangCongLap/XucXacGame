package xucxac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import xucxac.database.ConnectionUtil;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class InformationCustomer implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button bntInformationCustomer;

    @FXML
    private TextField txtCardMoney;

    @FXML
    private ComboBox<String> comboboxGender;
    private String[] list = {"male", "female"};

    @FXML
    private TextField txtIdCustomer;

    @FXML
    private TextField txtName;

    @FXML
    public void OnActionInformationCustomer(ActionEvent event) throws IOException {

        Connection conn = ConnectionUtil.connectdb();
        String sql = "INSERT INTO customer(idCustomer, name,gender,cardMoney) VALUES(?,?,?,?);\n";
        StringBuilder sb = new StringBuilder();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, txtIdCustomer.getText());
            pst.setString(2, txtName.getText());
            pst.setString(3, comboboxGender.getValue());
            pst.setString(4, txtCardMoney.getText());

            JOptionPane.showConfirmDialog(null, "succes");
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BoardGame.fxml"));
        root = loader.load();
        BoardGameController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxGender.getItems().addAll(list);
        comboboxGender.setValue("Nam");
    }
}
