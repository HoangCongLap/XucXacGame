package xucxac;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import xucxac.consts.BoardGameConsts;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static xucxac.consts.BoardGameConsts.*;


public class BoardGameController {
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
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label tai;
    @FXML
    private Label xiu;
    @FXML
    private Label Sum;
    @FXML
    private Label ResultGame;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button instruction;
    @FXML
    private TextField myTextFieldSum;
    @FXML
    private Label resultAccountGame;
    private int finalIndex = 0;
    private int selectTaiXiu = 0;
    private int selectLabel1 = 0;
    private int selectLabel2 = 0;
    private int selectLabel3 = 0;
    private int selectLabel4 = 0;
    private int sumAccount = 0;
    private int quayXucXac = 0;


    //Tổng tiền trong Account
    @FXML
    protected void newAccount(ActionEvent event) {
        sumAccount = Integer.parseInt(myTextFieldSum.getText());
        if (sumAccount >= 500) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Nạp tiền thành công.");
            alert.setContentText("Tiền trong tài khoản: " + sumAccount);
            alert.show();
            System.out.println(sumAccount);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Nạp tiền không thành công.");
            alert.setContentText("Hãy Nạp lại tiền >= 500\n ");
            alert.show();
        }
    }

    @FXML
    private void roll(ActionEvent event) {
        if (sumAccount >= 500 && (selectTaiXiu == 1 || selectTaiXiu == 2) && (selectLabel1 == 1 || selectLabel2 == 2 || selectLabel3 == 3 || selectLabel4 == 4)) {
            rollButton.setDisable(true);
            System.getProperty("user.dir");
            Thread theard = new Thread() {
                //            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread Running");
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
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                lblResult.setText(String.valueOf(finalIndex));
                            }
                        });
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
                                resultAccountGame.setText(String.valueOf(sumAccount));
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
            System.out.println(sumAccount);
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
    protected void handleClicked2(MouseEvent event) {
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
            alert.setHeaderText("Bạn chưa nạp tiền vào tài khoản...");
            alert.show();
        }
    }


    //==================================================================================================================
    //    Đặt tiền "\$500"
    @FXML
    protected void onNumberClicked1(MouseEvent event) {
        int value1 = Integer.parseInt(label1.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 500 && sumAccount - value1 > 500) {
            int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
            String text = null;
            if (Integer.parseInt(label1.getText()) == 0) {
                text = String.valueOf((Integer) value);
            } else {
                Integer sum = Integer.parseInt(label1.getText()) + value;
                text = String.valueOf(sum);
            }
            label1.setText(text);
            selectLabel1 = 1;
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
    protected void onNumberClicked2(MouseEvent event) {
        int value2 = Integer.parseInt(label2.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 1000 && sumAccount - value2 > 1000) {
            int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
            String text;
            if (Integer.parseInt(label2.getText()) == 0) {
                text = String.valueOf((Integer) value);
            } else {
                Integer sum = Integer.parseInt(label2.getText()) + value;
                text = String.valueOf(sum);
            }
            label2.setText(text);
            selectLabel2 = 2;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <1000.");
            alert.show();
        }
    }
    //==================================================================================================================

    //    Đặt tiền "\$5.000"
    @FXML
    protected void onNumberClicked3(MouseEvent event) {
        int value3 = Integer.parseInt(label3.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 5000 && sumAccount - value3 > 5000) {
            int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
            String text;
            if (Integer.parseInt(label3.getText()) == 0) {
                text = String.valueOf((Integer) value);
            } else {
                Integer sum = Integer.parseInt(label3.getText()) + value;
                text = String.valueOf(sum);
            }
            label3.setText(text);
            selectLabel3 = 3;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n" + "Tiền trong tài khoản <5000.");
            alert.show();
        }
    }
    //==================================================================================================================

    // Đặt tiền "$10.000"
    @FXML
    protected void onNumberClicked4(MouseEvent event) {
        int value4 = Integer.parseInt(label4.getText());
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount >= 10000 && sumAccount - value4 > 10000) {
            int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
            String text;
            if (Integer.parseInt(label4.getText()) == 0) {
                text = String.valueOf((Integer) value);
            } else {
                Integer sum = Integer.parseInt(label4.getText()) + value;
                text = String.valueOf(sum);
            }
            label4.setText(text);
            selectLabel4 = 4;
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
        value1 = Integer.parseInt(label1.getText());
        value2 = Integer.parseInt(label2.getText());
        value3 = Integer.parseInt(label3.getText());
        value4 = Integer.parseInt(label4.getText());
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("KẾT QUẢ");
        sumAccount = BoardGameConsts.result(value1, value2, value3, value4, finalIndex, selectLabel1, sumAccount, selectLabel2, selectLabel3, selectTaiXiu);
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
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void backLogin(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Login.fxml"));
        root = loader.load();
        LoginController scene1Controller = loader.getController();
//        FXMLDocumentController scene1Controller = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}