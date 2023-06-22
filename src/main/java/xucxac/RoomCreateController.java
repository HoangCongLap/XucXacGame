package xucxac;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xucxac.mySql.MysqlConnectRoom;
import xucxac.users.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class RoomCreateController implements Initializable {

    @FXML
    private TableView<User> tableV_inforAca;

    @FXML
    private TableColumn<User, String> col_IdPhong;

    @FXML
    private TableColumn<User, String> col_TenPhong;

    @FXML
    private TableColumn<User, String> col_SoNguoi;


    @FXML
    private TextField keyWordTextField;

    @FXML
    private Button btnTaoPhong;

    @FXML
    private Button btnTimPhong;


    ObservableList<User> dataList;


    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    public void initialize(URL url, ResourceBundle rb) {

       col_IdPhong.setCellValueFactory(new PropertyValueFactory<User, String>("idPhong"));
        col_TenPhong.setCellValueFactory(new PropertyValueFactory<User, String>("tenPhong"));
        col_SoNguoi.setCellValueFactory(new PropertyValueFactory<User, String>("soNguoi"));

        dataList = MysqlConnectRoom.getDataAllUsers();
        tableV_inforAca.setItems(dataList);

//        search_user();

        tableV_inforAca.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });

    }


    @FXML
    void search_user(ActionEvent event) {

    }

    @FXML
    private TextField passWordTextField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void onActionTaoPhong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("BoardGame.fxml"));
        root = loader.load();
        BoardGameController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
