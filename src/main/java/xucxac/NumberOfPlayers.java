package xucxac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;

public class NumberOfPlayers {
    private static final String BOARDGAME_XML_FILE = "BoardGame.fxml";
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private RadioButton radiobtn2;

    @FXML
    private RadioButton radiobtn3;

    @FXML
    private RadioButton radiobtn4;

    @FXML
    private RadioButton radiobtn5;

    @FXML
    private RadioButton radiobtn6;
    @FXML
    int limitPlayer=0;
    static int sum=0;
    @FXML
    public void playerNumber(ActionEvent event) {
        if (radiobtn2.isSelected()) {
             limitPlayer = 2;
        } else if (radiobtn3.isSelected()) {
             limitPlayer= 3;
        } else if (radiobtn4.isSelected()) {
             limitPlayer= 4;
        } else if (radiobtn5.isSelected()) {
             limitPlayer= 5;
        } else if (radiobtn6.isSelected()) {
             limitPlayer = 6;
        }
        sum=limitPlayer;
    }
    public static int hhh(){
        return sum;
    }
    @FXML
    public void successfulRoom(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(BOARDGAME_XML_FILE));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

