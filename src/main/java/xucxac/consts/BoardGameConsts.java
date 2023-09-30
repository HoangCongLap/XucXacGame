package xucxac.consts;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class BoardGameConsts {

    public static Object BettingValue;
    @FXML
    private static Label label1;
    @FXML
    private static Label label2;
    @FXML
    private static Label label3;
    @FXML
    private static Label label4;
    @FXML
    private static Button topLeft;
    @FXML
    private static Button topRight;
    @FXML
    private static Button bottomLeft;
    @FXML
    private static Button bottomRight;

    public static final String colorTaiXiu() {
        return "-fx-color:red-fx-border; -fx-background-color: #d3d3d3;";
    }

    public static final String setColorTaiXiu() {
        return "-fx-color:red-fx-border; -fx-background-color: #d3d3d3;";
    }

    public static final String PATHIMAGES = "src/main/resources/xucxac/images/dice";

    public class BettingValue {
        private int topLeft;
        private int topRight;
        private int bottomLeft;
        private int bottomRight;
    }

    public static final int result(int value1, int value2, int value3, int value4, int finalIndex,
                                   int selectLabelTopLeft, int sumAccount, int selectLabelTopRight,
                                  int selectLabelBottomLeft, int selectTaiXiu) {
        System.out.println("value1"+ value1+ ",value2"+ value2+ ",value3"+ value3+ ",value4"+ value4+ ",finalIndex:"+ finalIndex
                + ",selectLabelTopLeft:"+ selectLabelTopLeft+ ",sumAccount:"+ sumAccount+ ",selectLabelTopRight:"+ selectLabelTopRight
                + ",selectLabelBottomLeft:"+ selectLabelBottomLeft+ ",selectTaiXiu:"+ selectTaiXiu);


        if (finalIndex == 1 || finalIndex == 6) {

            if (selectLabelTopLeft == 1) {
                sumAccount -= value1;
            } else if (selectLabelTopRight == 2) {
                sumAccount -= value2;
            } else if (selectLabelBottomLeft == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }

//
        } else if (finalIndex <= 3 && selectTaiXiu == 2 || finalIndex > 3 && selectTaiXiu == 1) {

            if (selectLabelTopLeft == 1) {
                sumAccount -= value1;
            } else if (selectLabelTopRight == 2) {
                sumAccount -= value2;
            } else if (selectLabelBottomLeft == 3) {
                sumAccount -= value3;
            } else {
                sumAccount -= value4;
            }
//
        } else {
            if (selectLabelTopLeft == 1) {
                sumAccount += value1;
            } else if (selectLabelTopRight == 2) {
                sumAccount += value2;
            } else if (selectLabelBottomLeft == 3) {
                sumAccount += value3;
            } else {
                sumAccount += value4;
            }
        }
        return sumAccount;
    }

    public static int ranDomIdPhong() {
        Random ran = new Random();
        int index = (ran.nextInt(100000, 999999));
        return index;
    }

}
