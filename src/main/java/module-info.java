module com.example.xucxac {
    requires javafx.controls;
    requires javafx.fxml;


    opens XucXac to javafx.fxml;
    exports XucXac;
}