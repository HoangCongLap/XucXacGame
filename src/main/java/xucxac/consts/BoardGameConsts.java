package xucxac.consts;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BoardGameConsts {
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;

    public static final String colorTaiXiu() {
        return "-fx-color:red-fx-border; -fx-background-color: #d3d3d3;";
    }

    public static final String PATHIMAGES = "src/main/resources/xucxac/images/dice";



    public static final int result(int value1, int value2, int value3, int value4, int finalIndex,
                                   int selectLabel1, int sumAccount, int selectLabel2, int selectLabel3, int selectTaiXiu) {
        if (finalIndex == 1 || finalIndex == 6) {

            if (selectLabel1 == 1) {
                sumAccount -= value1;
            } else if (selectLabel2 == 2) {
                sumAccount -= value2;
            } else if (selectLabel3 == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }
//
        } else if (finalIndex <= 3 && selectTaiXiu == 2 || finalIndex > 3 && selectTaiXiu == 1) {

            if (selectLabel1 == 1) {
                sumAccount -= value1;
            } else if (selectLabel2 == 2) {
                sumAccount -= value2;
            } else if (selectLabel3 == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }
//
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
        }
        return sumAccount;
    }

}
