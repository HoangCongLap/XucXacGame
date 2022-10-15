package com.example.xucxac;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.Random;

public class HelloController {
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
    private TextField myTextFieldSum;

    private int selectTaiXiu = 0;
    private int selectLabel1 = 0;
    private int selectLabel2 = 0;
    private int selectLabel3 = 0;
    private int selectLabel4 = 0;

    private int sumAccount = 0;

    //Tổng tiền trong Account
    @FXML
    protected void newButtonClick(ActionEvent event) {
        sumAccount = Integer.parseInt(myTextFieldSum.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText("Nạp tiền thành công.");
        alert.setContentText("Tiền trong tài khoản:" + sumAccount);
        alert.show();
        System.out.println(sumAccount);
    }

    @FXML
    void roll(ActionEvent event) {
        if (sumAccount >= 500) {
            rollButton.setDisable(true);
            Thread theard = new Thread() {
                @Override
                public void run() {
                    System.out.println("Thread Running");
                    try {
                        int index = 1;
                        for (int i = 0; i < 15; i++) {
                            index = (random.nextInt(6) + 1);
                            String path = "src/main/resources/com/example/xucxac/IMG/dice" + index + ".png";
//                        String path = "src/main/resources/com/example/xucxac/IMG/dice" + ((i%6)+1) + ".png";
//                        System.out.println("path = " + path);

                            File file = new File(path);
//                        System.out.println(file.exists());
                            diceImage.setImage(new Image(file.toURI().toString()));
//                        if (i <= 9) {
//                            Thread.sleep(100);
//                        } else {
//                            Thread.sleep(100 * i);
//                        }
                            Thread.sleep(100);
                        }
//======================================================================================================================
                        int finalIndex = index;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                lblResult.setText(String.valueOf(finalIndex));
                            }
                        });
//======================================================================================================================
                        rollButton.setDisable(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            theard.start();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Tiền trong tài khoản <500.");
            alert.show();
        }
    }

    //==================================================================================================================

    //    Đặt tiền "\$500"
    @FXML
    protected void onNumberClicked1(MouseEvent event) {
        int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
        String text;
        if (Integer.parseInt(label1.getText()) == 0) {
            text = String.valueOf((Integer) value);
        } else {
            Integer sum = Integer.parseInt(label1.getText()) * 10 + value;
            text = String.valueOf(sum);
        }
        label1.setText(text);
        selectLabel1 = 1;
    }
    //==================================================================================================================

    //    Đặt tiền "\$1.000"
    @FXML
    protected void onNumberClicked2(MouseEvent event) {
        int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
        String text;
        if (Integer.parseInt(label2.getText()) == 0) {
            text = String.valueOf((Integer) value);
        } else {
            Integer sum = Integer.parseInt(label2.getText()) * 10 + value;
            text = String.valueOf(sum);
        }
        label2.setText(text);
        selectLabel2 = 2;
    }
    //==================================================================================================================

    //    Đặt tiền "\$5.000"
    @FXML
    protected void onNumberClicked3(MouseEvent event) {
        int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
        String text;
        if (Integer.parseInt(label3.getText()) == 0) {
            text = String.valueOf((Integer) value);
        } else {
            Integer sum = Integer.parseInt(label3.getText()) * 10 + value;
            text = String.valueOf(sum);
        }
        label3.setText(text);
        selectLabel3 = 3;
    }
    //==================================================================================================================

    // Đặt tiền "$10.000"
    @FXML
    protected void onNumberClicked4(MouseEvent event) {
        int value = Integer.parseInt(((Button) event.getSource()).getId().replace("button", ""));
        String text;
        if (Integer.parseInt(label4.getText()) == 0) {
            text = String.valueOf((Integer) value);
        } else {
            Integer sum = Integer.parseInt(label4.getText()) * 10 + value;
            text = String.valueOf(sum);
        }
        label4.setText(text);
        selectLabel4 = 4;
    }
    //==================================================================================================================

    // xử lí chọn tài hoặc xỉu
    @FXML
    protected void handleClicked2(MouseEvent event) {
        Label selectedtLabel = (Label) event.getSource();
        selectedtLabel.setStyle("-fx-border-color:red; -fx-background-color: blue;");
        if (((Label) event.getSource()).getId().equals("tai")) {
            this.xiu.setStyle("-fx-border-color:red; -fx-background-color: #d3d3d3;");
            selectTaiXiu = 2;
        } else {
            this.tai.setStyle("-fx-border-color:red; -fx-background-color: #d3d3d3;");
            selectTaiXiu = 1;
        }
    }

    //==================================================================================================================

//    @FXML
//    protected void Account(MouseEvent event) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("KẾT QUẢ");
//        alert.show();
//    }
//    @FXML
//    protected void Account(MouseEvent event) {
//        String getValue = lblResult.getText();
//        int numberOfButtons = Integer.parseInt(getValue);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("KẾT QUẢ");
//    }

    //    @FXML
//    protected void sumMoneyAccount(MouseEvent event) {
//        String text = String.valueOf(123456);
//        Label selectedtLabel = (Label) event.getSource();
//        selectedtLabel.setText(text);
//    }
// xuất kết quả thắng thua
    @FXML
    protected void ResultGame(MouseEvent event) {
        String getValue = lblResult.getText();
        int numberOfButtons = Integer.parseInt(getValue);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("KẾT QUẢ");
        int value1, value2, value3, value4;
        value1 = Integer.parseInt(label1.getText());
        value2 = Integer.parseInt(label2.getText());
        value3 = Integer.parseInt(label3.getText());
        value4 = Integer.parseInt(label4.getText());
//        System.out.println(label1.getText());
        if (numberOfButtons == 1 || numberOfButtons == 6) {
            if (selectLabel1 == 1) {
                sumAccount -= value1;
            } else if (selectLabel2 == 2) {
                sumAccount -= value2;
            } else if (selectLabel3 == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }
            alert.setHeaderText("LOSE");
        } else if (numberOfButtons <= 3 && selectTaiXiu == 2 || numberOfButtons > 3 && selectTaiXiu == 1) {
            if (selectLabel1 == 1) {
                sumAccount -= value1;
            } else if (selectLabel2 == 2) {
                sumAccount -= value2;
            } else if (selectLabel3 == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }
            alert.setHeaderText("LOSE");
        } else {
            if (selectLabel1 == 1) {
                sumAccount += value1;
            } else if (selectLabel2 == 2) {
                sumAccount += value2;
            } else if (selectLabel3 == 3) {
                sumAccount += value3;
            } else {
                sumAccount += value4;
            }
            alert.setHeaderText("WIN");
        }
        alert.setContentText(getValue);
        alert.show();
        System.out.println(sumAccount);
    }

}