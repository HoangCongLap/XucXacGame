package xucxac;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import xucxac.data.CurrentAccount;
import xucxac.data.CurrentRoom;
import xucxac.data.CurrentUser;
import xucxac.data.RoomManage;
import xucxac.database.entites.InformationInRoom;

import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.ListPlayers;
import xucxac.mysql.table.Rooms;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
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
    private Timer timer;

    public void initialize(URL url, ResourceBundle rb) {
        col_IdPhong.setCellValueFactory(new PropertyValueFactory<RoomUser, String>("idPhong"));
        col_SoNguoi.setCellValueFactory(new PropertyValueFactory<RoomUser, String>("soNguoi"));
//        dataList = Rooms.getDataAll();
        dataList = FXCollections.observableList(RoomManage.listRoom.getRoomUsers());
//        tableV_inforAca.setItems(RoomManage.rooms);
        tableV_inforAca.setItems(dataList);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tableV_inforAca.setItems(dataList);
                tableV_inforAca.refresh();
            }
        }, 5000, 2000);
        search_user();

//        sửa
        tableV_inforAca.setRowFactory(tv -> new TableRow<RoomUser>() {
            @Override
            public void updateItem(RoomUser item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
//                } else if (item.getSoNguoi()==ListPlayers.getPlayersInRoom().size()) {
                } else if (item.getSoNguoi() == 2) {
//                    setStyle("-fx-background-color: red;");
                } else {
                    setStyle("");
                }
            }
        });

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
                String idPhongStr = String.valueOf(person.getIdPhong());
                if (idPhongStr.contains(lowerCaseFilter)) {
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

//        System.out.println("id người chơi" + CurrentUser.player.getId());
//
//        System.out.println("id người chủ" + Rooms.getRooms().getCustomerOwnerId());
        TableView.TableViewSelectionModel<RoomUser> selectionModel = tableV_inforAca.getSelectionModel();
        RoomUser selectedRoomUser = selectionModel.getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(BOARDGAME_XML_FILE));
        int roomId = selectedRoomUser.getIdPhong();
        int limitPlayer = Rooms.getLimitPlayer(roomId);
        System.out.println("so nguowi " + limitPlayer);
//  KIỂM TRA GIỚI HẠN NGƯỜI CHƠI VÀO PHÒNG
        if (ListPlayers.getNumberOfPlayersInRoom(roomId) < limitPlayer) {
            int playerId = CurrentUser.player.getId();
            int ownerId = Rooms.getRooms().getCustomerOwnerId();
//  chưa làm được nếu có trong danh sách rồi thì ko insert vào nữa
            if (playerId != ownerId) {
                ListPlayers.insert(selectedRoomUser.getIdPhong(), CurrentUser.player.getId());

            }
            CurrentRoom.roomUser = selectedRoomUser;
            CurrentRoom.informationInRoom = new InformationInRoom(selectedRoomUser.getIdPhong(),
                    ListPlayers.getPlayersInRoom(selectedRoomUser.getIdPhong()));
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setHeaderText("FULL PLAYER");
            alert.show();
        }

    }
}