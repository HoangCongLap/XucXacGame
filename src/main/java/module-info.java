module xucxac {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires lombok;



    opens xucxac to javafx.fxml;
    exports xucxac;
    exports xucxac.database;
    opens xucxac.database to javafx.fxml;

    exports xucxac.consts;
    exports xucxac.data;
    exports xucxac.database.entites;
}