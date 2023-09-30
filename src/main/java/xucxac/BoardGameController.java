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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import xucxac.data.*;
import xucxac.consts.BoardGameConsts;
import xucxac.database.ConnectionUtil;
import xucxac.database.entites.ListPutMoney;
import xucxac.database.entites.Player;
import xucxac.database.entites.PutMoney;
import xucxac.database.entites.RoomUser;
import xucxac.mysql.table.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

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
    private Text textDataBottomLeft;

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
    private int sumOwner = 0;
    boolean isCallOnResult = false;
    boolean isCallOnResultOwenr = false;
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
    static ObservableList<PutMoney> putMoneyInBoardList;
    private Timer timer1 = null;
    private Timer timer2 = null;
    private Timer timer3 = null;
    boolean countClickInRoll = false;
    private static Boolean isStarted = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newThongTinBanChoi();
        int room = CurrentRoom.roomUser.getIdPhong();

        col_id.setCellValueFactory(integerListCellDataFeatures -> new SimpleIntegerProperty(integerListCellDataFeatures.getValue()).asObject());
//        dataList = PlayersInRoom.getDataListPlayer(room);
        dataList = FXCollections.observableList(CurrentRoom.informationInRoom.getCustomerIds());
        table_information_board.setItems(dataList);

        PutMoneyInBoard.listPutMoney = new ListPutMoney(MoneyPuts.getDataInPutMoney(CurrentRoom.informationInRoom.getIdPhong()));

//        REFRESH DANH SÁCH NGƯỜI VÀO PHÒNG
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                table_information_board.refresh();
            }
        }, 5000, 2000);

        putMoneyInBoardList = FXCollections.observableList(PutMoneyInBoard.listPutMoney.getMoneyPuts());
//        textDataTopLeft.setText(PutMoneyInBoardList.toString());

//        REFRESH BÀN ĐẶT TIỀN


        timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                List<PutMoney> topLeftValues = new ArrayList<>();
                List<PutMoney> topRightValues = new ArrayList<>();
                List<PutMoney> bottomLeftValues = new ArrayList<>();
                List<PutMoney> bottomRightValues = new ArrayList<>();
                for (int i = 0; i < putMoneyInBoardList.size(); i++) {
                    PutMoney putMoney = putMoneyInBoardList.get(i);
                    if (putMoney.getIdPutMoney() == 1) {
                        topLeftValues.add(putMoney);
                        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    labTopLeft.setText(String.valueOf(topLeftValues.size() * 500));
                                }
                            });
                        }
                    } else if (putMoney.getIdPutMoney() == 2) {
                        topRightValues.add(putMoney);
                        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    labTopRight.setText(String.valueOf(topRightValues.size() * 1000));
                                }
                            });
                        }
                    } else if (putMoney.getIdPutMoney() == 3) {
                        bottomLeftValues.add(putMoney);
                        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    labBottomLeft.setText(String.valueOf(bottomLeftValues.size() * 5000));
                                }
                            });
                        }
                    } else if (putMoney.getIdPutMoney() == 4) {
                        bottomRightValues.add(putMoney);
                        if (CurrentUser.player.getId() == CurrentRoom.roomUser.getCustomerOwnerId()) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    labBottomRight.setText(String.valueOf(bottomRightValues.size() * 10000));
                                }
                            });
                        }
                    }
                }
                textDataTopLeft.setText(topLeftValues.toString());
                textDataTopRight.setText(topRightValues.toString());
                textDataBottomLeft.setText(bottomLeftValues.toString());
                textDataBottomRight.setText(bottomRightValues.toString());
                if (PutMoneyInBoard.listPutMoney.getMoneyPuts().size() > 8) {
                    topLeft.setDisable(true);
                }

            }
        }, 5000, 200);

        Player player = Customers.getPlayerId(CurrentAccount.account.getId());
        CurrentUser.player = player;
        labMoneyTotal.setText(String.valueOf(CurrentUser.player.getMoneyTotal()));
        sumAccount = Integer.parseInt(String.valueOf(CurrentUser.player.getMoneyTotal()));
        CheckPlayerInformation();

        if (timer3 == null) {
// REFRESH QUAY XÚC XẮC
            synchronized (isStarted) {

                if (!isStarted) {
                    isStarted = true;

                    timer3 = new Timer();
                    timer3.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (CurrentRoom.roomUser.getCustomerOwnerId() != CurrentUser.player.getId()
                                    && Dices.getClickInRoll(CurrentRoom.roomUser.getIdPhong()) == 1) {

                                System.out.println("idplayer:" + CurrentUser.player.getId());
                                diceRandom();
                                countClickInRoll = false;
                                Dices.update(CurrentRoom.roomUser.getIdPhong(), countClickInRoll);

                            }
//                Dices.remove(CurrentRoom.roomUser.getIdPhong());
                        }
                    }, 10, 10);
                } else {
                    System.out.println("ignore run:" + CurrentUser.player.getId());
                }

            }
        }
    }

    public void refreshRoom() {
        MoneyPuts.remove(CurrentRoom.roomUser.getIdPhong());
        allButtonNotDisable();
        if (CurrentUser.player.getId() != CurrentRoom.roomUser.getCustomerOwnerId()) {
            tai.setDisable(false);
            xiu.setDisable(false);
        }
        selectTaiXiu = 0;
        this.tai.setStyle(setColorTaiXiu());
        this.xiu.setStyle(setColorTaiXiu());
        selectLabelTopLeft = 0;
        selectLabelTopRight = 0;
        selectLabelBottomLeft = 0;
        selectLabelBottomRight = 0;
        labTopLeft.setText(String.valueOf(0));
        labTopRight.setText(String.valueOf(0));
        labBottomLeft.setText(String.valueOf(0));
        labBottomRight.setText(String.valueOf(0));
        Dices.remove(CurrentRoom.roomUser.getIdPhong());
    }

    @FXML
    private void roll(ActionEvent event) {
//        if (selectLabelTopLeft == 1 || selectLabelTopRight == 2 ||
//                selectLabelBottomLeft == 3 || selectLabelBottomRight == 4) {
        countClickInRoll = true;
        isCallOnResultOwenr = false;
        diceRandom();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("THÔNG BÁO");
//            alert.setHeaderText("Chưa có người chơi đặt cược.");
//            alert.show();
//        }
    }
    //==================================================================================================================

    // QUAY XÚC XẮC
    public void diceRandom() {
//        if (sumAccount >= 500 && (selectTaiXiu == 1 || selectTaiXiu == 2) &&
//                (selectLabelTopLeft == 1 || selectLabelTopRight == 2 ||
//                        selectLabelBottomLeft == 3 || selectLabelBottomRight == 4)) {
        rollButton.setDisable(true);
        System.getProperty("User.dir");
        Thread theard = new Thread() {
            //            runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Running");
                try {
                    int index = 1;
                    finalIndex = (random.nextInt(6) + 1);
                    if (CurrentRoom.roomUser.getCustomerOwnerId() == CurrentUser.player.getId()) {
                        Dices.add(CurrentRoom.roomUser.getIdPhong(), finalIndex, countClickInRoll);
                    }
                    int[] arrRandom = {5, 2, 10, 8, 18, 15, 1, 11, 3, 7, 4, 9, 17, 1, 11, 20, 15, 13, 10, 20};
                    for (int i = 0; i < 20; i++) {
//                        index = (random.nextInt(6) + 1);
                        index = (Dices.getNumberInDice(CurrentRoom.roomUser.getIdPhong()) + arrRandom[i]) % 6 + 1;

                        String path = PATHIMAGES + index + ".png";
//                            String path = PATHIMAGES + index + ".png";
//                        String path = PATHIMAGES  + ((i%6)+1) + ".png";

                        File file = new File(path);
//                            System.out.println(file.exists());
                        diceImage.setImage(new Image(file.toURI().toString()));
                        //== mở ======
//                        if (i <= 15) {
//                            Thread.sleep(100);
//                        } else {
//                            Thread.sleep(80 * i);
//                        }
                        Thread.sleep(200);
                    }
//======================================================================================================================
//                    if (CurrentRoom.roomUser.getCustomerOwnerId() == CurrentUser.player.getId()) {
//                        finalIndex = index;
//                        System.out.println("kq xx:" + finalIndex);
//                        Dices.add(CurrentRoom.roomUser.getIdPhong(), finalIndex, countClickInRoll);
//                            ==================================
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                lblResult.setText(String.valueOf(finalIndex));
//                            }
//                        });


//                    } else {
                    finalIndex = Dices.getNumberInDice(CurrentRoom.roomUser.getIdPhong());
                    String path = PATHIMAGES + finalIndex + ".png";
                    File file = new File(path);
                    diceImage.setImage(new Image(file.toURI().toString()));
//                    }
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
                    isCallOnResult = false;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (isCallOnResult == false) {
                                result();
                                isCallOnResult = true;
                            }
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
//        } else {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("THÔNG BÁO");
//            alert.setHeaderText("Tiền trong tài khoản <500 hoặc chưa chọn 'Tài' 'Xỉu' hoặc chưa đặt cược.");
//            alert.show();
//        }

    }

    public void CheckPlayerInformation() {
        if (CurrentUser.player.getId() != CurrentRoom.roomUser.getCustomerOwnerId()) {
            rollButton.setVisible(false);
        } else {
            allButtonNotVisible();
            tai.setDisable(true);
            xiu.setDisable(true);
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
        sumAccount = sumAccount + Integer.parseInt(textCardMoney.getText());
        labMoneyTotal.setText(String.valueOf(sumAccount));
        Customers.updateMoneyTotal(CurrentUser.player.getId(), sumAccount);
        if (sumAccount >= 500) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Nạp tiền thành công.");
            alert.setContentText("Tiền trong tài khoản: " + sumAccount);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Nạp tiền không thành công.");
            alert.setContentText("Hãy Nạp lại tiền >= 500\n ");
            alert.show();
        }
    }


    // xử lí chọn tài hoặc xỉu
    @FXML
    protected void handleClickedTaiXiu(MouseEvent event) {
        if (sumAccount >= 500) {
            Label selectedtLabel = (Label) event.getSource();
            selectedtLabel.setStyle("-fx-border-color:red; -fx-background-color: blue;");
            if (((Label) event.getSource()).getId().equals("tai")) {
                this.xiu.setStyle(colorTaiXiu());
                selectTaiXiu = 2;
                xiu.setDisable(true);
            } else {
                this.tai.setStyle(colorTaiXiu());
                selectTaiXiu = 1;
                tai.setDisable(true);
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
            MoneyPuts.add(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
            PutMoney putMoney = new PutMoney(1, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
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

//            System.out.println("size"+topLeftValues.size());


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

            MoneyPuts.add(2, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
            PutMoney putMoney = new PutMoney(2, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
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
            MoneyPuts.add(3, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
            PutMoney putMoney = new PutMoney(3, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
            CurrentPutMoney.putMoney = putMoney;
            textDataBottomLeft.setText(PutMoneyInBoard.listPutMoney.getMoneyPuts().toString());
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
        int valueMoneyBottomRight = Integer.parseInt(labBottomRight.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 10000 && sumAccount - valueMoneyBottomRight > 10000) {
            MoneyPuts.add(4, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
            PutMoney putMoney = new PutMoney(4, CurrentRoom.roomUser.getIdPhong(), CurrentUser.player.getId(), selectTaiXiu);
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
        int idPlayer = CurrentUser.player.getId();
//        if(finalIndex!=0) {
        int value1, value2, value3, value4;
        value1 = Integer.parseInt(labTopLeft.getText());
        value2 = Integer.parseInt(labTopRight.getText());
        value3 = Integer.parseInt(labBottomLeft.getText());
        value4 = Integer.parseInt(labBottomRight.getText());
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("KẾT QUẢ");

        if (isCallOnResultOwenr == false) {

            if (CurrentRoom.roomUser.getCustomerOwnerId() == CurrentUser.player.getId()) {
                System.out.println("chu:" + sumAccount);
                if (finalIndex == 1 || finalIndex == 6) {
                    sumAccount = sumAccount + (value1 + value2 + value3 + value4);
                    alert.setContentText("WIN\n" + "Account Owner" + "(" + idPlayer + ")" + ": " + sumAccount);
                } else if (finalIndex <= 3) {
                    if (MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 1).size() != 0) {
//                        sumAccount -= MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 1).size() * (value1
//                                + value2 + value3 + value4);
                        sumAccount = sumAccount - (value1 + value2 + value3 + value4);
                        alert.setContentText("LOSE\n" + "Account Owner" + "(" + idPlayer + ")" + ": " + sumAccount);
                    }
                    if (MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 2).size() != 0) {
//                        sumAccount += MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 2).size() * (value1
//                                + value2 + value3 + value4);
                        sumAccount = sumAccount + (value1 + value2 + value3 + value4);
                        alert.setContentText("WIN\n" + "Account Owner" + "(" + idPlayer + ")" + ": " + sumAccount);
                    }
                } else {
                    if (MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 1).size() != 0) {
//                        sumAccount += MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 1).size() * (value1
//                                + value2 + value3 + value4);
                        sumAccount = sumAccount + (value1 + value2 + value3 + value4);
                        alert.setContentText("WIN\n" + "Account Owner" + "(" + idPlayer + ")" + ": " + sumAccount);
                    }
                    if (MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 2).size() != 0) {
//                        sumAccount -= MoneyPuts.getListTaiOrXiu(CurrentRoom.roomUser.getIdPhong(), 2).size() * (value1
//                                + value2 + value3 + value4);
                        sumAccount = sumAccount - (value1 + value2 + value3 + value4);
                        alert.setContentText("LOSE\n" + "Account Owner" + "(" + idPlayer + ")" + ": " + sumAccount);
                    }
                }
                isCallOnResultOwenr = true;

            } else {
                System.out.println("nguoi choi:" + sumAccount);
                sumAccount = BoardGameConsts.result(value1, value2, value3, value4, finalIndex,
                        selectLabelTopLeft, sumAccount, selectLabelTopRight, selectLabelBottomLeft, selectTaiXiu);
                System.out.println("nguoi choi da cong:" + sumAccount);
                if (finalIndex == 1 || finalIndex == 6) {
                    alert.setContentText("LOSE\n" + "Account " + "(" + idPlayer + ")" + ": " + sumAccount);
                } else if (finalIndex <= 3 && selectTaiXiu == 2 || finalIndex > 3 && selectTaiXiu == 1) {
                    alert.setContentText("LOSE\n" + "Account " + "(" + idPlayer + ")" + ": " + sumAccount);
                } else {
                    alert.setContentText("WIN\n" + "Account " + "(" + idPlayer + ")" + ": " + sumAccount);
                }
            }

            Customers.updateMoneyTotal(CurrentUser.player.getId(), sumAccount);
            alert.setHeaderText("Số nút là: " + finalIndex);
            alert.getDialogPane().

                    getButtonTypes().

                    add(ButtonType.OK);
            alert.setResizable(true);
            alert.showAndWait();
            refreshRoom();
        }

    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    // xóa phòng
    public void back(ActionEvent event) throws IOException {
        Dices.remove(CurrentRoom.roomUser.getIdPhong());
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