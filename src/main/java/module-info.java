module com.example.xucxac {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.xucxac to javafx.fxml;
    exports com.example.xucxac;
}