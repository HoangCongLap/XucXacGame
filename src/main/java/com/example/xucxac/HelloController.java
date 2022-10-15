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
    private Button instruction;
    @FXML
    private TextField myTextFieldSum;
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

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("THÔNG BÁO");
        alert.setHeaderText("Nạp tiền thành công.");
        alert.setContentText("Tiền trong tài khoản: " + sumAccount);
        alert.show();
        System.out.println(sumAccount);
    }

    @FXML
    void roll(ActionEvent event) {
        if (sumAccount >= 500 && (selectTaiXiu == 1 || selectTaiXiu == 2) && (selectLabel1 == 1 || selectLabel2 == 2 || selectLabel3 == 3 || selectLabel4 == 4)) {
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
            quayXucXac = 1;
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
                this.xiu.setStyle("-fx-border-color:red; -fx-background-color: #d3d3d3;");
                selectTaiXiu = 2;
            } else {
                this.tai.setStyle("-fx-border-color:red; -fx-background-color: #d3d3d3;");
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
        if ((selectTaiXiu == 1 || selectTaiXiu == 2) && sumAccount > 500) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n"+"Tiền trong tài khoản <500.");
            alert.show();
        }
    }
    //==================================================================================================================

    //    Đặt tiền "\$1.000"
    @FXML
    protected void onNumberClicked2(MouseEvent event) {
        if ((selectTaiXiu == 1 || selectTaiXiu == 2)&& sumAccount > 1000) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n"+"Tiền trong tài khoản <1000.");
            alert.show();
        }
    }
    //==================================================================================================================

    //    Đặt tiền "\$5.000"
    @FXML
    protected void onNumberClicked3(MouseEvent event) {
        if ((selectTaiXiu == 1 || selectTaiXiu == 2)&& sumAccount > 5000) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n"+"Tiền trong tài khoản <5000.");
            alert.show();
        }
    }
    //==================================================================================================================

    // Đặt tiền "$10.000"
    @FXML
    protected void onNumberClicked4(MouseEvent event) {
        if ((selectTaiXiu == 1 || selectTaiXiu == 2)&& sumAccount > 10000) {
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
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Hãy chọn 'TÀI' hoặc 'XỈU' trước khi đặt cược.\n"+"Tiền trong tài khoản <10000.");
            alert.show();
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
        if (quayXucXac == 1) {
            String getValue = lblResult.getText();
            int numberOfButtons = Integer.parseInt(getValue);
            Alert alert = new Alert(Alert.AlertType.NONE);
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
                alert.setContentText("LOSE\n"+ "Account: " + sumAccount);
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
                alert.setContentText("LOSE\n"+ "Account: " + sumAccount);
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
                alert.setContentText("WIN\n"+ "Account: " + sumAccount);
            }
            alert.setHeaderText("Số nút là: " + getValue);
            alert.show();
            System.out.println(sumAccount);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("THÔNG BÁO");
            alert.setHeaderText("Bạn chưa thực hiện đủ các bước.");
            alert.show();
        }
    }

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
}