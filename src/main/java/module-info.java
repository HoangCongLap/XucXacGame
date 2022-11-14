module com.example.xucxac {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens xucxac to javafx.fxml;
    exports xucxac;
}