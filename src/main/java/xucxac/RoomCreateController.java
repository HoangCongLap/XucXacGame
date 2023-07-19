package xucxac;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xucxac.data.CurrentRoom;
import xucxac.mysql.MysqlConnectRooms;
import xucxac.database.entites.RoomUser;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomCreateController implements Initializable {

    private static final String BOARDGAME_XML_FILE = "BoardGame.fxml";
    private static final String LOGIN_XML_FILE = "Login.fxml";
    private static final String NUMBEROFPLAYERS = "NumberOfPlayers.fxml";
    @FXML
    private TableView<RoomUser> tableV_inforAca;

    @FXML
    private TableColumn<RoomUser, String> col_IdPhong;

    @FXML
    private TableColumn<RoomUser, String> col_SoNguoi;


    @FXML
    private TextField keyWordTextField;

    @FXML
    private Button btnTaoPhong;
    @FXML
    private AnchorPane anchorPane;

    ObservableList<RoomUser> dataList;
    public static boolean check = false;

    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(URL url, ResourceBundle rb) {

        col_IdPhong.setCellValueFactory(new PropertyValueFactory<RoomUser, String>("idPhong"));
        col_SoNguoi.setCellValueFactory(new PropertyValueFactory<RoomUser, String>("soNguoi"));

        dataList = MysqlConnectRooms.getDataAllRooms();
//        tableV_inforAca.setItems(RoomManage.rooms);
        tableV_inforAca.setItems(dataList);
//        search_user();

        tableV_inforAca.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            }
        });
        search_user();
    }


    @FXML
    void search_user() {
        FilteredList<RoomUser> filteredData = new FilteredList<>(dataList, b -> true);
        keyWordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getIdPhong().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
//                else if (person.getSoNguoi().contains(lowerCaseFilter))
//                    return true;
                else
                    return false;

            });
        });
        SortedList<RoomUser> sortedList = new SortedList<>(filteredData);
        sortedList.comparatorProperty().bind(tableV_inforAca.comparatorProperty());
        tableV_inforAca.setItems(sortedList);
    }

    @FXML
    public void exitRoomCreate(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(LOGIN_XML_FILE));
        root = loader.load();
        LoginController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void onActionTaoPhong(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(NUMBEROFPLAYERS));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void handleVaoPhongClick(MouseEvent event) {
        anchorPane.getScene().getWindow().focusedProperty().addListener(observable -> {
            dataList = MysqlConnectRooms.getDataAllRooms();
            tableV_inforAca.setItems(dataList);
        });
        TableView.TableViewSelectionModel<RoomUser> selectionModel = tableV_inforAca.getSelectionModel();
        RoomUser selectedRoomUser = selectionModel.getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(BOARDGAME_XML_FILE));
        CurrentRoom.roomUser = selectedRoomUser;
        if (tableV_inforAca.getSelectionModel().getSelectedItem() != null) {
            try {
                root = loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
                Logger.getLogger(RoomCreateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


}
