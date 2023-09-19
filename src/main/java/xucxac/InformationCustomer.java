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
import xucxac.database.entites.Player;


import javax.sound.midi.MidiDevice;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static xucxac.database.PlayerDatabase.insertCustomer;


public class InformationCustomer implements Initializable {
    private static final String LOGIN_XML_FILE = "Login.fxml";
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField txtCardMoney;

    @FXML
    private ComboBox<String> comboboxGender;
    private String[] list = {"male", "female"};

    @FXML
    private TextField txtIdCustomer;

    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxGender.getItems().addAll(list);
        comboboxGender.setValue("Nam");
    }

    @FXML
    public void OnActionInformationCustomer(ActionEvent event) throws IOException {
        int idCustomer = Integer.parseInt(txtIdCustomer.getText());
        String name = txtName.getText();
        String gender = comboboxGender.getValue();
        int cardMoney = Integer.parseInt(txtCardMoney.getText());
        int idAccount = SignUpController.idAccount;
        Player player = new Player(idCustomer, name, gender, cardMoney, 0, idAccount);
        insertCustomer(player);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(LOGIN_XML_FILE));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
