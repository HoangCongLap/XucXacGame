module com.example.xucxac {
    requires javafx.controls;
    requires javafx.fxml;


    opens xucxac to javafx.fxml;
    exports xucxac;
}