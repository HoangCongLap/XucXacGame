package xucxac;


import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import xucxac.data.*;
import xucxac.consts.BoardGameConsts;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.Player;
import xucxac.database.entites.PutMoney;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.Customers;
import xucxac.mysql.table.ListPlayers;
import xucxac.mysql.table.MoneyPuts;
import xucxac.mysql.table.Rooms;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static xucxac.consts.BoardGameConsts.*;


public class BoardGameController implements Initializable {

    private static final String ROOMCREATE_XML_FILE = "RoomCreate.fxml";
    @FXML
    private Label welcomeText;
    Random random = new Random();
    @FXML
    private ImageView diceImage;
    @FXML
    private Button rollButton;
    // label sau khi random
    @FXML
    private Label lblResult;
    @FXML
    private Label labTopLeft;
    @FXML
    private Label labTopRight;
    @FXML
    private Label labBottomLeft;
    @FXML
    private Label labBottomRight;
    @FXML
    private Label tai;
    @FXML
    private Label xiu;
    @FXML
    private Label Sum;
    @FXML
    private Label ResultGame;
    @FXML
    private Button topLeft;
    @FXML
    private Button topRight;
    @FXML
    private Button bottomLeft;
    @FXML
    private Button bottomRight;
    @FXML
    private Button instruction;
    @FXML
    private TextField textCardMoney;
    @FXML
    public VBox vBoxThongTinBan;
    @FXML
    public Label labIdPhong;
    @FXML
    private Label labPlayerNumber;
    @FXML
    private Label labIdChuPhong;
    @FXML
    private Label labCardMoney;
    @FXML
    private Label labMoneyTotal;

//    @FXML
//    private Text txtDataTopLeft1;
//    @FXML
//    private Text txtDataTopLeft2;
//    @FXML
//    private Text txtDataTopLeft3;
//    @FXML
//    private Text txtDataTopLeft4;
//    @FXML
//    private Text txtDataTopLeft5;
//    @FXML
//    private Text txtDataTopLeft6;
//    @FXML
//    private Text txtDataTopRight1;
//    @FXML
//    private Text txtDataTopRight2;
//    @FXML
//    private Text txtDataTopRight3;
//    @FXML
//    private Text txtDataTopRight4;
//    @FXML
//    private Text txtDataTopRight5;
//    @FXML
//    private Text txtDataTopRight6;
//    @FXML
//    private Text txtDataBottomLeft1;
//    @FXML
//    private Text txtDataBottomLeft2;
//    @FXML
//    private Text txtDataBottomLeft3;
//    @FXML
//    private Text txtDataBottomLeft4;
//    @FXML
//    private Text txtDataBottomLeft5;
//    @FXML
//    private Text txtDataBottomLeft6;
//    @FXML
//    private Text txtDataBottomRight1;
//    @FXML
//    private Text txtDataBottomRight2;
//    @FXML
//    private Text txtDataBottomRight3;
//    @FXML
//    private Text txtDataBottomRight4;
//    @FXML
//    private Text txtDataBottomRight5;
//    @FXML
//    private Text txtDataBottomRight6;


    @FXML
    private Text textDataTopLeft;

    @FXML
    private Text textDataTopRight;

    @FXML
    private Text textDataBottomleft;

    @FXML
    private Text textDataBottomRight;


    private int finalIndex = 0;
    private int selectTaiXiu = 0;
    private int selectLabelTopLeft = 0;
    private int selectLabelTopRight = 0;
    private int selectLabelBottomLeft = 0;
    private int selectLabelBottomRight = 0;
    private int sumAccount = 0;
    private int quayXucXac = 0;
    private int moneyTopLeft = 500;
    private int moneyTopRight = 1000;
    private int moneyBottomLeft = 5000;
    private int moneyBottomRight = 10000;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    Connection conn = ConnectionUtil.connectdb();

    public BoardGameController() {
    }

    private RoomUser roomUser;
    @FXML
    private TableView<Integer> table_information_board;
    //    @FXML
//    private TableColumn<InformationInRoom, ?> col_stt;
    @FXML
    private TableColumn<Integer, Integer> col_id;


    static ObservableList<Integer> dataList;
    static ObservableList<PutMoney> PutMoneyInBoardList;
    private Timer timer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newThongTinBanChoi();
        int room = CurrentRoom.roomUser.getIdPhong();

        col_id.setCellValueFactory(integerListCellDataFeatures -> new SimpleIntegerProperty(integerListCellDataFeatures.getValue()).asObject());
//        dataList = PlayersInRoom.getDataListPlayer(room);
        dataList = FXCollections.observableList(CurrentRoom.informationInRoom.getCustomerIds());
        table_information_board.setItems(dataList);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                table_information_board.refresh();
            }
        }, 5000, 2000);

        PutMoneyInBoardList = FXCollections.observableList(PutMoneyInBoard.listPutMoney.getMoneyPuts());
        textDataTopLeft.setText(PutMoneyInBoardList.toString());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if()
                textDataTopLeft.setText(PutMoneyInBoardList.toString());
                if (PutMoneyInBoard.listPutMoney.getMoneyPuts().size() > 8) {
                    topLeft.setDisable(true);
                }
            }
        }, 5000, 2000);

        Player player = Customers.getPlayerId(CurrentAccount.account.getId());
        CurrentUser.player = player;
        labMoneyTotal.setText(String.valueOf(CurrentUser.player.getMoneyTotal()));
        sumAccount = Integer.parseInt(String.valueOf(CurrentUser.player.getMoneyTotal()));
        CheckPlayerInformation();


    }

    public void CheckPlayerInformation() {
        if (CurrentUser.player.getId() != CurrentRoom.roomUser.getCustomerOwnerId()) {
            rollButton.setVisible(false);
        } else {
//        System.out.println(CurrentRoom.roomUser.getCustomerOwnerId());
//        System.out.println(CurrentUser.player.getId());
//        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
//            topLeft.setVisible(false);
            allButtonNotVisible();
        }

    }

    public void newThongTinBanChoi() {
        int idPhong = CurrentRoom.roomUser.getIdPhong();
//        int idCustomerOwner = CurrentRoom.roomUser.getIdcustomer();
//        labIdChuPhong.setText(String.valueOf(idCustomerOwner));
        labIdPhong.setText(String.valueOf(idPhong));
        labIdChuPhong.setText(String.valueOf(CurrentRoom.roomUser.getCustomerOwnerId()));
        labPlayerNumber.setText(String.valueOf(CurrentRoom.roomUser.getSoNguoi()));


    }


    //Tổng tiền trong Account
    @FXML
    protected void newAccount(ActionEvent event) {
//        sumAccount = Integer.parseInt(textCardMoney.getText());
//        if (sumAccount >= 500) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("THÔNG BÁO");
//            alert.setHeaderText("Nạp tiền thành công.");
//            alert.setContentText("Tiền trong tài khoản: " + sumAccount);
//            alert.show();
//            System.out.println(sumAccount);
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("THÔNG BÁO");
//            alert.setHeaderText("Nạp tiền không thành công.");
//            alert.setContentText("Hãy Nạp lại tiền >= 500\n ");
//            alert.show();
//        }
    }

    @FXML
    private void roll(ActionEvent event) {
        if (sumAccount >= 500 && (selectTaiXiu == 1 || selectTaiXiu == 2) && (selectLabelTopLeft == 1 || selectLabelTopRight == 2 || selectLabelBottomLeft == 3 || selectLabelBottomRight == 4)) {
            rollButton.setDisable(true);
            System.getProperty("User.dir");
            Thread theard = new Thread() {
                //            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("Thread Running");
                    try {
                        int index = 1;
                        for (int i = 0; i < 15; i++) {
                            index = (random.nextInt(6) + 1);
                            String path = PATHIMAGES + index + ".png";
//                        String path = PATHIMAGES  + ((i%6)+1) + ".png";

                            File file = new File(path);
//                            System.out.println(file.exists());
                            diceImage.setImage(new Image(file.toURI().toString()));
//                        if (i <= 9) {
//                            Thread.sleep(100);
//                        } else {
//                            Thread.sleep(100 * i);
//                        }
                            Thread.sleep(100);
                        }
//======================================================================================================================
                        finalIndex = index;
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                lblResult.setText(String.valueOf(finalIndex));
//                            }
//                        });
//======================================================================================================================
                        // xuất ra kết quả
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {

//                                getMessage(object)
//                                alertNotification(message,);
//                            }
//                        });
                        //=======================================
                        // xuất ra kết quả
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                result();
                            }
                        });
// xuất ra tổng tiền ở Label
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                labMoneyTotal.setText(String.valueOf(sumAccount));
                            }
                        });
//==================================================================================================================
                        rollButton.setDisable(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            theard.start();
            // quayXucXac = 1;
//            System.out.println(sumAccount);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Tiền trong tài khoản <500 hoặc chưa chọn 'Tài' 'Xỉu' hoặc chưa đặt cược.");
            alert.show();
        }
    }
    //==================================================================================================================

    // xử lí chọn tài hoặc xỉu
    @FXML
    protected void handleClickedTaiXiu(MouseEvent event) {
        if (sumAccount >= 500) {
            Label selectedtLabel = (Label) event.getSource();
            selectedtLabel.setStyle("-fx-border-color:red; -fx-background-color: blue;");
            if (((Label) event.getSource()).getId().equals("tai")) {
//                this.xiu.setStyle("-fx-color:red-fx-border; -fx-background-color: #d3d3d3;");
                this.xiu.setStyle(colorTaiXiu());
                selectTaiXiu = 2;
            } else {
                this.tai.setStyle(colorTaiXiu());
                selectTaiXiu = 1;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Tiền trong tài khoản < 500");
            alert.show();
        }
    }


    //==================================================================================================================
    //    Đặt tiền "\$500"

    int count = 0;

    @FXML
    protected void clickTopLeft(MouseEvent event) {


        count = count + 1;
        int valueMoneyTopLeft = Integer.parseInt(labTopLeft.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 500 && sumAccount - valueMoneyTopLeft > 500) {
            MoneyPuts.add(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            PutMoney putMoney = new PutMoney(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            CurrentPutMoney.putMoney = putMoney;
            textDataTopLeft.setText(PutMoneyInBoard.listPutMoney.getMoneyPuts().toString());
//            if (PutMoneyInBoard.listPutMoney.getMoneyPuts().size() > 8) {
//                topLeft.setDisable(true);
//            }
            String text;
            if (labTopLeft.getText().isEmpty()) {
                text = String.valueOf((Integer) moneyTopLeft);
            } else {
                Integer sum = Integer.parseInt(labTopLeft.getText()) + moneyTopLeft;
                text = String.valueOf(sum);
            }
            labTopLeft.setText(text);
            selectLabelTopLeft = 1;
            buttonDisabledTopLeft();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <500.");
            alert.show();
        }

    }
    //==================================================================================================================

    //    Đặt tiền "\$1.000"
    @FXML
    protected void clickTopRight(MouseEvent event) {
        count = count + 1;
        int valueMoneyTopRight = Integer.parseInt(labTopRight.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 1000 && sumAccount - valueMoneyTopRight > 1000) {

            MoneyPuts.add(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            PutMoney putMoney = new PutMoney(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            CurrentPutMoney.putMoney = putMoney;
            textDataTopRight.setText(PutMoneyInBoard.listPutMoney.getMoneyPuts().toString());
            if (count > 5) {
                topRight.setDisable(true);
            }

            String text;
            if (Integer.parseInt(labTopRight.getText()) == 0) {
                text = String.valueOf((Integer) moneyTopRight);
            } else {
                Integer sum = Integer.parseInt(labTopRight.getText()) + moneyTopRight;
                text = String.valueOf(sum);
            }
            labTopRight.setText(text);
            selectLabelTopRight = 2;
            buttonDisabledTopRight();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <1000.");
            alert.show();
        }
    }

    //    //==================================================================================================================
//
//    //    Đặt tiền "\$5.000"
//
    @FXML
    protected void clickBottomLeft(MouseEvent event) {
        count = count + 1;
        int valueMoneyBottomLeft = Integer.parseInt(labBottomLeft.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 5000 && sumAccount - valueMoneyBottomLeft > 5000) {
            MoneyPuts.add(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            PutMoney putMoney = new PutMoney(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            CurrentPutMoney.putMoney = putMoney;
            textDataBottomleft.setText(PutMoneyInBoard.listPutMoney.getMoneyPuts().toString());
//            if(count>5) {
            if (PutMoneyInBoard.listPutMoney.getMoneyPuts().size() > 8) {
                bottomLeft.setDisable(true);
            }
            String text;
            if (Integer.parseInt(labBottomLeft.getText()) == 0) {
                text = String.valueOf((Integer) moneyBottomLeft);
            } else {
                Integer sum = Integer.parseInt(labBottomLeft.getText()) + moneyBottomLeft;
                text = String.valueOf(sum);
            }
            labBottomLeft.setText(text);
            selectLabelBottomLeft = 3;
            buttonDisabledBottomLeft();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <5000.");
            alert.show();
        }
    }

    //    //==================================================================================================================
//
//    // Đặt tiền "$10.000"
//

    @FXML
    protected void clickBottomRight(MouseEvent event) {
        count = count + 1;
        System.out.println(count);
        int valueMoneyBottomRight = Integer.parseInt(labBottomRight.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 10000 && sumAccount - valueMoneyBottomRight > 10000) {
            MoneyPuts.add(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            PutMoney putMoney = new PutMoney(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId());
            CurrentPutMoney.putMoney = putMoney;
            textDataBottomRight.setText(PutMoneyInBoard.listPutMoney.getMoneyPuts().toString());
            if (count > 5) {
                bottomRight.setDisable(true);
            }
            String text;
            if (Integer.parseInt(labBottomRight.getText()) == 0) {
                text = String.valueOf((Integer) moneyBottomRight);
            } else {
                Integer sum = Integer.parseInt(labBottomRight.getText()) + moneyBottomRight;
                text = String.valueOf(sum);
            }
            labBottomRight.setText(text);
            selectLabelBottomRight = 4;
            buttonDisabledBottomRight();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <10000.");
            alert.show();
        }
    }

    //==================================================================================================================

    @FXML
    protected void instruction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INSTRUCTION");
        alert.setHeaderText("Hay làm theo thứ tự bên dưới:");
        alert.setContentText("1.Bạn hay nạp tiền vào tài khoản.\n" +
                "2.Bạn hãy chọn tài hoặc xỉu.\n" +
                "3.Bạn hãy chọn mức đặt cược.\n" +
                "4.Bạn hãy click vào 'CHỌN' để quay xúc xắc.\n" +
                "5.Bạn Hãy click vào 'KẾT QUẢ' để nhận được kết quả.");
        alert.show();
    }


//    public void alertNotification(int value) {

    public void result() {
        int value1, value2, value3, value4;
        value1 = Integer.parseInt(labTopLeft.getText());
        value2 = Integer.parseInt(labTopRight.getText());
        value3 = Integer.parseInt(labBottomLeft.getText());
        value4 = Integer.parseInt(labBottomRight.getText());
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("KẾT QUẢ");
        sumAccount = BoardGameConsts.result(value1, value2, value3, value4, finalIndex, selectLabelTopLeft, sumAccount, selectLabelTopRight, selectLabelBottomLeft, selectTaiXiu);
        System.out.println("tien" + sumAccount);
        Customers.updateMoneyTotal(CurrentUser.player.getId(), sumAccount);


        if (finalIndex == 1 || finalIndex == 6) {
            alert.setContentText("LOSE\n" + "Account: " + sumAccount);
        } else if (finalIndex <= 3 && selectTaiXiu == 2 || finalIndex > 3 && selectTaiXiu == 1) {
            alert.setContentText("LOSE\n" + "Account: " + sumAccount);
        } else {
            alert.setContentText("WIN\n" + "Account: " + sumAccount);
        }
        alert.setHeaderText("Số nút là: " + finalIndex);
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.setResizable(true);
        alert.showAndWait();
//        allButtonNotDisable();
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    // xóa phòng
    public void back(ActionEvent event) throws IOException {
        ListPlayers.remove(CurrentUser.player.getId());
        MoneyPuts.remove(CurrentRoom.informationInRoom.getIdPhong());
        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
            Rooms.remove(CurrentRoom.roomUser.getCustomerOwnerId());
            ListPlayers.removeAll(CurrentRoom.roomUser.getIdPhong());

        }
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(ROOMCREATE_XML_FILE));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void buttonDisabledTopLeft() {
        topRight.setDisable(true);
        bottomLeft.setDisable(true);
        bottomRight.setDisable(true);
    }

    public void buttonDisabledTopRight() {
        topLeft.setDisable(true);
        bottomLeft.setDisable(true);
        bottomRight.setDisable(true);
    }

    public void buttonDisabledBottomLeft() {
        topRight.setDisable(true);
        topLeft.setDisable(true);
        bottomRight.setDisable(true);
    }

    public void buttonDisabledBottomRight() {
        topRight.setDisable(true);
        topLeft.setDisable(true);
        bottomLeft.setDisable(true);
    }

    public void allButtonNotDisable() {
        topLeft.setDisable(false);
        topRight.setDisable(false);
        bottomLeft.setDisable(false);
        bottomRight.setDisable(false);
    }

    public void allButtonNotVisible() {
        topLeft.setVisible(false);
        topRight.setVisible(false);
        bottomLeft.setVisible(false);
        bottomRight.setVisible(false);
    }


}