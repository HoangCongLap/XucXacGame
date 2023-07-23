package xucxac;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import xucxac.data.CurrentRoom;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.LimitPlayer;
import xucxac.database.entites.RoomUser;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static xucxac.consts.BoardGameConsts.ranDomIdPhong;

public class NumberOfPlayers {
    Connection conn = ConnectionUtil.connectdb();

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
    int player = 2;

    @FXML
    public void playerNumber(ActionEvent event) {
        if (radiobtn2.isSelected()) {
            player = 2;
        } else if (radiobtn3.isSelected()) {
            player = 3;
        } else if (radiobtn4.isSelected()) {
            player = 4;
        } else if (radiobtn5.isSelected()) {
            player = 5;
        } else if (radiobtn6.isSelected()) {
            player = 6;
        }

    }

    @FXML
    public void successfulRoom(ActionEvent event) throws IOException {
        LimitPlayer limitPlayer = new LimitPlayer(player);
        CurrentRoom.limitPlayer = limitPlayer;
//        System.out.println(CurrentRoom.limitPlayer.toString());

        String idPhong = String.valueOf(ranDomIdPhong());
        int limitPlayerInRoom = CurrentRoom.limitPlayer.getPlayer();

// chưa làm được số người chơi trong 1 phòng
        int soNguoi = limitPlayerInRoom;
        RoomUser roomUser = new RoomUser(idPhong, 03, soNguoi);
        CurrentRoom.roomUser = roomUser;
        System.out.println("check:" + soNguoi);
/// tạo phòng và ghi xuống dbase
        add_room(Integer.parseInt(idPhong),1212,soNguoi);
// add_room(idPhong,idCustomerOwner,limitPlayerInRoom);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(BOARDGAME_XML_FILE));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void add_room(int idPhong, int idCustomerOwner,int limitPlayerInRoom) {
        String sql = "INSERT INTO rooms(id, idcustomerOwner, soNguoi) VALUES(?,?,?);\n";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, idPhong);
            pst.setInt(2, idCustomerOwner);
            pst.setInt(3, limitPlayerInRoom);

//            if (text_IdOrganization.getText().equals("") || text_toChucOrganization.getText().equals("")) {
//                check();
//                showAlert("You missed some field!\n", "", null);
//            } else {
//                JOptionPane.showMessageDialog(null, "Create room success");
//                AddOrganizationControllerApi.saveDataOrganization(text_IdOrganization.getText(), text_toChucOrganization.getText());
//            }
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

